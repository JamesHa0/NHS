<template>
    <div class="app-container">
        <el-row :gutter="10">
            <el-col :span="8">
                <customers :customersList="customersList" v-if="customersList.length > 0"
                    @selectOne="handleChildSelected" />
            </el-col>
            <el-col :span="16">
                <el-form :model="queryParams" ref="queryRef" :inline="true" @submit.native.prevent>
                    <el-form-item label="护理记录">
                        <el-button type="primary" icon="Plus" @click="addNurseItem">添加</el-button>
                    </el-form-item>

                    <el-table v-loading="loading"
                        :data="filterednurseRecordsList.slice((pageNum - 1) * pageSize, pageNum * pageSize)"
                        style="width: 100%;">
                        <el-table-column label="序号" width="50" type="index" align="center">
                            <template #default="scope">
                                <span>{{ (pageNum - 1) * pageSize + scope.$index + 1 }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="ID" align="center" prop="id" :show-overflow-tooltip="true" v-if=false />
                        <el-table-column label="客户姓名" align="center" prop="customerInfo[0].customerName"
                            :show-overflow-tooltip="true" width="80" />
                        <el-table-column label="护理项目" align="center" prop="nurseContentInfo[0].nursingName"
                            :show-overflow-tooltip="true" />
                        <el-table-column label="护理时间" align="center" :show-overflow-tooltip="true" width="105">
                            <template #default="scope">
                                {{ formatDate(scope.row.nursingTime) }}
                            </template>
                        </el-table-column>
                        <el-table-column label="数量" align="center" prop="nursingCount" :show-overflow-tooltip="true"
                            width="50" />
                        <el-table-column label="护理内容" align="center" prop="nursingContent"
                            :show-overflow-tooltip="true" />
                        <el-table-column label="护理人员" align="center" prop="userInfo[0].nickname"
                            :show-overflow-tooltip="true" width="80" />
                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                            <template #default="scope">
                                <el-tooltip content="编辑" effect="dark" placement="top">
                                    <el-button type="primary" icon="Edit" @click="editNurseItem(scope.row)" />
                                </el-tooltip>
                                <el-tooltip content="删除" effect="dark" placement="top">
                                    <el-button type="danger" icon="Delete" @click="handleDelete(scope.row)" />
                                </el-tooltip>
                            </template>
                        </el-table-column>
                    </el-table>

                    <pagination v-show="total > 0" :total="total" v-model:page="pageNum" v-model:limit="pageSize" />

                </el-form>
            </el-col>
        </el-row>
    </div>
</template>

<script setup name="NurseItem">
import { list as initData, deleteItem, update, add } from "@/api/nurse/nurseRecords";
import customers from "../components/customers.vue";

const { proxy } = getCurrentInstance();

const nurseRecordsList = ref([]);
const customersList = ref([]);
const loading = ref(true);
const total = ref(0);
const pageNum = ref(1);
const pageSize = ref(10);

let queryParams = ref({
    nursingName: undefined
});

const currentSelected = ref(-1);

const handleChildSelected = (res) => {
    if (res.customerId == null || res.customerId == undefined || res.customerId == -1) {
        currentSelected.value = -1;
    } else {
        currentSelected.value = res.customerId;
    }
    getList();
}

// 计算过滤后的护理记录列表
const filterednurseRecordsList = ref(nurseRecordsList.value);

/** 查询护理记录列表 */
function getList() {
    loading.value = true;
    initData(queryParams.value).then(response => {
        nurseRecordsList.value = response.data;
        // 客户信息子组件赋值
        const customerInfoArray = response.data.flatMap(item => item.customerInfo);
        const uniqueCustomerInfo = Array.from(new Set(customerInfoArray.map(JSON.stringify)), JSON.parse);
        customersList.value = uniqueCustomerInfo;

        // 在前端实现筛选功能
        if (currentSelected.value == -1 || currentSelected.value == null || currentSelected.value == undefined) {
            filterednurseRecordsList.value = nurseRecordsList.value;
        } else {
            filterednurseRecordsList.value = nurseRecordsList.value.filter(record =>
                record.customerInfo[0].id === currentSelected.value
            );
        }

        total.value = filterednurseRecordsList.value.length;
        loading.value = false;
    });
}

// 格式化时间戳
function formatDate(timestamp) {
    const date = new Date(timestamp);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');

    return `${year}-${month}-${day}`;
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


getList();
</script>

<style scoped></style>