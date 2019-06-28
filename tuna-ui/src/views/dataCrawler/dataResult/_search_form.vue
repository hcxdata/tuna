<template>
  <b-form>
    <b-row>
      <b-col sm="12" md="6" lg="6" xl="6">
        <b-form-group
          id="queryGroup"
          label="搜索关键词"
          label-for="queryInput">
          <b-form-input
            id="queryInput"
            v-model.lazy.trim="form.query"
            placeholder="搜索关键词"
            type="text"/>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="6" lg="6" xl="6">
        <b-form-group
          id="titleGroup"
          label="标题"
          label-for="titleInput">
          <b-form-input
            id="titleInput"
            v-model.lazy.trim="form.title"
            placeholder="标题"
            type="text"/>
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
  data() {
    return {
      searchEngineNameOptions: [],
      submitted: false
    }
  },
  computed: {
    form() { return this.paramForm }
  },
  created() {
  },
  methods: {
    validate() {
    }
  }
}
</script>
