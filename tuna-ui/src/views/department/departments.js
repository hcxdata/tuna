import request from '@/utils/request'

export function query(params, data = {}) {
  return request({
    url: '/api/Departments/query',
    method: 'post',
    params: params,
    data: data
  })
}

export function create(data) {
  return request({
    url: '/api/Departments',
    method: 'post',
    data
  })
}

export function fetch(id) {
  return request({
    url: '/api/Departments/' + id,
    method: 'get'
  })
}

export function update(data) {
  return request({
    url: '/api/Departments/' + data.id,
    method: 'put',
    data
  })
}

export function del(id) {
  return request({
    url: '/api/Departments/' + id,
    method: 'delete'
  })
}
