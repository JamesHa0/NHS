<template>
    <div class="app-container">
        <el-row :gutter="10">
            <el-col :span="8">
                <user @selectOne="handleChildSelected" />
            </el-col>
            <el-col :span="16">
                <el-form :inline="true" @submit.native.prevent>
                    <el-form-item label="服务对象列表">
                        <el-button type="primary" icon="Plus" @click="addServiceCustomer">添加</el-button>
                    </el-form-item>

                    <el-table v-loading="loading"
                        :data="customerList.slice((pageNum - 1) * pageSize, pageNum * pageSize)" style="width: 100%;">
                        <el-table-column label="序号" width="50" type="index" align="center">
                            <template #default="scope">
                                <span>{{ (pageNum - 1) * pageSize + scope.$index + 1 }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="ID" align="center" prop="id" :show-overflow-tooltip="true" v-if=false />
                        <el-table-column label="客户姓名" align="center" prop="customerName"
                            :show-overflow-tooltip="true" />
                        <el-table-column label="性别" align="center" prop="customerSex" :show-overflow-tooltip="true">
                            <template #default="scope">
                                <span v-if="scope.row.customerSex == 0">男</span>
                                <span v-else-if="scope.row.customerSex == 1">女</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="年龄" align="center" prop="customerAge" :show-overflow-tooltip="true" />
                        <el-table-column label="护理级别" align="center" prop="levelName" :show-overflow-tooltip="true" />
                        <el-table-column label="护理人员" align="center" :show-overflow-tooltip="true">
                            <template #default="scope">
                                <span v-if="scope.row.nickName">{{ scope.row.nickName }}</span>
                                <span v-else>无</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="115">
                            <template #default="scope">
                                <el-tooltip content="删除" effect="dark" placement="top" v-if="scope.row.userId > 0">
                                    <el-button type="danger" icon="Delete" @click="handleDelete(scope.row)" />
                                </el-tooltip>
                            </template>
                        </el-table-column>
                    </el-table>

                    <pagination v-show="total > 0" :total="total" v-model:page="pageNum" v-model:limit="pageSize" />

                </el-form>
            </el-col>
        </el-row>


        <!--添加服务对象的对话框  BEGIN-->
        <el-dialog v-model="addFormVisible" title="添加服务对象" width="800">
            <el-form :model="addForm" :rules="rules" ref="addFormRef">
                <el-form-item label="用户ID" :label-width="100" v-if="false">
                    <el-input v-model="addForm.id" disabled />
                </el-form-item>

                <el-form-item label="服务对象" :label-width="100" prop="customerIds">
                    <el-select v-model="addForm.customerIds" multiple filterable placeholder="请选择客户">
                        <el-option v-for="item in customers" :key="item.id" :label="item.customerName" :value="item.id">
                            <span style="float: left">{{ item.customerName }}（{{ getSex(item.customerSex) }}，{{
                                item.customerAge
                                }}岁）</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">
                                护理级别: {{ item.levelName }}
                            </span>
                        </el-option>
                    </el-select>
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
        <!--添加服务对象的对话框  END-->

    </div>
</template>

<script setup name="NurseItem">
import user from "../components/user.vue";
import { listByUserId as initData, addUser, deleteUser } from "@/api/customer/customer";

const { proxy } = getCurrentInstance();

const customers = ref([]);
const customerList = ref([]);
const loading = ref(true);
const total = ref(0);
const pageNum = ref(1);
const pageSize = ref(10);

let addForm = ref({
    id: '',
    customerIds: []
});
let editForm = ref({});

let addFormVisible = ref(false);
let editFormVisible = ref(false);

const addFormRef = ref(null);
const editFormRef = ref(null);

const rules = ref({
    customerIds: [
        { required: true, message: '请选择客户', trigger: 'blur' }
    ]
});

const currentSelected = ref(-2);

// 当子组件触发事件时，更新currentSelected的值
const handleChildSelected = (res) => {
    if (res.userId == null || res.userId == undefined || isNaN(res.userId)) {
        currentSelected.value = -2;
    } else {
        currentSelected.value = res.userId;
    }
    getList();
}


/** 查询护理记录列表 */
function getList() {
    loading.value = true;
    initData({ userId: currentSelected.value }).then(response => {
        customerList.value = response.data;
        total.value = customerList.value.length;
        loading.value = false;
    });
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


/** 添加按钮操作 */
function addServiceCustomer() {
    if (currentSelected.value === -2) {
        proxy.$modal.msgError("请选择左侧用户");
    } else {
        getLeftCostomers();
        addForm.value.id = currentSelected.value;
        addFormVisible.value = true;
    }
}

/** 获取未负责的客户列表 */
function getLeftCostomers() {
    // 获取没有用户负责的客户列表
    initData({ userId: -1 }).then(response => {
        customers.value = response.data;
    });
}

/** 提交添加项目 */
function submitAdd() {
    addFormRef.value.validate((valid) => {
        if (valid) {
            addFormVisible.value = false;
            const data = {
                userId: addForm.value.id,
                customerIds: addForm.value.customerIds.join(',')
            }
            addUser(data).then(response => {
                getList();
                addForm = ref({
                    id: '',
                    customerIds: []
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


/** 删除按钮操作 */
function handleDelete(row) {
    console.log(row.id);

    proxy.$modal.confirm('是否要终止 *' + row.nickName + '* 对 *' + row.customerName + '* 的服务？').then(function () {
        return deleteUser({ customerId: row.id });
    }).then(() => {
        getList();
        proxy.$modal.msgSuccess("已取消 *' + row.nickName + '* 对 *' + row.customerName + '* 的服务");
    }).catch(() => { });
}

getList();

</script>

<style scoped></style>