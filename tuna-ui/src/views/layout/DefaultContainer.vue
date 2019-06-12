<template>
  <div class="app">
    <AppHeader fixed>
      <SidebarToggler class="d-lg-none" display="md" mobile />
      <b-link class="navbar-brand" to="#">
        <img class="navbar-brand-full" src="static/img/brand/logo.svg" width="89" height="25" alt="CoreUI Logo">
        <img class="navbar-brand-minimized" src="static/img/brand/sygnet.svg" width="30" height="30" alt="CoreUI Logo">
      </b-link>
      <SidebarToggler class="d-md-down-none" display="lg" />
      <b-navbar-nav class="d-md-down-none">
        <b-nav-item :to="'/task_center/dashboard'" class="px-3">
          <i id="desktop" class="fa fa-desktop" title="我的工作台"/>
        </b-nav-item>
        <b-nav-item :to="'/tasks/create'" class="px-3">
          <i id="pencil-square-o" class="fa fa-pencil-square-o" title="创建试验任务"/>
        </b-nav-item>
        <b-nav-item :to="'/devices/dashboard'" class="px-3">
          <i id="sliders" class="fa fa-sliders" title="设备监控"/>
        </b-nav-item>
      </b-navbar-nav>
      <b-navbar-nav class="ml-auto">
        <b-nav-item class="d-md-down-none">
          <DefaultHeaderDropdownNotif/>
        </b-nav-item>
        <b-nav-item class="d-md-down-none">
          <DefaultHeaderDropdownTasks/>
        </b-nav-item>
        <b-nav-item class="d-md-down-none">
          <DefaultHeaderDropdownMssgs/>
        </b-nav-item>
        <DefaultHeaderDropdownAccnt/>
      </b-navbar-nav>
      <AsideToggler class="d-none d-lg-block" />
      <!--<AsideToggler class="d-lg-none" mobile />-->
    </AppHeader>
    <div class="app-body">
      <AppSidebar fixed>
        <SidebarHeader/>
        <SidebarForm>
          <DefaultSidebarForm/>
        </SidebarForm>
        <SidebarNav :nav-items="nav"/>
        <SidebarFooter/>
        <SidebarMinimizer/>
      </AppSidebar>
      <main class="main">
        <breadcrumb :list="list"/>
        <div class="container-fluid">
          <router-view/>
        </div>
      </main>
      <AppAside fixed>
        <!--aside-->
        <DefaultAside/>
      </AppAside>
    </div>
    <TheFooter>
      Copyright © Hobin 2019 | All Right Reserved.
    </TheFooter>
  </div>
</template>

<script>
import { Header as AppHeader, SidebarToggler, Sidebar as AppSidebar, SidebarFooter, SidebarForm, SidebarHeader, SidebarMinimizer, SidebarNav, Aside as AppAside, AsideToggler, Footer as TheFooter, Breadcrumb } from '@coreui/vue'
import DefaultAside from './DefaultAside'
import DefaultHeaderDropdownNotif from './DefaultHeaderDropdownNotif'
import DefaultHeaderDropdownAccnt from './DefaultHeaderDropdownAccnt'
import DefaultHeaderDropdownMssgs from './DefaultHeaderDropdownMssgs'
import DefaultHeaderDropdownTasks from './DefaultHeaderDropdownTasks'
import DefaultSidebarForm from './DefaultSidebarForm'

import store from '@/store'
import { isExternal } from '@/utils'
import path from 'path'

function convert2NavItem(routes, basePath = '') {
  let res = []
  routes.forEach(route => {
    if (!route.hidden) {
      const tmp = { }
      if (route.meta) {
        tmp.name = route.name
        tmp.icon = route.meta.icon
        if (isExternal(route.path)) {
          tmp.url = route.path
        } else {
          tmp.url = path.resolve(basePath, route.path)
        }
        if (route.children) {
          const chr = convert2NavItem(route.children, path.resolve(basePath, route.path))
          if (chr.length > 0) {
            tmp.children = chr
          }
        }
        res.push(tmp)
      } else if (route.children) {
        res = res.concat(convert2NavItem(route.children, path.resolve(basePath, route.path)))
      }
    }
  })
  return res
}

export default {
  name: 'DefaultContainer',
  components: {
    AsideToggler,
    AppHeader,
    AppSidebar,
    AppAside,
    TheFooter,
    Breadcrumb,
    DefaultAside,
    DefaultHeaderDropdownMssgs,
    DefaultHeaderDropdownNotif,
    DefaultHeaderDropdownTasks,
    DefaultHeaderDropdownAccnt,
    DefaultSidebarForm,
    SidebarForm,
    SidebarFooter,
    SidebarToggler,
    SidebarHeader,
    SidebarNav,
    SidebarMinimizer
  },
  data() {
    return {
      nav: convert2NavItem(store.getters.permission_routers)
    }
  },
  computed: {
    name() {
      return this.$route.name
    },
    list() {
      return this.$route.matched
    }
  }
}
</script>
