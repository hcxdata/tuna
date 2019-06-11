import request from '@/utils/request'

export function query() {
  return request({
    url: '/api/departments/queryAll',
    method: 'get'
  })
}

export function create(data) {
  return request({
    url: '/api/departments',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/api/departments/' + data.id,
    method: 'put',
    data
  })
}

export function del(id) {
  return request({
    url: '/api/departments/' + id,
    method: 'delete'
  })
}
