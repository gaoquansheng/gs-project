import request from '@/utils/request'

// 查询项目起止时间列表
export function listOpentime(query) {
  return request({
    url: '/pc/opentime/list',
    method: 'get',
    params: query
  })
}

// 查询项目起止时间详细
export function getOpentime(rId) {
  return request({
    url: '/pc/opentime/' + rId,
    method: 'get'
  })
}

// 新增项目起止时间
export function addOpentime(data) {
  return request({
    url: '/pc/opentime',
    method: 'post',
    data: data
  })
}

// 修改项目起止时间
export function updateOpentime(data) {
  return request({
    url: '/pc/opentime',
    method: 'put',
    data: data
  })
}

// 删除项目起止时间
export function delOpentime(rId) {
  return request({
    url: '/pc/opentime/' + rId,
    method: 'delete'
  })
}

// 导出项目起止时间
export function exportOpentime(query) {
  return request({
    url: '/pc/opentime/export',
    method: 'get',
    params: query
  })
}