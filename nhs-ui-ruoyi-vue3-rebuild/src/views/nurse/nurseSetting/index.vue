<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryRef" :inline="true" @submit.native.prevent>
            <el-form-item label="客户列表" prop="customerName">
                <el-input v-model="queryParams.customerName" placeholder="请输入要查询的客户姓名" clearable style="width: 200px"
                    @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
                <el-button icon="Refresh" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>
        <el-table v-loading="loading" :data="customerList.slice((pageNum - 1) * pageSize, pageNum * pageSize)"
            style="width: 100%;">
            <el-table-column label="序号" width="50" type="index" align="center">
                <template #default="scope">
                    <span>{{ (pageNum - 1) * pageSize + scope.$index + 1 }}</span>
                </template>
            </el-table-column>
            <el-table-column label="ID" align="center" prop="id" :show-overflow-tooltip="true" v-if=false />
            <el-table-column label="姓名" align="center" prop="customerName" :show-overflow-tooltip="true" width="70" />
            <el-table-column label="性别" align="center" prop="customerSex" :show-overflow-tooltip="true" width="50">
                <template #default="scope">
                    <span v-if="scope.row.customerSex == 0">男</span>
                    <span v-else-if="scope.row.customerSex == 1">女</span>
                </template>
            </el-table-column>
            <el-table-column label="年龄" align="center" prop="customerAge" :show-overflow-tooltip="true" width="50" />
            <el-table-column label="联系电话" align="center" prop="contactTel" :show-overflow-tooltip="true" width="80" />
            <el-table-column label="楼房床号" align="center" prop="contactTel" :show-overflow-tooltip="true" width="125">
                <template #default="scope">
                    <span>{{ scope.row.buildingNo }}楼{{ scope.row.roomNo }} - {{ scope.row.bedId }}</span>
                </template>
            </el-table-column>
            <el-table-column label="入住时间" align="center" :show-overflow-tooltip="true" width="115">
                <template #default="scope">
                    <span>{{ parseTime(scope.row.checkinDate) }}</span>
                </template>
            </el-table-column>
            <el-table-column label="合同到期时间" align="center" :show-overflow-tooltip="true" width="115">
                <template #default="scope">
                    {{ parseTime(scope.row.expirationDate) }}
                </template>
            </el-table-column>
            <el-table-column label="身心状况" align="center" prop="psychosomaticState" :show-overflow-tooltip="true"
                width="80" />
            <el-table-column label="注意事项" align="center" prop="attention" :show-overflow-tooltip="true" />
            <el-table-column label="护理级别" align="center" prop="levelName" :show-overflow-tooltip="true" width="80" />
            <el-table-column label="家属" align="center" prop="familyMember" :show-overflow-tooltip="true" width="70" />
            <el-table-column label="健康管家" align="center" :show-overflow-tooltip="true" width="80">
                <template #default="scope">
                    <span v-if="scope.row.nickName">{{ scope.row.nickName }}</span>
                    <span v-else>无</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="115">
                <template #default="scope">
                    <el-tooltip content="查看护理记录" effect="dark" placement="top">
                        <el-button type="primary" icon="View" @click="toNurseRecord(scope.row)" />
                    </el-tooltip>
                    <el-tooltip content="更改护理级别" effect="dark" placement="top">
                        <el-button type="success" icon="Operation" @click="handleChange(scope.row)" />
                    </el-tooltip>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" v-model:page="pageNum" v-model:limit="pageSize" />

        <!--更改客户护理级别的对话框  BEGIN-->
        <el-dialog v-model="editFormVisible" title="客户护理级别" width="400">
            <el-form :model="editForm">
                <el-form-item label="客户ID" :label-width="100" v-if="false">
                    <el-input v-model="editForm.id" disabled />
                </el-form-item>
                <el-form-item label="客户姓名" :label-width="100" prop="customerName">
                    <el-input v-model="editForm.customerName" disabled />
                </el-form-item>
                <el-form-item label="护理级别" :label-width="100" prop="levelId">
                    <el-select v-model="editForm.levelId" filterable placeholder="请选择护理级别">
                        <el-option v-for="item in levels" :key="item.id" :label="item.levelName" :value="item.id">
                            <span style="float: left">{{ item.levelName }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">
                                <el-tag v-if="item.levelStatus == 1" type="success">已启用</el-tag>
                                <el-tag v-else-if="item.levelStatus == 2" type="danger">已停用</el-tag>
                            </span>
                        </el-option>
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
        <!--编辑项目信息的对话框  END-->
    </div>
</template>

<script setup name="NurseItem">
import { listDetails as initData, update } from "@/api/customer/customer";
import { list as getLevels } from "@/api/nurse/nurseLevel";
import { useRouter } from 'vue-router';

const { proxy } = getCurrentInstance();

const customerList = ref([]);
const loading = ref(true);
const total = ref(0);
const pageNum = ref(1);
const pageSize = ref(10);
const router = useRouter();

let editFormVisible = ref(false);

let levels = ref([]);

let editForm = ref({
    id: '',
    customerName: '',
    levelId: ''
});

let queryParams = ref({
    customerName: undefined
});


/** 查询客户列表 */
function getList() {
    loading.value = true;
    initData(queryParams.value).then(response => {
        customerList.value = response.data;
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

/** 查看护理记录按钮操作 */
function toNurseRecord(row) {
    let id = row.id;
    router.push({ name: 'nurseRecords', query: { id } });
}


/** 更改按钮操作 */
function handleChange(row) {
    editForm.value = row;
    getLevels().then(response => {
        levels.value = response.data;
    });
    editFormVisible.value = true;
}

/** 提交更改 */
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

getList();
</script>