import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function list1(query) {
  return request({
    url: '/clazz/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function get1(id) {
  return request({
    url: '/clazz/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function add1(data) {
  return request({
    url: '/clazz',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function update1(data) {
  return request({
    url: '/clazz',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function del1(id) {
  return request({
    url: '/clazz/' + id,
    method: 'delete'
  })
}
