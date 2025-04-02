<template>
  <div class="border">
    <el-form :model="loginForm" class="login-box">
      <h3 class="login-title" style="color: #888888">请登录</h3>
      <el-form-item label="登录名" prop="name">
        <el-input type="text" v-model="loginForm.name" style="width:200px" size="small"></el-input>
      </el-form-item>
      <el-form-item label="密&emsp;码" prop="pswd">
        <el-input type="password" v-model="loginForm.pswd" style="width:200px" size="small"></el-input>
      </el-form-item>
      <el-form-item style="position: relative; left:20%">
        <el-button class="el-button" @click="gotoMain" style="width: 150px; ">登录</el-button>
      </el-form-item>
    </el-form>
    <div>{{ errorMsg }}</div>
  </div>
</template>

<script setup>
import router from "@/util/router";
import { reactive } from 'vue';

let errorMsg = reactive({});

let loginForm = reactive({
  name: '',
  pswd: ''
});

function gotoMain() {
  console.log(loginForm);
  router.push({ path: '/index' });
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
  text-align: center;
}
</style>