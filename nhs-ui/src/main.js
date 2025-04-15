import { createApp } from 'vue'
import App from './App.vue'
import router from "@/util/router"
import ElementPlus from 'element-plus';
import 'element-plus/theme-chalk/index.css';
import locale from "element-plus/es/locale/lang/zh-cn";
import { createPinia } from "pinia";

createApp(App)
    .use(createPinia())
    .use(router)
    .use(ElementPlus, { locale })
    .mount('#app')
