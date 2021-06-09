import request from '@/utils/request'

// 查询projectcategoryreviewrulevoteoption列表
export function listProjectcategoryreviewrulevoteoption(categoryCode,phaseCode) {
  return request({
    url: '/re/projectcategoryreviewrulevoteoption/list/' + categoryCode + '/' + phaseCode,
    method: 'get',
  })
}

// 查询projectcategoryreviewrulevoteoption详细
export function getProjectcategoryreviewrulevoteoption(optionId) {
  return request({
    url: '/re/projectcategoryreviewrulevoteoption/' + optionId,
    method: 'get'
  })
}

// 新增projectcategoryreviewrulevoteoption
export function addProjectcategoryreviewrulevoteoption(data) {
  return request({
    url: '/re/projectcategoryreviewrulevoteoption',
    method: 'post',
    data: data
  })
}

// 修改projectcategoryreviewrulevoteoption
export function updateProjectcategoryreviewrulevoteoption(data) {
  return request({
    url: '/re/projectcategoryreviewrulevoteoption',
    method: 'put',
    data: data
  })
}

// 删除projectcategoryreviewrulevoteoption
export function delProjectcategoryreviewrulevoteoption(optionId) {
  return request({
    url: '/re/projectcategoryreviewrulevoteoption/' + optionId,
    method: 'delete'
  })
}

// 导出projectcategoryreviewrulevoteoption
export function exportProjectcategoryreviewrulevoteoption(query) {
  return request({
    url: '/re/projectcategoryreviewrulevoteoption/export',
    method: 'get',
    params: query
  })
}