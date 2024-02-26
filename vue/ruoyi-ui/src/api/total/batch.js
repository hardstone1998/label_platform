import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listBatch() {
  return request({
    url: '/total/batch/list',
    method: 'get'
  })
}

// 查询【请填写功能名称】详细
export function getBatch(id) {
  return request({
    url: '/total/batch/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addBatch(data) {
  return request({
    url: '/total/batch',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateBatch(data) {
  return request({
    url: '/total/batch',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delBatch(id) {
  return request({
    url: '/total/batch/' + id,
    method: 'delete'
  })
}
