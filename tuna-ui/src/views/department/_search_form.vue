<template>
  <b-form>
    <b-row>
      <b-col sm="12" md="6" lg="6" xl="6">
        <b-form-group id="departmentNameGroup"
                      label="部门编码"
                      label-for="departmentNameInput">
          <b-form-input id="departmentNameInput"
                        type="text"
                        v-model.lazy.trim="form.departmentName"
                        placeholder="部门编码"/>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="6" lg="6" xl="6">
        <b-form-group id="showNameGroup"
                      label="部门名称"
                      label-for="showNameInput">
          <b-form-input id="showNameInput"
                        type="text"
                        v-model.lazy.trim="form.showName"
                        placeholder="部门名称"/>
        </b-form-group>
      </b-col>
    </b-row>
  </b-form>
</template>

<script>
export default {
  name: "searchForm",
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
