import request from '@/utils/request'

// 查询项目列表列表
export function listProjectList(query) {
  return request({
    url: '/pm/projectList/list',
    method: 'get',
    params: query
  })
}

// 查询项目列表详细
export function getProjectList(projectId) {
  return request({
    url: '/pm/projectList/' + projectId,
    method: 'get'
  })
}
// 查询项目任务书列表详细
export function getProjectTask(projectId) {
  return request({
    url: '/pm/projecttask/' + projectId,
    method: 'get'
  })
}
// 查询项目结题报告详细
export function getProjectReport(projectId) {
  return request({
    url: '/pm/projectconclusionreport/' + projectId,
    method: 'get'
  })
}

// 新增项目列表
export function addProjectList(data) {
  return request({
    url: '/pm/projectList',
    method: 'post',
    data: data
  })
}

// 修改项目列表
export function updateProjectList(data) {
  return request({
    url: '/pm/projectList',
    method: 'put',
    data: data
  })
}

// 删除项目列表
export function delProjectList(projectId) {
  return request({
    url: '/pm/projectList/' + projectId,
    method: 'delete'
  })
}

// 导出项目列表
export function exportProjectList(query) {
  return request({
    url: '/pm/projectList/export',
    method: 'get',
    params: query
  })
}