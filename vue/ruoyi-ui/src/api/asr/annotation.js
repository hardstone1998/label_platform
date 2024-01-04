import request from '@/utils/request'

// 查询标注列表
export function listAnnotation(query) {
  return request({
    url: '/asr/annotation/list',
    method: 'get',
    params: query
  })
}

// 查询标注详细
export function getAnnotation(id) {
  return request({
    url: '/asr/annotation/' + id,
    method: 'get'
  })
}

// 新增标注
export function addAnnotation(data) {
  return request({
    url: '/asr/annotation',
    method: 'post',
    data: data
  })
}

// 修改标注
export function updateAnnotation(data) {
  return request({
    url: '/asr/annotation',
    method: 'put',
    data: data
  })
}

// 删除标注
export function delAnnotation(id) {
  return request({
    url: '/asr/annotation/' + id,
    method: 'delete'
  })
}


//获取标注统计数据
export function getData(){
  return request({
    url : '/asr/annotation/getData',
    method:'post',
  })

}