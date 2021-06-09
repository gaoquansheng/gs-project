import request from '@/utils/request'

// 查询管理流程列表
export async function listWorkflowphase() {
  return await request({
    url: '/pc/workflowphase/list',
    method: 'get'
  })
}

// 查询管理流程详细
export function getWorkflowphase(phaseCode) {
  return request({
    url: '/pc/workflowphase/' + phaseCode,
    method: 'get'
  })
}

// 新增管理流程
export function addWorkflowphase(data) {
  return request({
    url: '/pc/workflowphase',
    method: 'post',
    data: data
  })
}

// 修改管理流程
export function updateWorkflowphase(data) {
  return request({
    url: '/pc/workflowphase',
    method: 'put',
    data: data
  })
}

// 删除管理流程
export function delWorkflowphase(phaseCode) {
  return request({
    url: '/pc/workflowphase/' + phaseCode,
    method: 'delete'
  })
}

// 导出管理流程
export function exportWorkflowphase(query) {
  return request({
    url: '/pc/workflowphase/export',
    method: 'get',
    params: query
  })
}