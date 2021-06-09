import request from '@/utils/request'

// 查询项目附件审核列表
export function listProjectdocreview(query) {
  return request({
    url: '/pm/projectdocreview/list',
    method: 'get',
    params: query
  })
}

// 查询项目附件审核详细
export function getProjectdocreview(docId) {
  return request({
    url: '/pm/projectdocreview/' + docId,
    method: 'get'
  })
}

// 修改项目附件审核
export function updateProjectdocreview(data) {
  return request({
    url: '/pm/projectdocreview',
    method: 'put',
    data: data
  })
}