import request from '@/utils/request'

// 查询客户列表（通过id数组）
// export function listById(query) {
//     return request({
//         url: '/customer/listById',
//         method: 'get',
//         params: query
//     })
// }

// 查询客户列表
export function list(data) {
    return request({
        url: '/customer/list',
        method: 'get',
        params: data
    })
}

// 通过用户ID查询客户列表
export function listByUserId(data) {
    return request({
        url: '/customer/listByUserId',
        method: 'get',
        params: data
    })
}

// 查询客户详细信息
export function listDetails(data) {
    return request({
        url: '/customer/detail',
        method: 'get',
        params: data
    })
}

// 提交修改客户护理级别
export function update(data) {
    return request({
        url: '/customer/update',
        method: 'post',
        data: data
    })
}

// 添加护理人员
export function addUser(data) {
    return request({
        url: '/customer/addUser',
        method: 'post',
        params: data
    })
}

// 删除护理人员
export function deleteUser(data) {
    return request({
        url: '/customer/deleteUser',
        method: 'delete',
        params: data
    })
}