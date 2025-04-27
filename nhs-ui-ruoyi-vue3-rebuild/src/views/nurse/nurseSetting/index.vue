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
                    {{ formatDate(scope.row.checkinDate) }}
                </template>
            </el-table-column>
            <el-table-column label="合同到期时间" align="center" :show-overflow-tooltip="true" width="115">
                <template #default="scope">
                    {{ formatDate(scope.row.expirationDate) }}
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
                        <el-button type="success" icon="Operation" @click="addNurseItem(scope.row)" />
                    </el-tooltip>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" v-model:page="pageNum" v-model:limit="pageSize" />


        <!--添加项目信息的对话框  BEGIN-->
        <el-dialog v-model="addFormVisible" title="添加客户项目" width="500">
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
        <el-dialog v-model="editFormVisible" title="客户项目信息" width="500">
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
import { listDetails as initData } from "@/api/customer/customer";
import { useRouter } from 'vue-router';

const { proxy } = getCurrentInstance();

const customerList = ref([]);
const loading = ref(true);
const total = ref(0);
const pageNum = ref(1);
const pageSize = ref(10);
const router = useRouter();

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
    customerName: undefined
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


// 格式化时间戳
function formatDate(timestamp) {
    const date = new Date(timestamp);
    date.setHours(date.getHours() - 8); // 手动减去8小时
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');

    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}


/** 查询客户列表 */
function getList() {
    loading.value = true;
    initData(queryParams.value).then(response => {
        console.log(response);

        customerList.value = response.data;
        console.log(customerList.value)
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

getList();
</script>