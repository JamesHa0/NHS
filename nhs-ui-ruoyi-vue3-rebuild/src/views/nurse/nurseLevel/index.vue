<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" @submit.native.prevent>
            <el-form-item label="护理级别" prop="levelName">
                <el-input v-model="queryParams.levelName" placeholder="请输入要查询的护理级别" clearable style="width: 200px"
                    @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
                <el-button icon="Refresh" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">

            <el-col :span="3">
                <el-button type="info" plain icon="Sort" @click="toggleExpandAll">展开/折叠</el-button>
            </el-col>
            <el-col :span="3">
                <el-button type="primary" icon="Plus" @click="addNurseLevel">新的护理级别</el-button>
            </el-col>
            <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>


        <el-table v-if="refreshTable" v-loading="loading"
            :data="nurseLevelList.slice((pageNum - 1) * pageSize, pageNum * pageSize)" style="width: 100%;"
            :default-expand-all="isExpandAll">

            <el-table-column type="expand" lazy>
                <template #default="props">
                    <div>
                        <el-table :data="props.row.nurseContents" :border="childBorder">
                            <el-table-column label="序号" width="50" type="index" align="center">
                                <template #default="scope">
                                    <span>{{ scope.$index + 1 }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column label="ID" align="center" prop="id" :show-overflow-tooltip="true"
                                v-if=false />
                            <el-table-column label="编号" align="center" prop="serialNumber"
                                :show-overflow-tooltip="true" />
                            <el-table-column label="名称" align="center" prop="nursingName"
                                :show-overflow-tooltip="true" />
                            <el-table-column label="价格" align="center" prop="servicePrice"
                                :show-overflow-tooltip="true" />
                            <el-table-column label="描述" align="center" prop="message" :show-overflow-tooltip="true" />
                            <el-table-column label="状态" align="center" prop="status" :show-overflow-tooltip="true">
                                <template #default="scope">
                                    <el-tag v-if="scope.row.status == 1" type="success">已启用</el-tag>
                                    <el-tag v-else-if="scope.row.status == 2" type="danger">已停用</el-tag>
                                </template>
                            </el-table-column>
                            <el-table-column label="执行周期" align="center" prop="executionCycle"
                                :show-overflow-tooltip="true" />
                            <el-table-column label="执行次数" align="center" prop="executionTimes"
                                :show-overflow-tooltip="true" />
                            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                                <template #default="scope">
                                    <el-button link type="primary" icon="Delete"
                                        @click="handleDelete(scope.row)">删除</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-divider />
                        <el-divider />
                    </div>
                </template>
            </el-table-column>


            <el-table-column label="序号" width="50" type="index" align="center">
                <template #default="scope">
                    <span>{{ (pageNum - 1) * pageSize + scope.$index + 1 }}</span>
                </template>
            </el-table-column>
            <el-table-column label="ID" align="center" prop="id" :show-overflow-tooltip="true" v-if=false />
            <el-table-column label="护理级别" align="center" prop="levelName" :show-overflow-tooltip="true" />
            <el-table-column label="状态" align="center" prop="levelStatus" :show-overflow-tooltip="true">
                <template #default="scope">
                    <el-tag v-if="scope.row.levelStatus == 1" type="success">已启用</el-tag>
                    <el-tag v-else-if="scope.row.levelStatus == 2" type="danger">已停用</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template #default="scope">
                    <el-button type="primary" icon="Edit" @click="editNurseItem(scope.row)">编辑</el-button>
                    <el-button type="danger" icon="Delete" @click="handleDelete(scope.row)">删除</el-button>
                    <el-button type="success" icon="Plus" @click="addNurseItem(scope.row)">添加护理内容</el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" v-model:page="pageNum" v-model:limit="pageSize" />


        <!--添加护理级别的对话框  BEGIN-->
        <el-dialog v-model="addFormVisible" title="添加护理级别" width="500">
            <el-form :model="addForm">
                <el-form-item label="护理级别" :label-width="100">
                    <el-input v-model="addForm.levelName" />
                </el-form-item>
                <el-form-item label="状态" :label-width="100">
                    <el-select v-model="addForm.levelStatus">
                        <el-option v-for="item in statusOptions" :key="item.id" :label="item.statusName"
                            :value="item.id" />
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
        <!--添加护理级别的对话框  END-->


        <!--编辑护理级别的对话框  BEGIN-->
        <el-dialog v-model="editFormVisible" title="护理级别信息" width="500">
            <el-form :model="editForm">
                <el-form-item label="护理级别ID" :label-width="100">
                    <el-input v-model="editForm.id" disabled />
                </el-form-item>
                <el-form-item label="护理级别" :label-width="100">
                    <el-input v-model="editForm.levelName" />
                </el-form-item>
                <el-form-item label="状态" :label-width="100">
                    <el-select v-model="editForm.levelStatus">
                        <el-option v-for="item in statusOptions" :key="item.id" :label="item.statusName"
                            :value="item.id" />
                    </el-select>
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
        <!--编辑护理级别的对话框  END-->


        <!--添加护理内容的对话框  BEGIN-->
        <el-dialog v-model="addItemFormVisible" title="添加护理内容" width="500">
            <el-form :model="addItemForm">
                <el-form-item label="护理级别ID" :label-width="100">
                    <el-input v-model="addItemForm.id" disabled />
                </el-form-item>
                <el-form-item label="护理级别" :label-width="100">
                    <el-input v-model="addItemForm.levelName" disabled />
                </el-form-item>


            </el-form>
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="addItemFormVisible = false">取消</el-button>
                    <el-button type="primary" @click="submitAdd()">
                        确定
                    </el-button>
                </div>
            </template>
        </el-dialog>
        <!--添加护理内容的对话框  END-->


    </div>
</template>

<script setup name="NurseItem">
import { list as initData, deleteItem, update, add } from "@/api/nurse/nurseLevel";

const { proxy } = getCurrentInstance();

const nurseLevelList = ref([]);
const loading = ref(true);
const total = ref(0);
const pageNum = ref(1);
const pageSize = ref(10);
const showSearch = ref(true);
const isExpandAll = ref(false);
const refreshTable = ref(true);

let addFormVisible = ref(false);
let editFormVisible = ref(false);
let addItemFormVisible = ref(false);

let addForm = ref({
    levelName: '',
    message: ''
});
let beforeEditForm = ref({
    id: '',
    levelName: '',
    message: ''
});
let editForm = ref({
    id: '',
    levelName: '',
    message: ''
});
let addItemForm = ref({
    id: '',
    levelName: '',
    message: ''
});
const statusOptions = ref([
    { id: 1, statusName: "已启用" },
    { id: 2, statusName: "已停用" }
])

let queryParams = ref({
    nursingName: undefined
});

/** 查询护理级别列表 */
function getList() {
    loading.value = true;
    initData(queryParams.value).then(response => {
        nurseLevelList.value = response.data;
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
function addNurseLevel() {
    addFormVisible.value = true;
}

/** 提交添加项目 */
function submitAdd() {
    addFormVisible.value = false;
    add(addForm.value).then(response => {
        getList();
        proxy.$modal.msgSuccess("添加成功");
    })
        .catch(() => {
            getList();
            proxy.$modal.msgError("添加失败");
        });
}


/** 展开/折叠操作 */
function toggleExpandAll() {
    refreshTable.value = false;
    isExpandAll.value = !isExpandAll.value;
    nextTick(() => {
        refreshTable.value = true;
    });
}


/** 编辑按钮操作 */
function editNurseItem(row) {
    beforeEditForm = row;
    editForm = row;
    editFormVisible.value = true;
}

/** 提交编辑 */
function submitEdit() {
    update(editForm).then(response => {
        getList();
        proxy.$modal.msgSuccess("编辑成功");
        editFormVisible.value = false;
    })
        .catch(() => {
            getList();
            proxy.$modal.msgError("编辑失败");
            editFormVisible.value = false;
        });
    getList();
}

/** 删除按钮操作 */
function handleDelete(row) {
    proxy.$modal.confirm('是否删除名称为"' + row.levelName + '"的护理级别?').then(function () {
        return deleteItem(row.id);
    }).then(() => {
        getList();
        proxy.$modal.msgSuccess("删除成功");
    }).catch(() => { });
}


/** 添加护理内容按钮操作 */
function addNurseItem(row) {
    addItemForm = row;
    addItemFormVisible.value = true;
}

getList();
</script>