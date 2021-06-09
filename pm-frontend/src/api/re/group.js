import request from '@/utils/request'

// 查询分组信息列表
export function listGroup(query) {
  return request({
    url: '/re/group/list',
    method: 'get',
    params: query
  })
}

// 查询分组信息详细
export function getGroup(groupId) {
  return request({
    url: '/re/group/' + groupId,
    method: 'get'
  })
}

// 新增分组信息
export function addGroup(data) {
  return request({
    url: '/re/group',
    method: 'post',
    data: data
  })
}

// 修改分组信息
export function updateGroup(data) {
  return request({
    url: '/re/group',
    method: 'put',
    data: data
  })
}

// 删除分组信息
export function delGroup(groupId) {
  return request({
    url: '/re/group/' + groupId,
    method: 'delete'
  })
}

// 导出分组信息
export function exportGroup(query) {
  return request({
    url: '/re/group/export',
    method: 'get',
    params: query
  })
}