import request from '@/utils/request'

// 查询项目类别管理员列表
export function listProjectcategoryadmins(query) {
  return request({
    url: '/pc/projectcategoryadmins/list',
    method: 'get',
    params: query
  })
}

// 查询项目类别管理员列表
export async function listByAdminUserName(query) {
  return await request({
    url: '/pc/projectcategoryadmins/listByAdminUserName',
    method: 'get',
    params: query
  })
}

// 查询项目类别管理员详细
export function getProjectcategoryadmins(categoryCode) {
  return request({
    url: '/pc/projectcategoryadmins/' + categoryCode,
    method: 'get'
  })
}

// 新增项目类别管理员
export function addProjectcategoryadmins(data) {
  return request({
    url: '/pc/projectcategoryadmins',
    method: 'post',
    data: data
  })
}

// 修改项目类别管理员
export function updateProjectcategoryadmins(data) {
  return request({
    url: '/pc/projectcategoryadmins',
    method: 'put',
    data: data
  })
}

// 删除项目类别管理员
export function delProjectcategoryadmins(categoryCode,adminuserCode) {
  return request({
    url: '/pc/projectcategoryadmins/' + categoryCode +'/'+ adminuserCode,
    method: 'delete'
  })
}

// 导出项目类别管理员
export function exportProjectcategoryadmins(query) {
  return request({
    url: '/pc/projectcategoryadmins/export',
    method: 'get',
    params: query
  })
}