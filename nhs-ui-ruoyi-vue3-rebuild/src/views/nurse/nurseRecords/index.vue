<template>
    <div class="app-container">
        <el-row :gutter="15">
            <el-col :span="8">
                <customers :customersList="customersList" />
            </el-col>
            <el-col :span="16">
                2
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

let addFormVisible = ref(false);
let editFormVisible = ref(false);

let addForm = ref({
    serialNumber: '',
    nursingName: '',
    servicePrice: '',
    message: '',
    status: '',
    executionCycle: '',
    executionTimes: ''
});
let beforeEditForm = ref({
    id: '',
    serialNumber: '',
    nursingName: '',
    servicePrice: '',
    message: '',
    status: '',
    executionCycle: '',
    executionTimes: ''
});
let editForm = ref({
    id: '',
    serialNumber: '',
    nursingName: '',
    servicePrice: '',
    message: '',
    status: '',
    executionCycle: '',
    executionTimes: ''
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


/** 查询护理记录列表 */
function getList() {
    loading.value = true;
    initData(queryParams.value).then(response => {
        nurseRecordsList.value = response.data;
        total.value = response.data.length;

        // 客户信息子组件赋值
        const customerInfoArray = response.data.flatMap(item => item.customerInfo);
        const uniqueCustomerInfo = Array.from(new Set(customerInfoArray.map(JSON.stringify)), JSON.parse);
        customersList.value = uniqueCustomerInfo;

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


getList();
</script>