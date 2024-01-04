import request from '@/utils/request'

// 查询extract列表
export function listExtract(query) {
  return request({
    url: '/qa/extract/list',
    method: 'get',
    params: query
  })
}


export function optionsExtract(query) {
  return request({
    url: '/qa/extract/options',
    method: 'get',
    params: query
  })
}



// 查询extract详细
export function getExtract(id) {
  return request({
    url: '/qa/extract/' + id,
    method: 'get'
  })
}

// 新增extract
export function addExtract(data) {
  return request({
    url: '/qa/extract',
    method: 'post',
    data: data
  })
}

// 修改extract
export function updateExtract(data) {
  return request({
    url: '/qa/extract',
    method: 'put',
    data: data
  })
}

// 删除extract
export function delExtract(id) {
  return request({
    url: '/qa/extract/' + id,
    method: 'delete'
  })
}
