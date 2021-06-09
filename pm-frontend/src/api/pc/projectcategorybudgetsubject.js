import request from '@/utils/request'

// 查询项目类别预算列表
export function listProjectcategorybudgetsubject(query) {
  return request({
    url: '/pc/projectcategorybudgetsubject/list',
    method: 'get',
    params: query
  })
}

// 查询项目类别预算详细
export function getProjectcategorybudgetsubject(subjectId) {
  return request({
    url: '/pc/projectcategorybudgetsubject/' + subjectId,
    method: 'get'
  })
}

// 新增项目类别预算
export function addProjectcategorybudgetsubject(data) {
  return request({
    url: '/pc/projectcategorybudgetsubject',
    method: 'post',
    data: data
  })
}

// 修改项目类别预算
export function updateProjectcategorybudgetsubject(data) {
  return request({
    url: '/pc/projectcategorybudgetsubject',
    method: 'put',
    data: data
  })
}

// 删除项目类别预算
export function delProjectcategorybudgetsubject(subjectId) {
  return request({
    url: '/pc/projectcategorybudgetsubject/' + subjectId,
    method: 'delete'
  })
}

// 导出项目类别预算
export function exportProjectcategorybudgetsubject(query) {
  return request({
    url: '/pc/projectcategorybudgetsubject/export',
    method: 'get',
    params: query
  })
}