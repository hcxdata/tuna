import request from '@/utils/request'

export function query(params, data = {}) {
  return request({
    url: '/api/DataResult/query',
    method: 'post',
    params: params,
    data: data
  })
}

export function queryAll() {
  return request({
    url: '/api/DataResult/queryAll',
    method: 'get'
  })
}

export function create(data) {
  return request({
    url: '/api/DataResult',
    method: 'post',
    data
  })
}

export function fetch(id) {
  return request({
    url: '/api/DataResult/' + id,
    method: 'get'
  })
}

export function update(data) {
  return request({
    url: '/api/DataResult/' + data.id,
    method: 'put',
    data
  })
}

export function del(id) {
  return request({
    url: '/api/DataResult/' + id,
    method: 'delete'
  })
}
