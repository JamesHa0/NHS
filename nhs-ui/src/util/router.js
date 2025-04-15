import { createRouter, createWebHashHistory } from "vue-router";
import UserLogin from "@/components/UserLogin"
import MainPage from "@/components/frame/MainPage"
import { useUserStore } from "@/store/userStore";

const routes = [
    {
        path: "/login",
        component: UserLogin
    },
    {
        name: "MainPage",
        path: "/",
        Redirect: "/MainPage",
        component: MainPage,
        children: [
            {
                name: "CheckIn",
                path: "/CheckIn",
                component: function () {
                    return import("@/views/CustomerManage/CheckIn")
                }
            }
        ]
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

//设置不拦截路径-白名单
let whiteList = ["/login"];
router.beforeEach(
    (to, from, next) => {
        const path = to.path
        console.log("path:" + path)

        if (whiteList.includes(path)) {//如果当前路径在白名单，直接渲染
            console.log("渲染下一个组件")
            next()      //渲染下一个组件
            return true;
        }

        const userStore = useUserStore();
        let token = userStore.getToken;

        if (token) { //如果有token，登录成功了
            console.log("路由守卫：通过")
            next()      //渲染下一个组件
            return true;
        } else {
            console.log("路由守卫：未登录")
            next("/login")  //转回登录页
            return false;
        }
    }
)

export default router;