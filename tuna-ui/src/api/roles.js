import request from '@/utils/request'

export function query(query, data = {}) {
  return request({
    url: '/api/roles/query',
    method: 'post',
    params: query,
    data: data
  })
}

export function create(data) {
  return request({
    url: '/api/roles',
    method: 'post',
    data
  })
}

export function fetch(id) {
  return request({
    url: '/api/roles/' + id,
    method: 'get'
  })
}

export function update(data) {
  return request({
    url: '/api/roles/' + data.id,
    method: 'put',
    data
  })
}

export function del(id) {
  return request({
    url: '/api/roles/' + id,
    method: 'delete'
  })
}
