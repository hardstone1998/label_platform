import request from '@/utils/request'

// 查询VIEW列表
export function listUnallocation(query) {
  return request({
    url: '/task/unallocation/list',
    method: 'get',
    params: query
  })
}

// 查询VIEW详细
export function getUnallocation(clazzId) {
  return request({
    url: '/task/unallocation/' + clazzId,
    method: 'get'
  })
}

// 新增VIEW
export function addUnallocation(data) {
  return request({
    url: '/task/unallocation',
    method: 'post',
    data: data
  })
}

// 修改VIEW
export function updateUnallocation(data) {
  return request({
    url: '/task/unallocation',
    method: 'put',
    data: data
  })
}

// 删除VIEW
export function delUnallocation(clazzId) {
  return request({
    url: '/task/unallocation/' + clazzId,
    method: 'delete'
  })
}
