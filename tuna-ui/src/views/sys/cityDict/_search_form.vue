<template>
  <b-form>
    <b-row>
      <b-col sm="12" md="6" lg="6" xl="6">
        <b-form-group
          id="cityCodeGroup"
          label="地市代码"
          label-for="cityCodeInput">
          <b-form-input
            id="cityCodeInput"
            v-model.lazy.trim="form.cityCode"
            placeholder="地市代码"
            type="text"/>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="6" lg="6" xl="6">
        <b-form-group
          id="cityNameGroup"
          label="地市名称"
          label-for="cityNameInput">
          <b-form-input
            id="cityNameInput"
            v-model.lazy.trim="form.cityName"
            placeholder="地市名称"
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
