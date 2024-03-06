import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listAnswer(query) {
  return request({
    url: '/total/answer/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getAnswer(id) {
  return request({
    url: '/total/answer/' + id,
    method: 'get'
  })
}

// 查询全部类型
export function getType() {
  return request({
    url: '/total/answer/type',
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addAnswer(data) {
  return request({
    url: '/total/answer',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateAnswer(data) {
  return request({
    url: '/total/answer',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delAnswer(id) {
  return request({
    url: '/total/answer/' + id,
    method: 'delete'
  })
}
