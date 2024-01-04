import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listAllocation(query) {
  return request({
    url: '/system/allocation/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getAllocation(id) {
  return request({
    url: '/system/allocation/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addAllocation(data) {
  return request({
    url: '/system/allocation',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateAllocation(data) {
  return request({
    url: '/system/allocation',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delAllocation(id) {
  return request({
    url: '/system/allocation/' + id,
    method: 'delete'
  })
}
