import request from '@/utils/request'

// 查询客户列表
export function listById(query) {
    return request({
        url: '/customer/listById',
        method: 'get',
        params: query
    })
}

export function list(data) {
    return request({
        url: '/customer/list',
        method: 'get',
        params: data
    })
}
