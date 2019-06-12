import request from '@/utils/request'

export function query(params, data = {}) {
  return request({
    url: '/api/SalesAreaDict/query',
    method: 'post',
    params: params,
    data: data
  })
}

export function queryAll() {
  return request({
    url: '/api/SalesAreaDict/queryAll',
    method: 'get'
  })
}

export function create(data) {
  return request({
    url: '/api/SalesAreaDict',
    method: 'post',
    data
  })
}

export function fetch(id) {
  return request({
    url: '/api/SalesAreaDict/' + id,
    method: 'get'
  })
}

export function update(data) {
  return request({
    url: '/api/SalesAreaDict/' + data.id,
    method: 'put',
    data
  })
}

export function del(id) {
  return request({
    url: '/api/SalesAreaDict/' + id,
    method: 'delete'
  })
}
