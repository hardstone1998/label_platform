import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listVerityAsr(query) {
  return request({
    url: '/verity/asr/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getVerityAsr(id) {
  return request({
    url: '/verity/asr/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addVerityAsr(data) {
  return request({
    url: '/verity/asr',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateVerityAsr(data) {
  return request({
    url: '/verity/asr',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delVerityAsr(id) {
  return request({
    url: '/verity/asr/' + id,
    method: 'delete'
  })
}
