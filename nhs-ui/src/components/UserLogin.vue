<template>
  <!--    <router-link to="/main">跳转到主页</router-link>-->

  <div class="border">
    <el-form :model="loginForm" class="login-box">
      <h3 class="login-title" style="color: #888888">请登录</h3>
      <el-form-item label="登录名" prop="name">
        <el-input type="text" v-model="loginForm.name" style="width:200px" size="small"></el-input>
      </el-form-item>
      <el-form-item label="密&emsp;码" prop="pswd">
        <el-input type="password" v-model="loginForm.pswd" style="width:200px"></el-input>
      </el-form-item>
      <el-form-item style="position: relative; left:20%">
        <el-button class="el-button" @click="login" style="width: 150px; ">登录</el-button>
      </el-form-item>
    </el-form>
    <div>{{ errorMsg }}</div>
  </div>
</template>

<script setup>
import { reactive } from "vue";
import router from "@/util/router";
import http from '@/axios/http';
import qs from "qs";
import { useUserStore } from "@/store/userStore";

let loginForm = reactive({});
let errorMsg = reactive();

function login() {
  /*    console.log(loginForm);
      //路由通过渲染组件
      router.push({
        path: '/MainPage',
        state: loginForm
      });*/
  //组参数
  const logindata = {
    username: loginForm.name,
    password: loginForm.pswd
  };
  http.post('/lyy/login', qs.stringify(logindata)).then((res) => {
    let result = res.data;

    if (result.result === "success") {

      //获取当前用户信息
      let loginUser = result.data;
      console.log('当前用户：' + loginUser);

      //存token
      let token = loginUser.token;
      const userStore = useUserStore();
      userStore.setToken(token);
      console.log("userStore.getToken" + userStore.getToken);
      router.push({ path: '/', state: result.data });
    } else {
      errorMsg = result.error;
    }
  })
}

</script>

<style scoped>
.login-box {
  background-color: azure;
  height: 100%;
  width: 350px;
  margin: 120px auto;
  border: 1px solid #DCDFE6;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 30px #DCDFE6;
}

.login-title {
  text-algin: center;
}
</style>