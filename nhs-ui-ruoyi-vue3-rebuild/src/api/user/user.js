import request from '@/utils/request'

// 查询用户列表
export function list(data) {
    return request({
        url: '/user/list',
        method: 'get',
        params: data
    })
}


// 根据id查用户信息
export function listById(data) {
    return request({
        url: '/user/listById',
        method: 'get',
        params: data
    })
}
