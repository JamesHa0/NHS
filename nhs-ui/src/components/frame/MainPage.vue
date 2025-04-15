<template>
  <div>

<!--    框架容器 -->
    <el-container>
<!--      上部分问候语-->
      <el-header>
        {{nickName}}:您好！
      </el-header>
<!--      下部分用容器装 -->
      <el-container>
<!--        下左-菜单-->
        <el-aside>
          <LeftMenu :roleid="roleid"/>
        </el-aside>

<!--        下中-功能显示区域-->
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
//import {useRoute} from "vue-router"
//const route = useRoute();
import LeftMenu from "@/components/frame/LeftMenu";

let nickName = history?.state?.nickname;
const roleid = history?.state?.roleId;

</script>

<style scoped>

</style>