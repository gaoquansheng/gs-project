import request from '@/utils/request'

// 查询上传项目附件列表
export function listProjectdoc(query) {
  return request({
    url: '/pm/projectdoc/list',
    method: 'get',
    params: query
  })
}

// 查询上传项目附件详细
export function getProjectdoc(docId) {
  return request({
    url: '/pm/projectdoc/' + docId,
    method: 'get'
  })
}

// 新增上传项目附件
export function addProjectdoc(data) {
  return request({
    url: '/pm/projectdoc',
    method: 'post',
    data: data
  })
}

// 修改上传项目附件
export function updateProjectdoc(data) {
  return request({
    url: '/pm/projectdoc',
    method: 'put',
    data: data
  })
}

// 删除上传项目附件
export function delProjectdoc(docId) {
  return request({
    url: '/pm/projectdoc/' + docId,
    method: 'delete'
  })
}

// 导出上传项目附件
export function exportProjectdoc(query) {
  return request({
    url: '/pm/projectdoc/export',
    method: 'get',
    params: query
  })
}