<template>
    <!--  查询 -->
    <div>
        <el-input placeholder="请输入客户姓名" style="width:200px" v-model="queryName"></el-input>
        <el-button @click="queryByName" type="success" plain>查询</el-button>
        <el-button @click="showCustomerDialog" type="primary"
            style="width: 100px; margin-left: 50px;font-weight: bolder; font-size: 16px">
            登&emsp;记
        </el-button>
    </div>
    <!--  显示所有客户信息 table自动循环-->
    <div>{{ errorMessage }}</div>
    <el-table :data="customerList">
        <el-table-column label="序号" type="index" width="60px"></el-table-column>
        <el-table-column prop="customerName" label="客户姓名" width="80px"></el-table-column>
        <el-table-column prop="customerSex" label="性别" width="80px">
            <template #default="scope">
                {{ scope.row.customerSex === 0 ? '男' : '女' }}
            </template>
        </el-table-column>
        <el-table-column prop="customerAge" label="年龄" width="80px"></el-table-column>
        <el-table-column prop="idcard" label="身份证号" width="220px"></el-table-column>
        <el-table-column prop="buildingNo" label="所属楼房" width="80px"></el-table-column>
        <el-table-column prop="checkinDate" label="入住日期" width="120px" :formatter="formatDate"></el-table-column>
        <el-table-column prop="expirationDate" label="合同到期时间" width="120px" :formatter="formatDate"></el-table-column>
        <el-table-column prop="contactTel" label="联系电话" width="120px"></el-table-column>
        <el-table-column prop="familyMember" label="家属" width="80px"></el-table-column>
        <el-table-column prop="attention" label="备注" width="120px"></el-table-column>
        <el-table-column label="操作">
            <el-button type="primary">修改</el-button>
            <el-button type="danger">删除</el-button>
        </el-table-column>
    </el-table>

    <!--  客户信息对话框 -->
    <el-dialog v-model="customerDialog">
        <el-form :model="curCustomer">
            <el-form-item label="客户姓名">
                <el-col :span="11">
                    <el-input v-model="curCustomer.customerName"></el-input>
                </el-col>
            </el-form-item>
            <el-form-item label="性&emsp;&emsp;别">
                <el-col :span="11">
                    <el-select v-model="curCustomer.customerSex">
                        <el-option v-for="item in sexOptions" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </el-col>
            </el-form-item>
            <el-form-item label="年&emsp;&emsp;龄">
                <el-col :span="11">
                    <el-input v-model="curCustomer.customerAge"></el-input>
                </el-col>
            </el-form-item>
            <el-form-item label="入住日期">
                <el-col :span="11">
                    <el-date-picker v-model="curCustomer.checkinDate"></el-date-picker>
                </el-col>
            </el-form-item>
        </el-form>
        <el-button @click="recordOne()">录入</el-button>
    </el-dialog>
</template>

<script setup>
import { ref, onMounted, reactive } from "vue";
import http from '@/axios/http';
// 存放用户数据集合
let customerList = ref([]);

//报错提示
let errorMessage = ref("")

//模糊查询绑定的 客户名称
let queryName = ref("")

let customerDialog = ref(false)

//录入新客户，或者编辑已有客户的 绑定的客户信息
let curCustomer = reactive({});

//性别数组
let sexOptions = ref([
    {
        label: '男',
        value: 0
    }, {
        label: '女',
        value: 1
    }
])

//根据客户姓名模糊查询客户信息
function queryByName() {

    let param = {
        customerName: queryName.value
    }

    http.post('/lyy/get_customers_by', param).then(
        (res) => {
            let result = res.data;
            // 返回结果传给 customerList
            if (result.result === 'success') {
                customerList.value = result.data;
            } else {
                errorMessage.value = result.error;
            }

        }
    )
}

//单击登录按钮事件
function showCustomerDialog() {
    //弹出客户信息对话框
    customerDialog.value = true;

}

//关闭客户信息对话框
function closeCustomerDialog() {
    customerDialog.value = false;
}

//页面初始化
onMounted(
    () => {
        init();
    }
)

function init() {
    //查询所有用户信息
    getAllCustomers();
}


function getAllCustomers() {
    http.post('/lyy/get_all_customers').then(
        (res) => {
            let result = res.data;
            // 返回结果传给 customerList
            if (result.result === 'success') {
                customerList.value = result.data;
            } else {
                errorMessage.value = result.error;
            }

        }
    )
}

//格式化日期
function formatDate(row, column) {
    let data = row[column.property];

    if (data == null) {
        return null;
    }

    let dt = new Date(data);
    return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate()
    //+ ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds()
}

function recordOne() {
    let param = curCustomer;
    http.post('/lyy/add_one_customer', param).then(
        (res) => {
            let result = res.data;
            // 返回结果传给 customerList
            if (result.result === 'success') {
                //关闭对话框
                closeCustomerDialog()

                //刷新客户列表
                getAllCustomers();
            } else {
                errorMessage.value = result.error;
            }

        }
    )
}

</script>

<style scoped></style>