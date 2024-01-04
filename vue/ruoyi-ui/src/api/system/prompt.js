import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listPrompt(query) {
  return request({
    url: '/system/prompt/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getPrompt(id) {
  return request({
    url: '/system/prompt/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addPrompt(data) {
  return request({
    url: '/system/prompt',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updatePrompt(data) {
  return request({
    url: '/system/prompt',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delPrompt(id) {
  return request({
    url: '/system/prompt/' + id,
    method: 'delete'
  })
}
