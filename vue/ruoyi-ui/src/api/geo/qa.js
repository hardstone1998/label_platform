import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listQa(query) {
  return request({
    url: '/geo/qa/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getQa(id) {
  return request({
    url: '/geo/qa/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addQa(data) {
  return request({
    url: '/geo/qa',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateQa(data) {
  return request({
    url: '/geo/qa',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delQa(id) {
  return request({
    url: '/geo/qa/' + id,
    method: 'delete'
  })
}
