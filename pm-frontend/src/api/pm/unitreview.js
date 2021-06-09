import request from '@/utils/request'

// 查询单位项目审核列表
export function listUnitreview(query) {
  return request({
    url: '/pm/unitreview/list',
    method: 'get',
    params: query
  })
}

// 查询单位项目审核详细
export function getUnitreview(projectId) {
  return request({
    url: '/pm/unitreview/' + projectId,
    method: 'get'
  })
}

// 新增单位项目审核
export function addUnitreview(data) {
  return request({
    url: '/pm/unitreview',
    method: 'post',
    data: data
  })
}

// 修改单位项目审核
export function updateUnitreview(data) {
  return request({
    url: '/pm/unitreview',
    method: 'put',
    data: data
  })
}

// 删除单位项目审核
export function delUnitreview(projectId) {
  return request({
    url: '/pm/unitreview/' + projectId,
    method: 'delete'
  })
}

// 导出单位项目审核
export function exportUnitreview(query) {
  return request({
    url: '/pm/unitreview/export',
    method: 'get',
    params: query
  })
}

// 获取项目状态
export async function  getProjectStatus() {
  return await request({
    url: '/pm/unitreview/projectStatus',
    method: 'get'
  })
}
// 获取项目状态getUnitProjectStatus
export async function  getUnitProjectStatus() {
  return await request({
    url: '/pm/unitreview/unitProjectStatus',
    method: 'get'
  })
}