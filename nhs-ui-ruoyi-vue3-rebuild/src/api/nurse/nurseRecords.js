import request from '@/utils/request'

// 查询护理记录列表
export function list(query) {
    return request({
        url: '/nurse/nurseRecords/detail',
        method: 'get',
        params: query
    })
}

// 提交新增项目
export function add(data) {
    return request({
        url: '/nurse/nurseRecords/add',
        method: 'post',
        data: data
    })
}

// 提交修改内容
export function update(data) {
    return request({
        url: '/nurse/nurseRecords/update',
        method: 'post',
        data: data
    })
}

// 删除护理记录
export function deleteRecord(id) {
    return request({
        url: '/nurse/nurseRecords/delete',
        method: 'delete',
        params: { id }
    })
}
