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

// 上传ASR语料
export function uploadASRFile(file) {
  const formData = new FormData();
  formData.append('file', file);

  return request({
    url: '/audioUpload/asr',
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
}
// 上传QA语料
export function uploadQAFile(file) {
  const formData = new FormData();
  formData.append('file', file);

  return request({
    url: '/audioUpload/qa',
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
}