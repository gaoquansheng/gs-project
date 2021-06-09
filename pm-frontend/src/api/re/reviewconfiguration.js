import request from '@/utils/request'

// 查询评审配置列表
export function listReviewconfiguration(query) {
  return request({
    url: '/re/reviewconfiguration/list',
    method: 'get',
    params: query
  })
}

// 查询评审配置详细
export function getReviewconfiguration(categoryCode) {
  return request({
    url: '/re/reviewconfiguration/' + categoryCode,
    method: 'get'
  })
}

// 新增评审配置
export function addReviewconfiguration(data) {
  return request({
    url: '/re/reviewconfiguration',
    method: 'post',
    data: data
  })
}

// 修改评审配置
export function updateReviewconfiguration(data) {
  return request({
    url: '/re/reviewconfiguration',
    method: 'put',
    data: data
  })
}

// 删除评审配置
export function delReviewconfiguration(categoryCode) {
  return request({
    url: '/re/reviewconfiguration/' + categoryCode,
    method: 'delete'
  })
}

// 导出评审配置
export function exportReviewconfiguration(query) {
  return request({
    url: '/re/reviewconfiguration/export',
    method: 'get',
    params: query
  })
}