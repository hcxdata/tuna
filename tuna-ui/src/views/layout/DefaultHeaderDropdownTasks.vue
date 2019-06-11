<template>
  <AppHeaderDropdown right no-caret>
    <template slot="header">
      <i class="icon-list"/><b-badge pill variant="warning">{{ itemsCount }}</b-badge>
    </template>
    <template slot="dropdown">
      <b-dropdown-header tag="div" class="dropdown-menu-lg text-center"><strong>You have {{ itemsCount }} pending tasks</strong></b-dropdown-header>
      <b-dropdown-item>
        <div class="small mb-1">Upgrade NPM &amp; Bower <span class="float-right"><strong>0%</strong></span></div>
        <b-progress :value="0" height="{}" class="progress-xs" variant="info"/>
      </b-dropdown-item>
      <b-dropdown-item>
        <div class="small mb-1">ReactJS Version <span class="float-right"><strong>25%</strong></span></div>
        <b-progress :value="25" height="{}" class="progress-xs" variant="danger"/>
      </b-dropdown-item>
      <b-dropdown-item>
        <div class="small mb-1">VueJS Version <span class="float-right"><strong>50%</strong></span>
        </div>
        <b-progress :value="50" height="{}" class="progress-xs" variant="warning"/>
      </b-dropdown-item>
      <b-dropdown-item>
        <div class="small mb-1">Add new layouts <span class="float-right"><strong>75%</strong></span>
        </div>
        <b-progress :value="75" height="{}" class="progress-xs" variant="info"/>
      </b-dropdown-item>
      <b-dropdown-item>
        <div class="small mb-1">Angular 2 Cli Version <span class="float-right"><strong>100%</strong></span></div>
        <b-progress :value="100" height="{}" class="progress-xs" variant="success"/>
      </b-dropdown-item>
      <b-dropdown-item class="text-center"><strong>View all tasks</strong></b-dropdown-item>
    </template>
  </AppHeaderDropdown>
</template>
<script>
import { HeaderDropdown as AppHeaderDropdown } from '@coreui/vue'
export default {
  name: 'DefaultHeaderDropdownTasks',
  components: {
    AppHeaderDropdown
  },
  data: () => {
    return { itemsCount: 15 }
  }
}
</script>
