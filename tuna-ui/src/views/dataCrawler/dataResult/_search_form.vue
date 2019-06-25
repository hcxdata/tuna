<template>
  <b-form>
    <b-row>
      <b-col sm="12" md="6" lg="6" xl="6">
        <b-form-group
          id="linkGroup"
          label="网页链接"
          label-for="linkInput">
          <b-form-input
            id="linkInput"
            v-model.lazy.trim="form.link"
            placeholder="网页链接"
            type="text"/>
        </b-form-group>
      </b-col>
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
          id="searchEngineNameGroup"
          label="搜索引擎"
          label-for="searchEngineNameInput">
          <b-form-select
            id="searchEngineNameInput"
            v-model.lazy.trim="form.searchEngineName"
            :options="searchEngineNameOptions"
            name="searchEngineName"
            value-field="paramCode"
            text-field="paramName"/>
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
import { query as paramInfoQuery } from "@/api/paramInfo"
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
    this.getParamInfoList(col.config.data.source.name, 'searchEngineNameOptions') {
      QueryAll().then(response => {
        this.Options = response.data
      }).catch(function(err) {
        console.log(err)
      })
    }
  },
  methods: {
    getParamInfoList(id, p) {
      const params = {}
      const data = {}
      data.typeId = id
      params.page = 1000
      params.size = 1000
      paramInfoQuery(params, data).then(response => {
        this[p] = response.data
      }).catch(function(err) {
        console.log(err)
      })
    },
    validate() {
    }
  }
}
</script>
