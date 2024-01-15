import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listUser(query) {
  return request({
    url: '/task/user/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】列表
export function allByUser(userName) {
  return request({
    url: '/task/user/allByUser',
    method: 'get',
    params: { "userName": userName }
  })
}

// 查询【请填写功能名称】详细
export function getUser(id) {
  return request({
    url: '/task/user/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addUser(data) {
  return request({
    url: '/task/user',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateUser(data) {
  return request({
    url: '/task/user',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delUser(id) {
  return request({
    url: '/task/user/' + id,
    method: 'delete'
  })
}

export function verityAnnotation(data){
  console.log("verityAnnotation");
  return request({
    url: '/task/user/verityAllocation',
    method: 'post',
    data: data
  })
}
