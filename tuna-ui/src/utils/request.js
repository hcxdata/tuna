import axios from 'axios'
// import { Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'
import miniToastr from 'mini-toastr'

miniToastr.init()

const codeMessage = {
  400: '非法的请求。',
  401: '资源未授权。',
  403: '访问被禁止。',
  404: '请求资源不存在。',
  406: '请求的格式不可得。',
  409: '资源存在冲突',
  410: '请求的资源被永久删除，且不会再得到的。',
  500: '服务器发生错误，请检查服务器。',
  502: '网关错误。',
  503: '服务不可用，服务器暂时过载或维护。',
  504: '网关超时。',
  1000: '无返回内容',
  1100: '请求的资源已经存在',
  1101: '请求的字段不能为空',
  1102: '请求的操作不存在',
  1103: '请求的操作冲突',
  1104: '资源未绑定手机号',
  1105: '请求的资源已满（无可用资源）',
  1106: '非法的取值',
  1107: '非法的状态',
  1108: '设备被占用'
}

// create an axios instance
const service = axios.create({
  baseURL: process.env.BASE_API, // api 的 base_url
  timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // Do something before request is sent
    if (store.getters.token) {
      // 让每个请求携带token-- ['X-Token']为自定义key 请根据实际情况自行修改
      config.headers['Authorization'] = "Basic " + getToken()
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  response => response,
  /**
   * 下面的注释为通过在response里，自定义code来标示请求状态
   * 当code返回如下情况则说明权限有问题，登出并返回到登录页
   * 如想通过 xmlhttprequest 来状态码标识 逻辑可写在下面error中
   * 以下代码均为样例，请结合自生需求加以修改，若不需要，则可删除
   */
  // response => {
  //   const res = response.data
  //   if (res.code !== 20000) {
  //     Message({
  //       message: res.message,
  //       type: 'error',
  //       duration: 5 * 1000
  //     })
  //     // 50008:非法的token; 50012:其他客户端登录了;  50014:Token 过期了;
  //     if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
  //       // 请自行在引入 MessageBox
  //       // import { Message, MessageBox } from 'element-ui'
  //       MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
  //         confirmButtonText: '重新登录',
  //         cancelButtonText: '取消',
  //         type: 'warning'
  //       }).then(() => {
  //         store.dispatch('FedLogOut').then(() => {
  //           location.reload() // 为了重新实例化vue-router对象 避免bug
  //         })
  //       })
  //     }
  //     return Promise.reject('error')
  //   } else {
  //     return response.data
  //   }
  // },
  error => {
    console.log('err' + error) // for debug
    let data = error.response.data
    let status = error.response.status
    let errortext = ''
    if(data && data.code)
      errortext = codeMessage[data.code] || codeMessage[status] || data.message
    else
      errortext = codeMessage[status] || data.message
    miniToastr.error(errortext)
    // Message({
    //   message: error.message,
    //   type: 'error',
    //   duration: 5 * 1000
    // })
    return Promise.reject(error)
  }
)

export default service
