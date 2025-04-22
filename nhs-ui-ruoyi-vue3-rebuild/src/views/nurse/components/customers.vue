<template>

    <el-form :model="queryParams" ref="queryRef" :inline="true" @submit.native.prevent>
        <el-form-item label="客户列表" prop="customerName">
            <el-input v-model="queryParams.customerName" placeholder="请输入要查询的客户姓名" clearable style="width: 200px"
                @keyup.enter="handleQuery" />
        </el-form-item>
        <el-form-item>
            <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
            <el-button icon="Refresh" @click="resetQuery">重置</el-button>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" icon="Plus" @click="addNurseItem">添加</el-button>
        </el-form-item>

        <el-table v-loading="loading" :data="customersList.slice((pageNum - 1) * pageSize, pageNum * pageSize)"
            style="width: 100%;">
            <el-table-column label="序号" width="50" type="index" align="center">
                <template #default="scope">
                    <span>{{ (pageNum - 1) * pageSize + scope.$index + 1 }}</span>
                </template>
            </el-table-column>
            <el-table-column label="ID" align="center" prop="id" :show-overflow-tooltip="true" v-if=false />
            <el-table-column label="姓名" align="center" prop="customerName" :show-overflow-tooltip="true" width="70" />
            <el-table-column label="性别" align="center" prop="customerSex" :show-overflow-tooltip="true" width="50">
                <template #default="scope">
                    <span v-if="scope.row.customerSex == 0">男</span>
                    <span v-else-if="scope.row.customerSex == 1">女</span>
                </template>
            </el-table-column>
            <el-table-column label="年龄" align="center" prop="customerAge" :show-overflow-tooltip="true" width="50" />
            <el-table-column label="床位" align="center" :show-overflow-tooltip="true" width="90">
                <template #default="scope">
                    <span>{{ scope.row.roomNo }} - {{ scope.row.bedId }}</span>
                </template>
            </el-table-column>
            <el-table-column label="护理级别" align="center" prop="levelId" :show-overflow-tooltip="true" width="80" />
        </el-table>

        <pagination v-show="total > 0" :total="total" v-model:page="pageNum" v-model:limit="pageSize" />

    </el-form>

</template>


<script setup>

const { proxy } = getCurrentInstance();

const loading = ref(true);
const total = ref(0);
const pageNum = ref(1);
const pageSize = ref(5);


let addFormVisible = ref(false);

let queryParams = ref({
    nursingName: undefined
});
const props = defineProps({
    //接收父组件传递过来的值
    customersList: {}
})
//使用父组件传递过来的值
const { customersList } = toRefs(props)

/** 查询护理记录列表 */
function getList() {
    loading.value = true;
    console.log(customersList);

    total.value = customersList.value.length;
    loading.value = false;
}

/** 搜索按钮操作 */
function handleQuery() {
    pageNum.value = 1;
    getList();
}

/** 重置按钮操作 */
function resetQuery() {
    proxy.resetForm("queryRef");
    handleQuery();
}

/** 添加按钮操作 */
function addNurseItem() {
    addFormVisible.value = true;
}


getList();



</script>


<style lang="css" scoped></style>