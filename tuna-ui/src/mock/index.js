import Mock from 'mockjs'
import loginAPI from './login'
// import sysUserAPI from './sysUser'
// import remoteSearchAPI from './remoteSearch'
// import transactionAPI from './transaction'

import sysDepAPI from './departments'

// Mock.setup({
//   timeout: '350-600'
// })

// 登录相关
Mock.mock(/\/api\/users\/login/, 'post', loginAPI.loginByUsername)
Mock.mock(/\/api\/users\/logout/, 'post', loginAPI.logout)
Mock.mock(/\/api\/users\/self\.*/, 'get', loginAPI.getUserInfo)

// 文章相关
// Mock.mock(/\/api\/system\/users/, 'get', sysUserAPI.getSysUserList)
// Mock.mock(/\/article\/detail/, 'get', articleAPI.getArticle)
// Mock.mock(/\/article\/pv/, 'get', articleAPI.getPv)
// Mock.mock(/\/article\/create/, 'post', articleAPI.createArticle)
// Mock.mock(/\/article\/update/, 'post', articleAPI.updateArticle)

// 搜索相关
// Mock.mock(/\/search\/user/, 'get', remoteSearchAPI.searchUser)

// 账单相关
// Mock.mock(/\/transaction\/list/, 'get', transactionAPI.getList)

//
Mock.mock(/\/api\/sys\/dep\.*/, 'get', sysDepAPI.getList)


export default Mock
