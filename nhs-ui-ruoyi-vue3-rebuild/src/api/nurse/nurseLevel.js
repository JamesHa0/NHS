import request from '@/utils/request'

// 查询护理项目列表
export function list(query) {
    return request({
        url: '/nurse/nurseLevel/items',
        method: 'get',
        params: query
    })
}

// 提交新增项目
export function add(data) {
    return request({
        url: '/nurse/nurseLevel/add',
        method: 'post',
        data: data
    })
}

// 提交修改内容
export function update(data) {
    return request({
        url: '/nurse/nurseLevel/update',
        method: 'post',
        data: data
    })
}

// 删除护理项目
export function deleteItem(id) {
    return request({
        url: '/nurse/nurseLevel/delete',
        method: 'delete',
        params: { id }
    })
}
