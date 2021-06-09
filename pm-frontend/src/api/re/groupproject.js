import request from '@/utils/request'

// 查询分组项目列表
export function listGroupproject(query) {
  return request({
    url: '/re/groupproject/list',
    method: 'get',
    params: query
  })
}

// 查询分组项目详细
export function getGroupproject(rid) {
  return request({
    url: '/re/groupproject/' + rid,
    method: 'get'
  })
}

// 新增分组项目
export function addGroupproject(data) {
  return request({
    url: '/re/groupproject',
    method: 'post',
    data: data
  })
}

// 修改分组项目
export function updateGroupproject(data) {
  return request({
    url: '/re/groupproject',
    method: 'put',
    data: data
  })
}

// 删除分组项目
export function delGroupproject(rid) {
  return request({
    url: '/re/groupproject/' + rid,
    method: 'delete'
  })
}

// 导出分组项目
export function exportGroupproject(query) {
  return request({
    url: '/re/groupproject/export',
    method: 'get',
    params: query
  })
}