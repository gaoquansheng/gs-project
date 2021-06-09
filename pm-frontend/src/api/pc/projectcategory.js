import request from '@/utils/request'

// 查询项目类别列表
export function listProjectcategory(query) {
  return request({
    url: '/pc/projectcategory/list',
    method: 'get',
    params: query
  })
}

// 查询项目类别详细
export function getProjectcategory(categoryCode) {
  return request({
    url: '/pc/projectcategory/' + categoryCode,
    method: 'get'
  })
}

// 新增项目类别
export function addProjectcategory(data) {
  return request({
    url: '/pc/projectcategory',
    method: 'post',
    data: data
  })
}

// 修改项目类别
export function updateProjectcategory(data) {
  return request({
    url: '/pc/projectcategory',
    method: 'put',
    data: data
  })
}

// 删除项目类别
export function delProjectcategory(categoryCode) {
  return request({
    url: '/pc/projectcategory/' + categoryCode,
    method: 'delete'
  })
}

// 导出项目类别
export function exportProjectcategory(query) {
  return request({
    url: '/pc/projectcategory/export',
    method: 'get',
    params: query
  })
}