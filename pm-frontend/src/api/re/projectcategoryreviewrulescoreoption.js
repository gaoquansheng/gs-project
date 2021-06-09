import request from '@/utils/request'

// 查询评分设置列表
export function listProjectcategoryreviewrulescoreoption(categoryCode,phaseCode) {
  return request({
    url: '/re/projectcategoryreviewrulescoreoption/list/'  + categoryCode + '/' + phaseCode,
    method: 'get',
  })
}

// 查询评分设置详细
export function getProjectcategoryreviewrulescoreoption(scoreId) {
  return request({
    url: '/re/projectcategoryreviewrulescoreoption/' + scoreId,
    method: 'get'
  })
}

// 新增评分设置
export function addProjectcategoryreviewrulescoreoption(data) {
  return request({
    url: '/re/projectcategoryreviewrulescoreoption',
    method: 'post',
    data: data
  })
}

// 修改评分设置
export function updateProjectcategoryreviewrulescoreoption(data) {
  return request({
    url: '/re/projectcategoryreviewrulescoreoption',
    method: 'put',
    data: data
  })
}

// 删除评分设置
export function delProjectcategoryreviewrulescoreoption(scoreId) {
  return request({
    url: '/re/projectcategoryreviewrulescoreoption/' + scoreId,
    method: 'delete'
  })
}

// 导出评分设置
export function exportProjectcategoryreviewrulescoreoption(query) {
  return request({
    url: '/re/projectcategoryreviewrulescoreoption/export',
    method: 'get',
    params: query
  })
}