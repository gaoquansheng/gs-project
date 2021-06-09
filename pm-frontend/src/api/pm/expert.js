import request from '@/utils/request'

// 查询专家列表
export function listExpert(query) {
  return request({
    url: '/pm/expert/list',
    method: 'get',
    params: query
  })
}

// 查询专家详细
export function getExpert(expertid) {
  return request({
    url: '/pm/expert/' + expertid,
    method: 'get'
  })
}

// 新增专家
export function addExpert(data) {
  return request({
    url: '/pm/expert',
    method: 'post',
    data: data
  })
}

// 修改专家
export function updateExpert(data) {
  return request({
    url: '/pm/expert',
    method: 'put',
    data: data
  })
}

// 删除专家
export function delExpert(expertid) {
  return request({
    url: '/pm/expert/' + expertid,
    method: 'delete'
  })
}

// 导出专家
export function exportExpert(query) {
  return request({
    url: '/pm/expert/export',
    method: 'get',
    params: query
  })
}