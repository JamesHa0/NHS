import {defineStore} from 'pinia'

export const useUserStore = defineStore(
    'user', {
        state: ()=>({
            token: null,
            userinfo: null
        }),
        actions: {
            //传值
            setToken(token){
                this.token = token;
                localStorage.setItem("token", this.token)
            },

            //删除token
            removeToken(){
                this.token = null;
                localStorage.removeItem("token")
            },
        },
        getters: {
            getToken:(state) => {
                if(!state.token){
                    return localStorage.getItem("token")
                }
                return state.token;
            }
        }
    }
)