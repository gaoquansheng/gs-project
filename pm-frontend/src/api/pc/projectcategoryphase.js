import request from '@/utils/request'

// 查询项目类别流程配置列表
export async function listProjectcategoryphase(query) {
  return await request({
    url: '/pc/projectcategoryphase/list',
    method: 'get',
    params: query
  })
}



// 新增项目类别流程配置
export function addProjectcategoryphase(data) {
  return request({
    url: '/pc/projectcategoryphase',
    method: 'post',
    data: data
  })
}

// 修改项目类别流程配置
export function updateProjectcategoryphase(data) {
  return request({
    url: '/pc/projectcategoryphase',
    method: 'put',
    data: data
  })
}

// 删除项目类别流程配置
export function delProjectcategoryphase(categoryCode) {
  return request({
    url: '/pc/projectcategoryphase/' + categoryCode,
    method: 'delete'
  })
}

// 导出项目类别流程配置
export function exportProjectcategoryphase(query) {
  return request({
    url: '/pc/projectcategoryphase/export',
    method: 'get',
    params: query
  })
}