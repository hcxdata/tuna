import { param2Obj } from '@/utils'

const userMap = {
  admin: {
    roles: ['ROLE_ADMIN'],
    token: 'admin',
    introduction: '我是超级管理员',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    username: 'Super Admin'
  },
  editor: {
    roles: ['ROLE_EDITOR'],
    token: 'editor',
    introduction: '我是编辑',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    username: 'Normal Editor'
  }
}

export default {
  loginByUsername: config => {
    const { username } = JSON.parse(config.body)
    return userMap[username]
  },
  getUserInfo: config => {
    var token = ''
    // const { token } = param2Obj(config.url)
    const search = config.url.split('?')[1]
    if (search) {
      token = JSON.parse(
        '{"' +
          decodeURIComponent(search)
            .replace(/"/g, '\\"')
            .replace(/&/g, '","')
            .replace(/=/, '":"') +
          '"}'
      ).token
    }
    const username = atob(token).split(':')[0]
    if (userMap[username]) {
      return userMap[username]
    } else {
      return false
    }
  },
  logout: () => 'success'
}
