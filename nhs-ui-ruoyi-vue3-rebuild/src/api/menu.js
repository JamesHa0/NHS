import request from '@/utils/request'

// 获取路由
export const getRouters = (roleid) => {
  return request({
    url: '/getRouters',
    method: 'get',
    params: { roleid }
  })
}