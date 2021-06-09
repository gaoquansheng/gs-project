import request from '@/utils/request'

// 查询我的项目列表
export function listMyProject(query) {
  return request({
    url: '/pm/myProject/list',
    method: 'get',
    params: query
  })
}

// 查询我的项目列表
export async function myProject() {
  return await request({
    url: '/pm/myProject/myList',
    method: 'get'
  })
}

// 查询我的项目详细
export function getMyProject(projectId) {
  return request({
    url: '/pm/myProject/' + projectId,
    method: 'get'
  })
}

// 新增我的项目
export function addMyProject(data) {
  return request({
    url: '/pm/myProject',
    method: 'post',
    data: data
  })
}

// 修改我的项目
export function updateMyProject(data) {
  return request({
    url: '/pm/myProject',
    method: 'put',
    data: data
  })
}

// 删除我的项目
export function delMyProject(projectId) {
  return request({
    url: '/pm/myProject/' + projectId,
    method: 'delete'
  })
}

// 导出我的项目
export function exportMyProject(query) {
  return request({
    url: '/pm/myProject/export',
    method: 'get',
    params: query
  })
}