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
                        <el-table-column label="护理时间" align="center" :show-overflow-tooltip="true" width="120">
                            <template #default="scope">
                                {{ parseTime(scope.row.nursingTime) }}
                            </template>
                        </el-table-column>
                        <el-table-column label="数量" align="center" prop="nursingCount" :show-overflow-tooltip="true"
                            width="50" />
                        <el-table-column label="护理内容" align="center" prop="nursingContent"
                            :show-overflow-tooltip="true" />
                        <el-table-column label="护理人员" align="center" prop="userInfo[0].nickname"
                            :show-overflow-tooltip="true" width="80" />
                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width"
                            v-if="isManager">
                            <template #default="scope">
                                <el-tooltip content="编辑" effect="dark" placement="top">
                                    <el-button type="primary" icon="Edit" @click="editNurseRecord(scope.row)" />
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
                <el-form-item label="客户姓名" :label-width="100" prop="customerId">
                    <el-select v-model="addForm.customerId" filterable placeholder="请选择客户">
                        <el-option v-for="item in customers" :key="item.id" :label="item.customerName" :value="item.id">
                            <span style="float: left">{{ item.customerName }}（{{ getSex(item.customerSex) }}，{{
                                item.customerAge
                                }}岁）</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">
                                所属楼房: {{ item.buildingNo }} | 房间号: {{ item.roomNo }} | 床号: {{ item.bedId }}
                            </span>
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="护理项目" :label-width="100" prop="itemId">
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
                    <el-date-picker v-model="addForm.nursingTime" type="datetime" placeholder="选择日期时间" />
                </el-form-item>
                <el-form-item label="护理内容" :label-width="100">
                    <el-input v-model="addForm.nursingContent" />
                </el-form-item>
                <el-form-item label="护理数量" :label-width="100" type="number">
                    <el-input-number v-model="addForm.nursingCount" />
                </el-form-item>
                <el-form-item label="护理人员" :label-width="100" prop="userId">
                    <el-select v-if="isManager" v-model="addForm.userId" filterable placeholder="请选择护理人员">
                        <el-option v-for="item in users" :key="item.id" :label="item.nickname" :value="item.id">
                            <span>{{ item.nickname }} | </span>
                            <span style="color: #8492a6; font-size: 13px"> {{ getRoleName(item.roleId) }} </span>
                        </el-option>
                    </el-select>
                    <el-input v-else :placeholder="userName" disabled />
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

        <!--修改护理记录的对话框  BEGIN-->
        <el-dialog v-model="editFormVisible" title="修改护理记录" width="700">
            <el-form :model="editForm" :rules="rules" ref="editFormRef">
                <el-form-item label="护理记录ID" :label-width="100" v-if="false">
                    <el-input v-model="editForm.id" disabled />
                </el-form-item>
                <el-form-item label="护理时间" :label-width="100">
                    <el-date-picker v-model="editForm.nursingTime" type="datetime" placeholder="选择日期时间" />
                </el-form-item>
                <el-form-item label="护理内容" :label-width="100">
                    <el-input v-model="editForm.nursingContent" />
                </el-form-item>
                <el-form-item label="护理数量" :label-width="100" type="number">
                    <el-input-number v-model="editForm.nursingCount" />
                </el-form-item>
                <el-form-item label="护理人员" :label-width="100" prop="userId">
                    <el-select v-if="isManager" v-model="editForm.userId" filterable placeholder="请选择护理人员">
                        <el-option v-for="item in users" :key="item.id" :label="item.nickname" :value="item.id">
                            <span>{{ item.nickname }} | </span>
                            <span style="color: #8492a6; font-size: 13px"> {{ getRoleName(item.roleId) }} </span>
                        </el-option>
                    </el-select>
                    <el-input v-else :placeholder="editForm.userName" disabled />
                </el-form-item>

            </el-form>
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="editFormVisible = false">取消</el-button>
                    <el-button type="primary" @click="submitEdit()">
                        确定
                    </el-button>
                </div>
            </template>
        </el-dialog>
        <!--添加护理记录的对话框  END-->

    </div>
</template>

<script setup name="NurseItem">
import { list as initData, deleteRecord, update, add } from "@/api/nurse/nurseRecords";
import customer from "../components/customer.vue";
import { list as getCustomers } from "@/api/customer/customer";
import { list as getItems } from "@/api/nurse/nurseItem";
import { list as getUsers } from "@/api/user/user";
import useUserStore from '@/store/modules/user';
import { useRoute, useRouter } from 'vue-router';

const { proxy } = getCurrentInstance();

const nurseRecordsList = ref([]);
const customersList = ref([]);
const loading = ref(true);
const total = ref(0);
const pageNum = ref(1);
const pageSize = ref(10);

let addFormVisible = ref(false);
let editFormVisible = ref(false);
const addFormRef = ref(null);
const editFormRef = ref(null);

const rules = ref({
    customerId: [
        { required: true, message: '请选择客户', trigger: 'blur' }
    ],
    itemId: [
        { required: true, message: '请选择护理项目', trigger: 'blur' }
    ],
    userId: [
        { required: true, message: '请选择护理人员', trigger: 'blur' }
    ]
});

const customers = ref([]);
const items = ref([]);
const users = ref([]);
const isManager = ref(false);
const userId = ref();
const userName = ref();
const route = useRoute();
const router = useRouter();
const getPushId = route.query.id;

let addForm = ref({
    customerId: '',
    itemId: '',
    nursingTime: '',
    nursingContent: '',
    nursingCount: 0,
    userId: ''
});
let editForm = ref({
    id: '',
    customerId: '',
    itemId: '',
    nursingTime: '',
    nursingContent: '',
    nursingCount: 0,
    userId: ''
});


const currentSelected = ref(-1);

// 当子组件触发事件时，更新currentSelected的值
const handleChildSelected = (res) => {
    if (res.customerId == null || res.customerId == undefined || res.customerId == -1 || isNaN(res.customerId)) {
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
        if (getPushId != null && getPushId != undefined) {
            customersList.value.getPushId = getPushId;
        }
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

/** 设置是否管理员 */
function setManager() {
    if (useUserStore().roles === 1) {
        isManager.value = true;
        // 查用户
        getUsers().then(response => {
            users.value = response.data;
        });
    } else {
        userId.value = useUserStore().userId;
        userName.value = useUserStore().name;
    }
}

/** 添加按钮操作 */
function addNurseRecord() {
    // 查客户
    getCustomers().then(response => {
        customers.value = response.data;
        // 查项目
        getItems().then(response => {
            items.value = response.data;
            if (!isManager.value) {
                addForm.value.userId = userId.value;
            }
        });
    });
    addFormVisible.value = true;
}

// 获取性别
function getSex(sex) {
    if (sex === 0) {
        return '男';
    } else if (sex === 1) {
        return '女';
    }
    return '';
}

// 获取角色名称
function getRoleName(roleId) {
    if (roleId === 1) {
        return '管理员';
    } else if (roleId === 2) {
        return '健康管家';
    }
    return '未知角色';
}

/** 提交添加项目 */
function submitAdd() {
    addFormRef.value.validate((valid) => {
        if (valid) {
            addFormVisible.value = false;
            add(addForm.value).then(response => {
                getList();
                addForm = ref({
                    customerId: '',
                    itemId: '',
                    nursingTime: '',
                    nursingContent: '',
                    nursingCount: 0,
                    userId: ''
                });
                proxy.$modal.msgSuccess("添加成功");
            })
                .catch(() => {
                    getList();
                    proxy.$modal.msgError("添加失败");
                });
        } else {
            proxy.$modal.msgError('必选项不能为空');
            return false;
        }
    });
}


/** 编辑按钮操作 */
function editNurseRecord(row) {
    editForm.value = {
        id: row.id,
        nursingTime: row.nursingTime,
        nursingContent: row.nursingContent,
        nursingCount: row.nursingCount,
        userName: row.userInfo[0].nickname
    };
    editFormVisible.value = true;
}

/** 提交编辑记录 */
function submitEdit() {
    editFormVisible.value = false;

    update(editForm.value).then(response => {
        getList();
        proxy.$modal.msgSuccess("编辑成功");
    })
        .catch(() => {
            getList();
            proxy.$modal.msgError("编辑失败");
        });

}

/** 删除按钮操作 */
function handleDelete(row) {
    proxy.$modal.confirm('是否删除该记录?').then(function () {
        return deleteRecord(row.id);
    }).then(() => {
        getList();
        proxy.$modal.msgSuccess("删除成功");
    }).catch(() => { });
}

getList();
setManager();
onMounted(() => {
    // 清除 query 参数
    const { path } = route;
    router.replace({ path });
});
</script>

<style scoped></style>