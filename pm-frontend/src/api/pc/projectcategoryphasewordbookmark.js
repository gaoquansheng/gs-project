import request from '@/utils/request'

// 查询Word模板设置列表
export function listProjectcategoryphasewordbookmark(query) {
  return request({
    url: '/pc/projectcategoryphasewordbookmark/list',
    method: 'get',
    params: query
  })
}

// 查询Word模板设置详细
export function getProjectcategoryphasewordbookmark(categoryCode,phaseCode,bookMarkCode) {
  return request({
    url: '/pc/projectcategoryphasewordbookmark/' + categoryCode+"/"+phaseCode+"/"+bookMarkCode,
    method: 'get'
  })
}

// 新增Word模板设置
export function addProjectcategoryphasewordbookmark(data) {
  return request({
    url: '/pc/projectcategoryphasewordbookmark',
    method: 'post',
    data: data
  })
}

// 修改Word模板设置
export function updateProjectcategoryphasewordbookmark(data) {
  return request({
    url: '/pc/projectcategoryphasewordbookmark',
    method: 'put',
    data: data
  })
}

// 删除Word模板设置
export function delProjectcategoryphasewordbookmark(data) {
  return request({
    url: '/pc/projectcategoryphasewordbookmark/delete',
    method: 'post',
    data: data
  })
}

// 导出Word模板设置
export function exportProjectcategoryphasewordbookmark(query) {
  return request({
    url: '/pc/projectcategoryphasewordbookmark/export',
    method: 'get',
    params: query
  })
}