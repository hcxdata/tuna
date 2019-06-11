<template>
  <b-form novalidate @submit.prevent="onSubmit" @reset.prevent="onReset">
    <b-row>
      <b-col sm="12" md="6" lg="6" xl="6">
        <b-form-group
          id="usernameInputGroup"
          label="岗位名称"
          label-for="roleName">
          <b-form-input
            id="roleName"
            v-model.lazy.trim="form.roleName"
            type="text"
            placeholder="岗位名称"
            autofocus />
        </b-form-group>
      </b-col>
      <b-col sm="12" md="6" lg="6" xl="6">
        <b-form-group
          id="showNameInputGroup"
          label="显示名称"
          label-for="showName">
          <b-form-input
            id="showName"
            v-model.lazy.trim="form.showName"
            type="text"
            placeholder="显示名称" />
        </b-form-group>
      </b-col>
      <b-col sm="12" md="6" lg="6" xl="6">
        <b-form-group
          id="descriptionInputGroup"
          label="岗位描述"
          label-for="description">
          <b-form-input
            id="description"
            v-model.trim="form.description"
            type="text"
            placeholder="岗位描述"/>
        </b-form-group>
      </b-col>
    </b-row>
  </b-form>
</template>

<script>
export default {
  name: 'SearchForm',
  props: {
    paramForm: {
      type: Object,
      default: function() {
        return {}
      }
    }
  },
  computed: {
    form() { return this.paramForm }
  }
}
</script>
