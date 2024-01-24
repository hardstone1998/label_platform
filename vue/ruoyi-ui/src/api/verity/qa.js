import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listVerityQa(query) {
  return request({
    url: '/verity/qa/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getVerityQa(id) {
  return request({
    url: '/verity/qa/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addVerityQa(data) {
  return request({
    url: '/verity/qa',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateVerityQa(data) {
  return request({
    url: '/verity/qa',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delVerityQa(id) {
  return request({
    url: '/verity/qa/' + id,
    method: 'delete'
  })
}
