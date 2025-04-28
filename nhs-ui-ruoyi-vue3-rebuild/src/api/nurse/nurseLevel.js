import request from '@/utils/request'

// 查询护理项目列表(带相应护理内容)
export function details(query) {
    return request({
        url: '/nurse/nurseLevel/items',
        method: 'get',
        params: query
    })
}

// (通过id)查护理级别信息
export function list(id) {
    return request({
        url: '/nurse/nurseLevel/list',
        method: 'get',
        params: { id }
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

// 删除护理级别
export function deleteItem(id) {
    return request({
        url: '/nurse/nurseLevel/delete',
        method: 'delete',
        params: { id }
    })
}


// 护理级别添加护理内容
export function addItems(data) {
    return request({
        url: '/nurse/nurseLevel/addItems',
        method: 'post',
        params: data
    })
}

//删除级别下的项目
export function deleteLevelItem(data) {
    return request({
        url: '/nurse/nurseLevel/deleteLevelItem',
        method: 'post',
        data: data
    })
}


// 通过id查护理级别名称
export function getLevelName(id) {
    return request({
        url: '/nurse/nurseLevel/getNameById',
        method: 'get',
        params: { id }
    })
}
