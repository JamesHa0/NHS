<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryRef" :inline="true" @submit.native.prevent>
            <el-form-item label="项目名称" prop="nursingName">
                <el-input v-model="queryParams.nursingName" placeholder="请输入要查询的项目名称" clearable style="width: 200px"
                    @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
                <el-button icon="Refresh" @click="resetQuery">重置</el-button>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="Plus" @click="addNurseItem">添加</el-button>
            </el-form-item>
        </el-form>
        <el-table v-loading="loading" :data="nurseItemList.slice((pageNum - 1) * pageSize, pageNum * pageSize)"
            style="width: 100%;">
            <el-table-column label="序号" width="50" type="index" align="center">
                <template #default="scope">
                    <span>{{ (pageNum - 1) * pageSize + scope.$index + 1 }}</span>
                </template>
            </el-table-column>
            <el-table-column label="ID" align="center" prop="id" :show-overflow-tooltip="true" v-if=false />
            <el-table-column label="编号" align="center" prop="serialNumber" :show-overflow-tooltip="true" />
            <el-table-column label="名称" align="center" prop="nursingName" :show-overflow-tooltip="true" />
            <el-table-column label="价格" align="center" prop="servicePrice" :show-overflow-tooltip="true" />
            <el-table-column label="描述" align="center" prop="message" :show-overflow-tooltip="true" />
            <el-table-column label="状态" align="center" prop="status" :show-overflow-tooltip="true">
                <template #default="scope">
                    <el-tag v-if="scope.row.status == 1" type="success">已启用</el-tag>
                    <el-tag v-else-if="scope.row.status == 2" type="danger">已停用</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="执行周期" align="center" prop="executionCycle" :show-overflow-tooltip="true" />
            <el-table-column label="执行次数" align="center" prop="executionTimes" :show-overflow-tooltip="true" />
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template #default="scope">
                    <el-button link type="primary" icon="edit" @click="editNurseItem(scope.row)">编辑</el-button>
                    <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" v-model:page="pageNum" v-model:limit="pageSize" />


        <!--添加项目信息的对话框  BEGIN-->
        <el-dialog v-model="addFormVisible" title="添加护理项目" width="500">
            <el-form :model="addForm" :rules="rules" ref="addFormRef">
                <el-form-item label="项目编号" :label-width="100" prop="serialNumber">
                    <el-input v-model="addForm.serialNumber" />
                </el-form-item>
                <el-form-item label="名称" :label-width="100" prop="nursingName">
                    <el-input v-model="addForm.nursingName" />
                </el-form-item>
                <el-form-item label="价格" :label-width="100" prop="servicePrice">
                    <el-input v-model="addForm.servicePrice" />
                </el-form-item>
                <el-form-item label="描述" :label-width="100">
                    <el-input v-model="addForm.message" />
                </el-form-item>
                <el-form-item label="状态" :label-width="100">
                    <el-select v-model="addForm.status">
                        <el-option v-for="item in statusOptions" :key="item.id" :label="item.statusName"
                            :value="item.id" />
                    </el-select>
                </el-form-item>
                <el-form-item label="执行周期" :label-width="100">
                    <el-input v-model="addForm.executionCycle" />
                </el-form-item>
                <el-form-item label="执行次数" :label-width="100" type="number">
                    <el-input-number v-model="addForm.executionTimes" />
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
        <!--添加项目信息的对话框  END-->


        <!--编辑项目信息的对话框  BEGIN-->
        <el-dialog v-model="editFormVisible" title="护理项目信息" width="500">
            <el-form :model="editForm" :rules="rules" ref="editFormRef">
                <el-form-item label="项目ID" :label-width="100" v-if="false">
                    <el-input v-model="editForm.id" disabled />
                </el-form-item>
                <el-form-item label="项目编号" :label-width="100" prop="serialNumber">
                    <el-input v-model="editForm.serialNumber" />
                </el-form-item>
                <el-form-item label="名称" :label-width="100" prop="nursingName">
                    <el-input v-model="editForm.nursingName" />
                </el-form-item>
                <el-form-item label="价格" :label-width="100" prop="servicePrice">
                    <el-input v-model="editForm.servicePrice" />
                </el-form-item>
                <el-form-item label="描述" :label-width="100">
                    <el-input v-model="editForm.message" />
                </el-form-item>
                <el-form-item label="状态" :label-width="100">
                    <el-select v-model="editForm.status">
                        <el-option v-for="item in statusOptions" :key="item.id" :label="item.statusName"
                            :value="item.id" />
                    </el-select>
                </el-form-item>
                <el-form-item label="执行周期" :label-width="100">
                    <el-input v-model="editForm.executionCycle" />
                </el-form-item>
                <el-form-item label="执行次数" :label-width="100">
                    <el-input-number v-model="editForm.executionTimes" />
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
        <!--编辑项目信息的对话框  END-->
    </div>
</template>

<script setup name="NurseItem">
import { list as initData, deleteItem, update, add } from "@/api/nurse/nurseItem";

const { proxy } = getCurrentInstance();

const nurseItemList = ref([]);
const loading = ref(true);
const total = ref(0);
const pageNum = ref(1);
const pageSize = ref(10);

let addFormVisible = ref(false);
let editFormVisible = ref(false);

let addForm = ref({
    serialNumber: '',
    nursingName: '',
    servicePrice: '',
    message: '',
    status: '',
    executionCycle: '',
    executionTimes: 0
});
let editForm = ref({
    id: '',
    serialNumber: '',
    nursingName: '',
    servicePrice: '',
    message: '',
    status: '',
    executionCycle: '',
    executionTimes: 0
});
const statusOptions = ref([
    { id: 1, statusName: "已启用" },
    { id: 2, statusName: "已停用" }
])

let queryParams = ref({
    nursingName: undefined
});

const rules = ref({
    serialNumber: [
        { required: true, message: '项目编号不能为空', trigger: 'blur' }
    ],
    nursingName: [
        { required: true, message: '名称不能为空', trigger: 'blur' }
    ],
    servicePrice: [
        { required: true, message: '价格不能为空', trigger: 'blur' }
    ],
});
const addFormRef = ref(null);
const editFormRef = ref(null);


/** 查询护理项目列表 */
function getList() {
    loading.value = true;
    initData(queryParams.value).then(response => {
        nurseItemList.value = response.data;
        total.value = response.data.length;
        loading.value = false;
    });
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

/** 提交添加项目 */
function submitAdd() {
    addFormRef.value.validate((valid) => {
        if (valid) {
            addFormVisible.value = false;
            add(addForm.value).then(response => {
                getList();
                addForm = ref({
                    serialNumber: '',
                    nursingName: '',
                    servicePrice: '',
                    message: '',
                    status: '',
                    executionCycle: '',
                    executionTimes: 0
                });
                proxy.$modal.msgSuccess("添加成功");
            })
                .catch(() => {
                    getList();
                    proxy.$modal.msgError("添加失败");
                });
        } else {
            proxy.$modal.msgError('必填项不能为空');
            return false;
        }
    });
}


/** 编辑按钮操作 */
function editNurseItem(row) {
    editForm = row;
    editFormVisible.value = true;
}

/** 提交编辑 */
function submitEdit() {
    editFormRef.value.validate((valid) => {
        if (valid) {
            editFormVisible.value = false;
            update(editForm.value).then(response => {
                getList();
                proxy.$modal.msgSuccess("编辑成功");
            })
                .catch(() => {
                    getList();
                    proxy.$modal.msgError("编辑失败");
                });
        } else {
            proxy.$modal.msgError('必填项不能为空');
            return false;
        }
    });
}


/** 删除按钮操作 */
function handleDelete(row) {
    proxy.$modal.confirm('是否删除名称为"' + row.nursingName + '"的项目?').then(function () {
        return deleteItem(row.id);
    }).then(() => {
        getList();
        proxy.$modal.msgSuccess("删除成功");
    }).catch(() => { });
}

getList();
</script>