<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default{
  name: 'App'
}
</script>

<style lang="scss">
  // CoreUI Icons Set
  @import '@coreui/icons/css/coreui-icons.min.css';
  /* Import Font Awesome Icons Set */
  $fa-font-path: '~font-awesome/fonts/';
  @import 'font-awesome/scss/font-awesome.scss';
  /* Import Simple Line Icons Set */
  $simple-line-font-path: '~simple-line-icons/fonts/';
  @import 'simple-line-icons/scss/simple-line-icons.scss';
  /* Import Flag Icons Set */
  @import 'flag-icon-css/css/flag-icon.min.css';
  /* Import Bootstrap Vue Styles */
  @import 'bootstrap-vue/dist/bootstrap-vue.css';
  // Import Main styles for this application
  @import 'static/scss/style';

  @import 'vue-datetime/dist/vue-datetime.css'
</style>
