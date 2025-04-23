<template>

    <el-form :model="queryParams" ref="queryRef" :inline="true" @submit.native.prevent>
        <el-form-item label="客户查询" prop="customerName">
            <el-input v-model="queryParams.customerName" placeholder="请输入要查询的客户姓名" clearable style="width: 185px"
                @keyup.enter="handleQuery" />
            <el-tooltip content="搜索" effect="dark" placement="top">
                <el-button type="primary" icon="Search" @click="handleQuery" />
            </el-tooltip>
            <el-tooltip content="重置" effect="dark" placement="top">
                <el-button icon="Refresh" @click="resetQuery" />
            </el-tooltip>
        </el-form-item>

        <el-card class="card-box">
            <template #header>
                <div class="card-header">客户列表</div>
            </template>
            <el-table v-loading="loading"
                :data="filteredCustomersList.slice((pageNum - 1) * pageSize, pageNum * pageSize)" style="width: 100%;">
                <el-table-column label="序号" width="50" type="index" align="center">
                    <template #default="scope">
                        <span>{{ (pageNum - 1) * pageSize + scope.$index + 1 }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="ID" align="center" prop="id" :show-overflow-tooltip="true" v-if=false />
                <el-table-column label="姓名" align="center" prop="customerName" :show-overflow-tooltip="true"
                    width="70" />
                <el-table-column label="性别" align="center" prop="customerSex" :show-overflow-tooltip="true" width="50">
                    <template #default="scope">
                        <span v-if="scope.row.customerSex == 0">男</span>
                        <span v-else-if="scope.row.customerSex == 1">女</span>
                    </template>
                </el-table-column>
                <el-table-column label="年龄" align="center" prop="customerAge" :show-overflow-tooltip="true"
                    width="50" />
                <el-table-column label="床位" align="center" :show-overflow-tooltip="true" width="86">
                    <template #default="scope">
                        <span>{{ scope.row.roomNo }} - {{ scope.row.bedId }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="护理级别" align="center" prop="levelName" :show-overflow-tooltip="true"
                    width="80" />
            </el-table>

            <pagination v-show="total > 0" :total="total" v-model:page="pageNum" v-model:limit="pageSize"
                class="pagination" />
        </el-card>

    </el-form>

</template>


<script setup>

const { proxy } = getCurrentInstance();

const loading = ref(true);
const total = ref(0);
const pageNum = ref(1);
const pageSize = ref(5);

let queryParams = ref({
    customerName: undefined
});

const props = defineProps({
    //接收父组件传递过来的值
    customersList: {}
})
//使用父组件传递过来的值
const { customersList } = toRefs(props)

// 计算过滤后的客户列表
const filteredCustomersList = ref(customersList.value);

/** 查询护理记录列表 */
function getList() {
    loading.value = true;
    // 在前端实现搜索功能
    if (!queryParams.value.customerName) {
        filteredCustomersList.value = customersList.value;
    } else {
        filteredCustomersList.value = customersList.value.filter(customer =>
            customer.customerName.includes(queryParams.value.customerName)
        );
    }

    total.value = filteredCustomersList.value.length;
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
    queryParams.value.customerName = undefined;
    handleQuery();
}

getList();



</script>


<style lang="css" scoped>
.pagination :deep(.el-pagination__sizes) {
    margin: 0 0 0 10px;
}

.pagination :deep(.el-select) {
    width: 100px;
}

.pagination :deep(.btn-prev) {
    margin: 0 0 0 15px;
}

.pagination :deep(.el-input) {
    width: 40px;
}

.pagination :deep(.btn-next) {
    margin: 0;
}

.pagination :deep(.el-pagination__jump is-last) {
    margin: 0 0 0 5px;
}

.card-box :deep(.el-card__body) {
    padding: 10px 1px 20px 2px;
}
</style>