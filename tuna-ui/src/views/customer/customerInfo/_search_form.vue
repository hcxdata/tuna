<template>
  <b-form>
    <b-row>
      <b-col sm="12" md="6" lg="6" xl="6">
        <b-form-group
          id="custNameGroup"
          label="客户名称"
          label-for="custNameInput">
          <b-form-input
            id="custNameInput"
            v-model.lazy.trim="form.custName"
            placeholder="客户名称"
            type="text"/>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="6" lg="6" xl="6">
        <b-form-group
          id="custAliasGroup"
          label="客户别名"
          label-for="custAliasInput">
          <b-form-input
            id="custAliasInput"
            v-model.lazy.trim="form.custAlias"
            placeholder="客户别名"
            type="text"/>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="6" lg="6" xl="6">
        <b-form-group
          id="salesAreaCodeGroup"
          label="销售区域"
          label-for="salesAreaCodeInput">
          <b-form-select
            id="salesAreaCodeInput"
            v-model.lazy.trim="form.salesAreaCode"
            :options="salesAreaDictOptions"
            name="salesAreaCode"
            value-field="salesAreaCode"
            text-field="salesAreaName"/>
        </b-form-group>
      </b-col>
    </b-row>
  </b-form>
</template>

<script>
import { queryAll as salesAreaDictQueryAll } from "@/api/salesAreaDict"
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
      salesAreaDictOptions: [],
      submitted: false
    }
  },
  computed: {
    form() { return this.paramForm }
  },
  created() {
    this.getSalesAreaDictList()
  },
  methods: {
    getSalesAreaDictList() {
      salesAreaDictQueryAll().then(response => {
        this.salesAreaDictOptions = response.data
      }).catch(function(err) {
        console.log(err)
      })
    },
    validate() {
    }
  }
}
</script>
