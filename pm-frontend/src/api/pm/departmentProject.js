import request from '@/utils/request'

// 查询单位项目列表
export function listDepartmentProject(query) {
  return request({
    url: '/pm/departmentProject/list',
    method: 'get',
    params: query
  })
}

// 查询单位项目详细
export function getDepartmentProject(projectId) {
  return request({
    url: '/pm/departmentProject/' + projectId,
    method: 'get'
  })
}

// 新增单位项目
export function addDepartmentProject(data) {
  return request({
    url: '/pm/departmentProject',
    method: 'post',
    data: data
  })
}

// 修改单位项目
export function updateDepartmentProject(data) {
  return request({
    url: '/pm/departmentProject',
    method: 'put',
    data: data
  })
}

// 删除单位项目
export function delDepartmentProject(projectId) {
  return request({
    url: '/pm/departmentProject/' + projectId,
    method: 'delete'
  })
}

// 导出单位项目
export function exportDepartmentProject(query) {
  return request({
    url: '/pm/departmentProject/export',
    method: 'get',
    params: query
  })
}