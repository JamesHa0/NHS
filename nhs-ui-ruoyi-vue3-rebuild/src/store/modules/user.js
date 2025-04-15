import { login, logout, getInfo } from '@/api/login'
import { getToken, setToken, removeToken, getUserId, setUserId } from '@/utils/auth'
import { isHttp, isEmpty } from "@/utils/validate"
import defAva from '@/assets/images/profile.jpg'

const useUserStore = defineStore(
  'user',
  {
    state: () => ({
      token: getToken(),
      userId: getUserId(),
      name: '',
      avatar: '',
      roles: [],
      permissions: []
    }),
    actions: {
      // 登录
      login(userInfo) {
        const username = userInfo.username.trim()
        const password = userInfo.password
        const code = userInfo.code
        const uuid = userInfo.uuid
        return new Promise((resolve, reject) => {
          login(username, password, code, uuid).then(res => {
            setToken(res.data.token)
            setUserId(res.data.id)
            console.log(`登录成功`);
            this.userId = res.data.id
            this.token = res.data.token
            resolve()
          }).catch(error => {
            console.log(`登录失败`);
            reject(error)
          })
        })
      },
      // 获取用户信息
      getInfo(userId) {
        return new Promise((resolve, reject) => {
          getInfo(userId).then(res => {
            console.log(`获取用户信息成功：`, res.data);
            let avatar = res.data.avatar || ""
            if (!isHttp(avatar)) {
              avatar = (isEmpty(avatar)) ? defAva : import.meta.env.VITE_APP_BASE_API + avatar
            }
            if (res.data[0].roleId) { // 验证返回的roles是否非空
              this.roles = res.data[0].roleId
              this.permissions = ["PERMISSIONS_DEFAULT"]
              //this.permissions = res.permissions // 这里没有权限（我的数据库里角色代表了权限）
            } else {
              this.roles = ['ROLE_DEFAULT']
            }
            this.name = res.data[0].nickname
            this.avatar = avatar
            resolve(res)
          }).catch(error => {
            reject(error)
          })
        })
      },
      // 退出系统
      logOut() {
        return new Promise((resolve, reject) => {
          logout(this.token).then(() => {
            this.token = ''
            this.roles = ''
            this.permissions = []
            removeToken()
            resolve()
          }).catch(error => {
            reject(error)
          })
        })
      }
    }
  })

export default useUserStore
