import request from '@/utils/request'

// 查询tags列表
export function listTag(query) {
  return request({
    url: '/asr/tag/list',
    method: 'get',
    params: query
  })
}

// 查询tags详细
export function getTag(id) {
  return request({
    url: '/asr/tag/' + id,
    method: 'get'
  })
}

// 新增tags
export function addTag(data) {
  return request({
    url: '/asr/tag',
    method: 'post',
    data: data
  })
}

// 修改tags
export function updateTag(data) {
  return request({
    url: '/asr/tag',
    method: 'put',
    data: data
  })
}

// 删除tags
export function delTag(id) {
  return request({
    url: '/asr/tag/' + id,
    method: 'delete'
  })
}
