<template>

    <el-form :model="queryParams" ref="queryRef" :inline="true" @submit.native.prevent>
        <el-form-item label="用户查询" prop="nickname">
            <el-input v-model="queryParams.nickname" placeholder="请输入要查询的用户姓名" clearable style="width: 185px"
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
                <div class="card-header">用户列表</div>
            </template>
            <el-table v-loading="loading" :data="userList.slice((pageNum - 1) * pageSize, pageNum * pageSize)"
                style="width: 100%;" highlight-current-row @current-change="row_change" ref="usersTable">
                <el-table-column label="序号" width="50" type="index" align="center">
                    <template #default="scope">
                        <span>{{ (pageNum - 1) * pageSize + scope.$index + 1 }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="ID" align="center" prop="id" :show-overflow-tooltip="true" v-if=false />
                <el-table-column label="系统账号" align="center" prop="username" :show-overflow-tooltip="true" />
                <el-table-column label="姓名" align="center" prop="nickname" :show-overflow-tooltip="true" width="110" />
                <el-table-column label="性别" align="center" prop="sex" :show-overflow-tooltip="true" width="50">
                    <template #default="scope">
                        <span v-if="scope.row.sex == 0">男</span>
                        <span v-else-if="scope.row.sex == 1">女</span>
                    </template>
                </el-table-column>
                <el-table-column label="角色" align="center" prop="roleId" :show-overflow-tooltip="true" width="110">
                    <template #default="scope">
                        <span v-if="scope.row.roleId == 1">管理员</span>
                        <span v-else-if="scope.row.roleId == 2">健康管家</span>
                    </template>
                </el-table-column>
            </el-table>

            <pagination v-show="total > 0" :total="total" v-model:page="pageNum" v-model:limit="pageSize"
                class="pagination" />
        </el-card>

    </el-form>

</template>


<script setup>
import { list as getUsers, listById as getUserById } from '@/api/user/user';
import useUserStore from '@/store/modules/user';

const { proxy } = getCurrentInstance();

const loading = ref(true);
const total = ref(0);
const pageNum = ref(1);
const pageSize = ref(10);
const isManager = ref(false);
const userList = ref([]);
const usersTable = ref(null);

let queryParams = ref({
    nickname: undefined
});

const emit = defineEmits(['selectOne']);

const row_change = (row) => {

    if (row == null || row == undefined || row == -1) {
        let param = { userId: -2 }
        // 触发事件并传递数据给父组件
        emit('selectOne', param)
    } else {
        let param = {
            userId: row.id
        }
        // 触发事件并传递数据给父组件
        emit('selectOne', param)
    }

}

/** 设置是否管理员 */
function setManager() {
    if (useUserStore().roles === 1) {
        isManager.value = true;
    }
}


/** 查询用户列表 */
function getList() {
    loading.value = true;
    setManager();
    if (isManager.value) {
        // 查所有用户
        getUsers(queryParams.value).then(response => {
            userList.value = response.data;
            total.value = response.data.length;
            loading.value = false;
        });
    } else {
        // 查该用户
        getUserById(useUserStore().userId).then(response => {
            userList.value = response.data;
            total.value = response.data.length;
            loading.value = false;
        });
    }

}

/** 搜索按钮操作 */
function handleQuery() {
    pageNum.value = 1;
    getList();
}

/** 重置按钮操作 */
function resetQuery() {
    proxy.resetForm("queryRef");
    if (usersTable.value) {
        usersTable.value.setCurrentRow(-1);
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