import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listTask(query) {
  return request({
    url: '/task/allocation/list',
    method: 'get',
    params: query
  })
}


// 查询【请填写功能名称】详细
export function getTask(id) {
  return request({
    url: '/task/allocation/' + id,
    method: 'get'
  })
}

// 查询【请填写功能名称】详细
export function allTask(username) {
  return request({
    url: '/task/allocation/all',
    method: 'post',
    params: {
      responsiblePersonName: username
    }
  })
}

// 新增【请填写功能名称】
export function addTask(data) {
  return request({
    url: '/task/allocation',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateTask(data) {
  return request({
    url: '/task/allocation',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delTask(id) {
  return request({
    url: '/task/allocation/' + id,
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