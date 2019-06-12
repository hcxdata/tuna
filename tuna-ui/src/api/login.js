import request from '@/utils/request'

export function loginByUsername(username, password) {
  const data = {
    username,
    password
  }
  const auth = btoa(username + ':' + password)

  return request({
    url: '/api/users/login',
    method: 'post',
    headers: {
      'Authorization': 'Basic ' + auth,
      'Content-Type': 'application/json'
    },
    data: data
  })
}

export function logout() {
  return request({
    url: '/api/users/logout',
    method: 'get'
  })
}

export function getUserInfo(token) {
  return request({
    url: '/api/users/self',
    method: 'get',
    params: { token }
  })
}

