import request from '@/utils/request'

// 查询分配专家列表
export function listGroupexpert(query) {
  return request({
    url: '/re/groupexpert/list',
    method: 'get',
    params: query
  })
}

// 查询分配专家详细
export function getGroupexpert(rid) {
  return request({
    url: '/re/groupexpert/' + rid,
    method: 'get'
  })
}

// 新增分配专家
export function addGroupexpert(data) {
  return request({
    url: '/re/groupexpert',
    method: 'post',
    data: data
  })
}

// 修改分配专家
export function updateGroupexpert(data) {
  return request({
    url: '/re/groupexpert',
    method: 'put',
    data: data
  })
}

// 删除分配专家
export function delGroupexpert(rid) {
  return request({
    url: '/re/groupexpert/' + rid,
    method: 'delete'
  })
}

// 导出分配专家
export function exportGroupexpert(query) {
  return request({
    url: '/re/groupexpert/export',
    method: 'get',
    params: query
  })
}