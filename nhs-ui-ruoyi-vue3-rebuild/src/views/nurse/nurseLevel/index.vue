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
                                        @click="itemDelete(props.row, scope.row)">删除</el-button>
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
                    <el-tooltip content="编辑" effect="dark" placement="top">
                        <el-button type="primary" icon="Edit" @click="editNurseItem(scope.row)" />
                    </el-tooltip>
                    <el-tooltip content="删除" effect="dark" placement="top">
                        <el-button type="danger" icon="Delete" @click="handleDelete(scope.row)" />
                    </el-tooltip>
                    <el-tooltip content="添加护理内容" effect="dark" placement="top">
                        <el-button type="success" icon="Plus" @click="addNurseItem(scope.row)" />
                    </el-tooltip>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" v-model:page="pageNum" v-model:limit="pageSize" />


        <!--添加护理级别的对话框  BEGIN-->
        <el-dialog v-model="addFormVisible" title="添加护理级别" width="500">
            <el-form :model="addForm" :rules="rules" ref="addFormRef">
                <el-form-item label="护理级别" :label-width="100" prop="levelName">
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
            <el-form :model="editForm" :rules="rules" ref="editFormRef">
                <el-form-item label="护理级别ID" :label-width="100" v-if="false">
                    <el-input v-model="editForm.id" disabled />
                </el-form-item>
                <el-form-item label="护理级别" :label-width="100" prop="levelName">
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
        <el-dialog v-model="addItemFormVisible" title="添加护理内容" width="800">
            <el-form :model="addItemForm" :rules="rules" ref="addItemFormRef">
                <el-form-item label="护理级别ID" :label-width="100" v-if="false">
                    <el-input v-model="addItemForm.id" disabled />
                </el-form-item>
                <el-form-item label="护理级别" :label-width="100">
                    <el-input v-model="addItemForm.levelName" disabled />
                </el-form-item>

                <el-form-item label="护理项目" :label-width="100" prop="nurseItemIds">
                    <el-select v-model="addItemForm.nurseItemIds" multiple filterable placeholder="请选择护理项目">
                        <el-option v-for="item in nurseItemForm" :key="item.id" :label="item.nursingName"
                            :value="item.id">
                            <span style="float: left">{{ item.serialNumber }} | {{ item.nursingName }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">
                                价格: {{ item.servicePrice }} | 执行周期: {{ item.executionCycle }} | 执行次数: {{
                                    item.executionTimes }}
                            </span>
                        </el-option>
                    </el-select>
                </el-form-item>

            </el-form>
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="addItemFormVisible = false">取消</el-button>
                    <el-button type="primary" @click="submitAddItem()">
                        确定
                    </el-button>
                </div>
            </template>
        </el-dialog>
        <!--添加护理内容的对话框  END-->


    </div>
</template>

<script setup name="NurseItem">
import { details as initData, deleteItem, update, add, addItems, deleteLevelItem } from "@/api/nurse/nurseLevel";
import { list as listNurseItem } from "@/api/nurse/nurseItem"

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
let editForm = ref({
    id: '',
    levelName: '',
    message: ''
});
let addItemForm = ref({
    id: '',
    levelName: '',
    nurseItemIds: []
});
let nurseItemForm = ref([]);

const statusOptions = ref([
    { id: 1, statusName: "已启用" },
    { id: 2, statusName: "已停用" }
])

let queryParams = ref({
    levelName: undefined
});
let queryItemParams = ref({
    id: undefined
});

const rules = ref({
    levelName: [
        { required: true, message: '请输入护理级别', trigger: 'blur' }
    ],
    nurseItemIds: [
        { required: true, message: '请选择护理项目', trigger: 'blur' }
    ]
});
const addFormRef = ref(null);
const editFormRef = ref(null);
const addItemFormRef = ref(null);

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
    addFormRef.value.validate((valid) => {
        if (valid) {
            addFormVisible.value = false;
            add(addForm.value).then(response => {
                getList();
                addForm = ref({
                    levelName: '',
                    message: ''
                });
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
            proxy.$modal.msgError('护理级别不能为空');
            return false;
        }
    });
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
    getNurseItemList(row.id);
    addItemFormVisible.value = true;
}

/** 获取未添加的护理项目列表 */
function getNurseItemList(id) {
    proxy.resetForm("queryRef");
    queryItemParams.value = {
        id: id
    };
    initData(queryItemParams.value).then(response => {
        const itemsById = response.data[0].nurseContents || [];

        listNurseItem().then(listResponse => {
            const allItems = listResponse.data || [];

            // 将两个数组的 id 分别存入 Set
            const idSetById = new Set(itemsById.map(item => item.id));
            const idSetAll = new Set(allItems.map(item => item.id));

            // 找出两个数组中不重复的 id 集合
            const uniqueIds = new Set([
                ...[...idSetById].filter(id => !idSetAll.has(id)),
                ...[...idSetAll].filter(id => !idSetById.has(id))
            ]);

            // 根据不重复的 id 集合筛选出对应的项目
            const finalItems = [...itemsById, ...allItems].filter(item => uniqueIds.has(item.id));

            nurseItemForm.value = finalItems;
        })
    })
    queryItemParams.value = {
        id: undefined
    };
}



/** 提交添加护理内容 */
function submitAddItem() {
    addItemFormRef.value.validate((valid) => {
        if (valid) {
            addItemFormVisible.value = false;
            const data = {
                levelId: addItemForm.id,
                nurseItemIds: addItemForm.nurseItemIds.join(',')
            }
            addItems(data).then(response => {
                getList();
                proxy.$modal.msgSuccess("添加项目成功");
            })
                .catch(() => {
                    getList();
                    proxy.$modal.msgError("添加项目失败");
                });
            proxy.$modal.msgSuccess("添加成功");
        } else {
            proxy.$modal.msgError('护理项目不能为空');
            return false;
        }
    });
}


/** 删除级别下的项目 */
function itemDelete(prop, row) {
    const data = {
        levelId: prop.id,
        itemId: row.id
    }
    proxy.$modal.confirm('是否删除"' + prop.levelName + '"下，名称为"' + row.nursingName + '"的护理项目?').then(function () {
        return deleteLevelItem(data);
    }).then(() => {
        getList();
        proxy.$modal.msgSuccess("删除成功");
    }).catch(() => {
        getList();
        proxy.$modal.msgError("未删除");
    });

}

getList();
</script>