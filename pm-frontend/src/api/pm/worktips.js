import request from '@/utils/request'

// 查询工作提示列表
export function listWorktips() {
  return request({
    url: '/pm/worktips/list',
    method: 'get'
  })
}

