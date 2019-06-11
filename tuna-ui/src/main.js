// import './polyfill'

import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
// import Cookies from 'js-cookie'

// import 'normalize.css/normalize.css' // A modern alternative to CSS resets

// import Element from 'element-ui'
// import 'element-ui/lib/theme-chalk/index.css'

// import '@/styles/index.scss' // global css

import App from './App'
import router from './router'
import store from './store'

import i18n from './lang' // Internationalization
// import './icons' // icon
// import './errorLog' // error log
import './permission' // permission control
// import './mock' // simulation data

import * as filters from './filters' // global filters
import ZkTable from 'vue-table-with-tree-grid'
import Datetime from 'vue-datetime'
import VueNotifications from 'vue-notifications'
import miniToastr from 'mini-toastr'

const toastTypes = {
  success: 'success',
  error: 'error',
  info: 'info',
  warn: 'warn'
}

miniToastr.init({types: toastTypes})

function toast ({title, message, type, timeout, cb}) {
  return miniToastr[type](message, title, timeout, cb)
}

const options = {
  success: toast,
  error: toast,
  info: toast,
  warn: toast
}

Vue.use(VueNotifications, options)

Vue.use(ZkTable)
Vue.use(Datetime)
// Vue.use(BootstrapVue, {
//   size: Cookies.get('size') || 'medium', // set element-ui default size
//   i18n: (key, value) => i18n.t(key, value)
// })
Vue.use(BootstrapVue)
// register global utility filters.
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

// Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  i18n,
  render: h => h(App)
})
