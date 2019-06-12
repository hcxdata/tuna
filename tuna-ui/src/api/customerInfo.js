import request from '@/utils/request'

export function query(params, data = {}) {
  return request({
    url: '/api/CustomerInfo/query',
    method: 'post',
    params: params,
    data: data
  })
}

export function queryAll() {
  return request({
    url: '/api/CustomerInfo/queryAll',
    method: 'get'
  })
}

export function create(data) {
  return request({
    url: '/api/CustomerInfo',
    method: 'post',
    data
  })
}

export function fetch(id) {
  return request({
    url: '/api/CustomerInfo/' + id,
    method: 'get'
  })
}

export function update(data) {
  return request({
    url: '/api/CustomerInfo/' + data.id,
    method: 'put',
    data
  })
}

export function del(id) {
  return request({
    url: '/api/CustomerInfo/' + id,
    method: 'delete'
  })
}
