import request from '@/utils/request'

// 查询qa联系列表
export function listRelation(query) {
  return request({
    url: '/qa/relation/list',
    method: 'get',
    params: query
  })
}

// 查询qa联系详细
export function getRelation(id) {
  return request({
    url: '/qa/relation/' + id,
    method: 'get'
  })
}

// 新增qa联系
export function addRelation(data) {
  return request({
    url: '/qa/relation',
    method: 'post',
    data: data
  })
}

// 修改qa联系
export function updateRelation(data) {
  return request({
    url: '/qa/relation',
    method: 'put',
    data: data
  })
}

// 删除qa联系
export function delRelation(id) {
  return request({
    url: '/qa/relation/' + id,
    method: 'delete'
  })
}