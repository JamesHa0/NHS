import router from './router'
import { ElMessage } from 'element-plus'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import { getToken } from '@/utils/auth'
import { isHttp, isPathMatch } from '@/utils/validate'
import { isRelogin } from '@/utils/request'
import useUserStore from '@/store/modules/user'
import useSettingsStore from '@/store/modules/settings'
import usePermissionStore from '@/store/modules/permission'

NProgress.configure({ showSpinner: false })

const whiteList = ['/login', '/register']

const isWhiteList = (path) => {
  return whiteList.some(pattern => isPathMatch(pattern, path))
}

router.beforeEach((to, from, next) => {
  NProgress.start()
  console.log(`==========发送请求==========`)
  if (getToken()) {
    console.log(`进入守卫`)
    to.meta.title && useSettingsStore().setTitle(to.meta.title)
    /* has token*/
    if (to.path === '/login') {
      next({ path: '/' })
      NProgress.done()
    } else if (isWhiteList(to.path)) {
      next()
    } else {
      console.log(`当前用户:`);
      // 打印用户信息
      console.log('Token:', useUserStore().token);
      console.log('User ID:', useUserStore().userId);
      console.log('Name:', useUserStore().name);
      console.log('Avatar:', useUserStore().avatar);
      console.log('Roles:', useUserStore().roles);
      console.log('Permissions:', useUserStore().permissions);

      if (useUserStore().roles.length === 0) {
        isRelogin.show = true
        console.log(`获取存储的用户ID：`, useUserStore().userId)
        // 判断当前用户是否已拉取完user_info信息
        useUserStore().getInfo(useUserStore().userId).then(() => {
          isRelogin.show = false
          usePermissionStore().generateRoutes().then(accessRoutes => {
            // 根据roleId权限生成可访问的路由表
            accessRoutes.forEach(route => {
              console.log('路由对象:', route);
              if (!isHttp(route.path)) {
                router.addRoute(route) // 动态添加可访问路由表
              }
            })
            next({ ...to, replace: true }) // hack方法 确保addRoutes已完成
          })
        }).catch(err => {
          useUserStore().logOut().then(() => {
            ElMessage.error(err)
            next({ path: '/' })
          })
        })
      } else {
        next()
      }
    }
  } else {
    // 没有token
    if (isWhiteList(to.path)) {
      // 在免登录白名单，直接进入
      next()
    } else {
      next(`/login?redirect=${to.fullPath}`) // 否则全部重定向到登录页
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done()
})
