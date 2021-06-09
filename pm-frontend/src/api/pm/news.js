import request from '@/utils/request'

// 查询新闻列表
export function listNews(query) {
  return request({
    url: '/pm/news/list',
    method: 'get',
    params: query
  })
}

// 查询新闻详细
export function getNews(newsid) {
  return request({
    url: '/pm/news/' + newsid,
    method: 'get'
  })
}

// 新增新闻
export function addNews(data) {
  return request({
    url: '/pm/news',
    method: 'post',
    data: data
  })
}

// 修改新闻
export function updateNews(data) {
  return request({
    url: '/pm/news',
    method: 'put',
    data: data
  })
}

// 删除新闻
export function delNews(newsid) {
  return request({
    url: '/pm/news/' + newsid,
    method: 'delete'
  })
}

// 导出新闻
export function exportNews(query) {
  return request({
    url: '/pm/news/export',
    method: 'get',
    params: query
  })
}