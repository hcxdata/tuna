<template>
  <b-form>
    <b-row>
      <b-col sm="12" md="6" lg="6" xl="6">
        <b-form-group
          id="salesAreaCodeGroup"
          label="sales_area_code"
          label-for="salesAreaCodeInput">
          <b-form-input
            id="salesAreaCodeInput"
            v-model.lazy.trim="form.salesAreaCode"
            placeholder="sales_area_code"
            type="text"/>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="6" lg="6" xl="6">
        <b-form-group
          id="salesAreaNameGroup"
          label="sales_area_name"
          label-for="salesAreaNameInput">
          <b-form-input
            id="salesAreaNameInput"
            v-model.lazy.trim="form.salesAreaName"
            placeholder="sales_area_name"
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
