import IndexPage from "@/components/IndexPage.vue";
import UserLogin from "@/components/UserLogin.vue";
import { createRouter, createWebHashHistory } from "vue-router";

const routes = [
    {
        path: '/',
        redirect: '/index',
    },
    {
        path: '/index',
        component: IndexPage
    },
    {
        path: '/login',
        component: UserLogin
    }

]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router;