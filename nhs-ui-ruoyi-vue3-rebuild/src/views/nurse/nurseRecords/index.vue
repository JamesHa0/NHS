<template>
    <div class="app-container">
        <el-row :gutter="10">
            <el-col :span="8">
                <customer :customersList="customersList" v-if="customersList.length > 0"
                    @selectOne="handleChildSelected" />
            </el-col>
            <el-col :span="16">
                <el-form :inline="true" @submit.native.prevent>
                    <el-form-item label="护理记录">
                        <el-button type="primary" icon="Plus" @click="addNurseRecord">添加</el-button>
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



        <!--添加护理记录的对话框  BEGIN-->
        <el-dialog v-model="addFormVisible" title="添加护理记录" width="700">
            <el-form :model="addForm" :rules="rules" ref="addFormRef">
                <el-form-item label="客户姓名" :label-width="100">
                    <el-select v-model="addForm.customerId" filterable placeholder="请选择客户">
                        <el-option v-for="item in customers" :key="item.id" :label="item.customerName" :value="item.id">
                            <span style="float: left">{{ item.customerName }}（{{ item.customerAge }}岁）</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">
                                所属楼房: {{ item.buildingNo }} | 房间号: {{ item.roomNo }} | 床号: {{ item.bedId }}
                            </span>
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="护理项目" :label-width="100">
                    <el-select v-model="addForm.itemId" filterable placeholder="请选择护理项目">
                        <el-option v-for="item in items" :key="item.id" :label="item.nursingName" :value="item.id">
                            <span style="float: left">{{ item.serialNumber }} | {{ item.nursingName }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">
                                价格: {{ item.servicePrice }} | 执行周期: {{ item.executionCycle }} | 执行次数: {{
                                    item.executionTimes }}
                            </span>
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="护理时间" :label-width="100">
                    <el-date-picker v-model="addForm.nursingTime" type="datetime" placeholder="选择日期时间"
                        value-format="timestamp" />
                </el-form-item>
                <el-form-item label="护理内容" :label-width="100">
                    <el-input v-model="addForm.nursingContent" />
                </el-form-item>
                <el-form-item label="护理数量" :label-width="100" type="number">
                    <el-input v-model="addForm.nursingCount" />
                </el-form-item>
                <el-form-item label="护理人员" :label-width="100" type="number">
                    <!-- todo -->
                </el-form-item>

            </el-form>
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="addFormVisible = false">取消</el-button>
                    <el-button type="primary" @click="submitAdd()">
                        确定
                    </el-button>
                </div>
            </template>
        </el-dialog>
        <!--添加护理记录的对话框  END-->


    </div>
</template>

<script setup name="NurseItem">
import { list as initData, deleteItem, update, add } from "@/api/nurse/nurseRecords";
import customer from "../components/customer.vue";
import { list as getCustomers } from "@/api/customer/customer";
import { list as getItems } from "@/api/nurse/nurseItem";

const { proxy } = getCurrentInstance();

const nurseRecordsList = ref([]);
const customersList = ref([]);
const loading = ref(true);
const total = ref(0);
const pageNum = ref(1);
const pageSize = ref(10);

let addFormVisible = ref(false);
let editFormVisible = ref(false);

const customers = ref([]);
const items = ref([]);


let addForm = ref({
    customerId: undefined,
    itemId: undefined,
    nursingTime: undefined,
    nursingContent: undefined,
    nursingCount: undefined,
    userId: undefined
});

let queryParams = ref({
    customerName: undefined
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
    initData().then(response => {
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

/** 添加按钮操作 */
function addNurseRecord() {
    getCustomers().then(response => {
        customers.value = response.data;
    });
    getItems().then(response => {
        items.value = response.data;
    });
    addFormVisible.value = true;
}

/** 提交添加项目 */
function submitAdd() {
    addFormRef.value.validate((valid) => {
        if (valid) {
            addFormVisible.value = false;
            add(addForm.value).then(response => {
                getList();
                proxy.$modal.msgSuccess("添加成功");
            })
                .catch(() => {
                    getList();
                    proxy.$modal.msgError("添加失败");
                });
        } else {
            proxy.$modal.msgError('护理级别不能为空');
            return false;
        }
    });
}

getList();
</script>

<style scoped></style>