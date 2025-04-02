import { createApp } from 'vue'
import App from './App.vue'
import router from "@/util/router"
import "element-plus/dist/index.css"
import locale from 'element-plus/es/locale/lang/zh-cn'
import ElementPlus from 'element-plus'

createApp(App)
    .use(router)
    .use(ElementPlus, { locale })
    .mount('#app')
