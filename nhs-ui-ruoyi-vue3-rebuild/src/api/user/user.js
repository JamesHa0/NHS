import request from '@/utils/request'

// 查询用户列表
export function list(data) {
    return request({
        url: '/user/list',
        method: 'get',
        params: data
    })
}
