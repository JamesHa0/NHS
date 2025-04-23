import request from '@/utils/request'

// 查询客户列表
export function list(data) {
    return request({
        url: '/customer/listById',
        method: 'post',
        params: data
    })
}

