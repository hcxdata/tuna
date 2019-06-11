import request from '@/utils/request'

export function fetchSysUserList(query, data = {}) {
  return request({
    url: '/api/users/query',
    method: 'post',
    params: query,
    data: data
  })
}

export function createSysUser(data) {
  return request({
    url: '/api/users/profile',
    method: 'post',
    data
  })
}

export function fetchSysUser(id) {
  return request({
    url: '/api/users/' + id,
    method: 'get'
  })
}

export function updateSysUser(data) {
  return request({
    url: '/api/users/profile/' + data.uid,
    method: 'put',
    data
  })
}

export function deleteSysUser(id) {
  return request({
    url: '/api/users/' + id,
    method: 'delete'
  })
}

export function updateSysUserRoles(data) {
  return request({
    url: '/api/users/actions/reset-roles',
    method: 'post',
    data
  })
}

export function fetchSysUserRoles(uid) {
  return request({
    url: '/api/users/list-roles/' + uid,
    method: 'get'
  })
}

export function getSysUserList() {
  return request({
    url: '/api/users/list',
    method: 'get'
  })
}
