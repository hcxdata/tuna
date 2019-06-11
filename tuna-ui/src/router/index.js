import Vue from 'vue'
import Router from 'vue-router'
import { generateTitle } from '@/utils/i18n'

Vue.use(Router)

/* Layout */
import { default as Layout } from '@/views/layout/DefaultContainer'

// /* Router Modules */
// import componentsRouter from './modules/components'
// import chartsRouter from './modules/charts'
// import tableRouter from './modules/table'
// import nestedRouter from './modules/nested'

/** note: Submenu only appear when children.length>=1
 *  detail see  https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 **/

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    roles: ['admin','editor']     will control the page roles (you can set multiple roles)
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
    noCache: true                if true ,the page will no be cached(default is false)
  }
**/
export const constantRouterMap = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/errorPage/404'),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'system/user'
  }
]

export default new Router({
  mode: 'hash',
  linkActiveClass: 'open active',
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

export const asyncRouterMap = [
  {
    path: '/system',
    component: Layout,
    name: generateTitle('system'),
    redirect: 'system/user',
    alwaysShow: true, // will always show the root menu
    meta: {
      icon: 'icon-settings',
      roles: ['ROLE_ADMIN'] // you can set roles in root nav
    },
    children: [
      {
        path: 'user',
        component: () => import('@/views/sys/user/index'),
        name: generateTitle('sys_user'),
        meta: {
          icon: 'icon-user',
          roles: ['ROLE_ADMIN'] // or you can only set roles in sub nav
        }
      },
      {
        path: 'user/add',
        component: () => import('@/views/sys/user/add'),
        name: generateTitle('add_sys_user'),
        hidden: true,
        meta: {
          icon: 'icon-user',
          roles: ['ROLE_ADMIN'] // or you can only set roles in sub nav
        }
      },
      {
        path: 'user/edit/:id(\\d+)',
        component: () => import('@/views/sys/user/edit'),
        name: generateTitle('edit_sys_user'),
        hidden: true,
        meta: {
          icon: 'icon-user',
          roles: ['ROLE_ADMIN'] // or you can only set roles in sub nav
        }
      },
      {
        path: 'dep',
        component: () => import('@/views/sys/department'),
        name: generateTitle('sys_dep'),
        meta: {
          icon: 'fa fa-address-book-o',
          roles: ['ROLE_ADMIN'] // or you can only set roles in sub nav
        }
      },
      {
        path: 'role',
        component: () => import('@/views/sys/role/index'),
        name: generateTitle('sys_role'),
        meta: {
          icon: 'fa fa-vcard-o',
          roles: ['ROLE_ADMIN'] // or you can only set roles in sub nav
        }
      }
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]
