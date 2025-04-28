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
                :data="filteredCustomersList.slice((pageNum - 1) * pageSize, pageNum * pageSize)" style="width: 100%;"
                highlight-current-row @current-change="row_change" ref="customerTable">
                <el-table-column label="序号" width="50" type="index" align="center">
                    <template #default="scope">
                        <span>{{ (pageNum - 1) * pageSize + scope.$index + 1 }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="ID" align="center" prop="id" :show-overflow-tooltip="true" v-if=false />
                <el-table-column label="姓名" align="center" prop="customerName" :show-overflow-tooltip="true"
                    width="110" />
                <el-table-column label="性别" align="center" prop="customerSex" :show-overflow-tooltip="true" width="50">
                    <template #default="scope">
                        <span v-if="scope.row.customerSex == 0">男</span>
                        <span v-else-if="scope.row.customerSex == 1">女</span>
                    </template>
                </el-table-column>
                <el-table-column label="年龄" align="center" prop="customerAge" :show-overflow-tooltip="true"
                    width="50" />
                <el-table-column label="护理级别" align="center" prop="levelName" :show-overflow-tooltip="true"
                    width="110" />
                <el-table-column label="床位" align="center" :show-overflow-tooltip="true" width="86">
                    <template #default="scope">
                        <span>{{ scope.row.roomNo }} - {{ scope.row.bedId }}</span>
                    </template>
                </el-table-column>
            </el-table>

            <pagination v-show="total > 0" :total="total" v-model:page="pageNum" v-model:limit="pageSize"
                class="pagination" />
        </el-card>

    </el-form>

</template>


<script setup>
import { ref } from 'vue';


const { proxy } = getCurrentInstance();

const loading = ref(true);
const total = ref(0);
const pageNum = ref(1);
const pageSize = ref(10);
const customerTable = ref(null);

let queryParams = ref({
    customerName: undefined
});

const emit = defineEmits(['selectOne']);

const row_change = (row) => {

    if (row == null || row == undefined || row == -1) {
        let param = { customerId: -1 }
        // 触发事件并传递数据给父组件
        emit('selectOne', param)
    } else {
        let param = {
            customerId: row.id
        }
        // 触发事件并传递数据给父组件
        emit('selectOne', param)
    }

}

const props = defineProps({
    //接收父组件传递过来的值
    customersList: {}
})
//使用父组件传递过来的值
const { customersList } = toRefs(props)

// 计算过滤后的客户列表
//const filteredCustomersList = ref(customersList.value);
const filteredCustomersList = ref();

/** 查询护理记录列表 */
function getList() {
    loading.value = true;
    let getPushId = customersList.value.getPushId;

    if (getPushId) {
        filteredCustomersList.value = customersList.value.filter(customer =>
            String(customer.id).includes(getPushId)
        );
        let param = {
            customerId: Number(getPushId)
        }
        emit('selectOne', param)
    }

    // 在前端实现搜索功能
    else if (!queryParams.value.customerName) {
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
    if (customersList.value.getPushId) {
        delete customersList.value.getPushId;
    }
    proxy.resetForm("queryRef");
    if (customerTable.value) {
        customerTable.value.setCurrentRow(-1);
    }
    handleQuery();
}

getList();



</script>


<style lang="css" scoped>
.pagination :deep(.el-pagination__sizes) {
    margin: 0 0 0 5px;
}

.pagination :deep(.el-select) {
    width: 100px;
}

.pagination :deep(.btn-prev) {
    margin: 0 0 0 5px;
}

.pagination :deep(.el-input) {
    width: 35px;
}

.pagination :deep(.btn-next) {
    margin: 0;
}

.pagination :deep(.el-pagination__jump) {
    margin: 0 0 0 5px;
}

/* .card-box :deep(.el-card__body) {
    padding: 10px 1px 20px 2px;
} */
</style>