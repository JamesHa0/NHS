<template>
  <div>
    <div>{{ errorMsg }}</div>
    <!-- 菜单 -->
    <el-menu>
      <el-sub-menu class="el-sub-menu" v-for="(menu, index) in menuList" :index="index" v-bind:key="menu.id">

        <!-- 一级菜单：显示菜单名 -->
        <template #title>
          <span>{{ menu.title }}</span>
        </template>

        <!-- 二级菜单：单击切换功能页 -->
        <template v-for="(child) in menu.children" v-bind:key="child.id">
          <el-menu-item>
            <!-- router-link指定单击路由 -->
            <router-link :to="child.path">
              {{ child.title }}
            </router-link>
          </el-menu-item>
        </template>
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, toRefs } from "vue";
import http from '@/axios/http';
import qs from "qs";


let errorMsg = reactive("");
const menuList = ref([]);

const props = defineProps({
  roleid: Number
})

const { roleid } = toRefs(props);


onMounted(() => {
  init()
})

//通过角色id查询当前用户菜单
function init() {
  const param = reactive({
    roleid: roleid
  });
  //查询菜单
  http.post('/lyy/getmenus', qs.stringify(param))
    .then((res) => {
      let resultmenu = res.data;

      if (resultmenu.result === "success") {
        menuList.value = resultmenu.data;
        console.log(menuList)

        //动态加载路由
        //dynamicAddRouter(menuList.value)


        if (menuList.value === null) {
          errorMsg = "获取菜单失败！";
        }
      } else {
        errorMsg = "获取菜单失败！";
      }
    })
}
</script>

<style scoped></style>