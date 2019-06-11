<#function parseJSON json>
  <#local null = 'null'> <#-- null is not a keyword in FTL -->
  <#return json?eval>
</#function>
<#assign root = parseJSON(RootDataModel)>
<#assign params = parseJSON(RunnerParams)>
<#function camelize x>
  <#return x?replace("_", " ")?capitalize?replace(" ", "")?uncap_first>
</#function>
<template>
  <b-form>
    <b-row>
    <#list root.columns as col >
      <#if col.config.isQuery>
      <b-col sm="12" md="6" lg="6" xl="6">
        <b-form-group id="${ camelize(col.columnName) }Group"
                      label="${ col.config.fieldLabel }"
                      label-for="${ camelize(col.columnName) }Input">
        <#if col.config.fieldType == 'number'>
          <b-form-input id="${ camelize(col.columnName) }Input"
                        type="number"
                        v-model.lazy.trim="form.${ camelize(col.columnName) }"
                        placeholder="${ col.config.fieldLabel }"/>
        <#elseif col.config.fieldType == 'datetime'>
          <datetime input-id="issuePlanEnded"
                    type="datetime"
                    format="yyyy-MM-dd HH:mm"
                    v-model.lazy.trim="form.${ camelize(col.columnName) }"
                    :value-zone="'local'"
                    class="theme-primary"
                    aria-describedby="issuePlanEndedLiveFeedback"
                    placeholder="${ col.config.fieldLabel }"/>
        <#elseif col.config.fieldType == 'datetime'>
          <datetime input-id="issuePlanEnded"
                    type="date"
                    v-model.lazy.trim="form.${ camelize(col.columnName) }"
                    :value-zone="'local'"
                    class="theme-primary"
                    aria-describedby="issuePlanEndedLiveFeedback"
                    placeholder="${ col.config.fieldLabel }"/>
        <#elseif col.config.fieldType == 'time'>
          <datetime input-id="issuePlanEnded"
                    type="date"
                    v-model.lazy.trim="form.${ camelize(col.columnName) }"
                    :value-zone="'local'"
                    class="theme-primary"
                    aria-describedby="issuePlanEndedLiveFeedback"
                    placeholder="${ col.config.fieldLabel }"/>
        <#elseif col.config.fieldType == 'textarea'>
          <b-form-input id="${ camelize(col.columnName) }Input"
                        type="textarea"
                        v-model.lazy.trim="form.${ camelize(col.columnName) }"
                        placeholder="${ col.config.fieldLabel }"/>
        <#elseif col.config.fieldType == 'radio'>
          <#if col.config.data.source.category == 'd'>
          <b-form-radio id="${ camelize(col.columnName) }Input"
                        name="${camelize(col.columnName)}"
                        v-for="item in ${ camelize(col.columnName) }Options"
                        :value="item.paramCode"
                        v-model.lazy.trim="form.${ camelize(col.columnName) }">
            {{ item.paramName }}
          </b-form-radio>
          <#else>
          <b-form-radio id="${ camelize(col.columnName) }Input"
                        name="${camelize(col.columnName)}"
                        v-for="item in ${ camelize(col.config.data.source.name) }Options"
                        :value="item.${ col.config.data.source.value }"
                        v-model.lazy.trim="form.${ camelize(col.columnName) }">
            {{ item.${ col.config.data.source.label } }}
          </b-form-radio>
          </#if>
        <#elseif col.config.fieldType == 'checkbox'>
          <b-form-checkbox-group id="${ camelize(col.columnName) }Input"
                                 name="${camelize(col.columnName)}"
                                 stacked
                                 v-model.lazy.trim="form.${ camelize(col.columnName) }"
                                 :plain="true">
          <#if col.config.data.source.category == 'd'>
            <b-form-checkbox v-for="item in ${ camelize(col.tableName) }Options"
                             value="time.paramCode">
              {{ item.paramName }}
            </b-form-checkbox>
          <#else>
            <b-form-checkbox v-for="item in ${ camelize(col.config.data.source.name) }Options"
                             value="item.${ col.config.data.source.value }">
              {{ item.${ col.config.data.source.label } }}
            </b-form-checkbox>
          </#if>
          </b-form-checkbox-group>
        <#elseif col.config.fieldType == 'select'>
          <#if col.config.data.source.category == 'd'>
          <b-form-select id="${ camelize(col.columnName) }Input"
                        name="${camelize(col.columnName)}"
                        v-model.lazy.trim="form.${ camelize(col.columnName) }"
                        :options="${ camelize(col.columnName) }Options"
                        value-field="paramCode"
                        text-field="paramName"/>
          <#else>
          <b-form-select id="${ camelize(col.columnName) }Input"
                         name="${camelize(col.columnName)}"
                         v-model.lazy.trim="form.${ camelize(col.columnName) }"
                         :options="${ camelize(col.config.data.source.name) }Options"
                         value-field="${ camelize(col.config.data.source.value) }"
                         text-field="${ camelize(col.config.data.source.label) }"/>
          </#if>
        <#else>
          <b-form-input id="${ camelize(col.columnName) }Input"
                        type="text"
                        v-model.lazy.trim="form.${ camelize(col.columnName) }"
                        placeholder="${ col.config.fieldLabel }"/>
        </#if>
        </b-form-group>
      </b-col>
      </#if>
    </#list>
    </b-row>
  </b-form>
</template>

<script>
<#assign importTableSeq = []>
<#list root.columns as col >
  <#if col.config.isQuery>
    <#if col.config.fieldType == 'radio' || col.config.fieldType == 'checkbox' || col.config.fieldType == 'select'>
      <#if col.config.data.source.category == 'd'>
        <#assign tableName = 'paramInfo' >
        <#if !importTableSeq?seq_contains(tableName)>
          <#assign importTableSeq = importTableSeq + [tableName]>
import { query as ${tableName}Query } from "@/api/${tableName}"
        </#if>
      <#else>
        <#assign tableName = camelize(col.config.data.source.name) >
        <#if !importTableSeq?seq_contains(tableName)>
          <#assign importTableSeq = importTableSeq + [tableName]>
import { queryAll as ${tableName}QueryAll } from "@/api/${tableName}"
        </#if>
      </#if>
    </#if>
  </#if>
</#list>
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
<#list root.columns as col >
  <#if col.config.isQuery>
    <#if col.config.fieldType == 'radio' || col.config.fieldType == 'checkbox' || col.config.fieldType == 'select'>
      <#if col.config.data.source.category == 'd'>
      ${ camelize(col.columnName) }Options: [],
      <#else>
      ${ camelize(col.config.data.source.name) }Options: [],
      </#if>
    </#if>
  </#if>
</#list>
      submitted: false
    }
  },
  computed: {
    form() { return this.paramForm }
  },
  created() {
<#assign createdStr>
<#list root.columns as col >
  <#if col.config.isQuery>
    <#if col.config.fieldType == 'radio' || col.config.fieldType == 'checkbox' || col.config.fieldType == 'select'>
      <#if col.config.data.source.category == 'd'>
    this.getParamInfoList(col.config.data.source.name, '${ camelize(col.columnName) }Options') {
      ${t}QueryAll().then(response => {
        this.${t}Options = response.data
      }).catch(function(err) {
        console.log(err)
      })
    },
      </#if>
    </#if>
  </#if>
</#list>
<#list importTableSeq as t >
    <#if t != 'paramInfo'>
    this.get${t?cap_first}List(),
    </#if>
</#list>
</#assign>
<#if createdStr?trim?length gt 2>
    ${ createdStr?trim[0..createdStr?trim?length-2] }
</#if>
  },
  methods: {
<#list importTableSeq as t >
  <#if t == 'paramInfo'>
    get${t?cap_first}List(id, p) {
      const params = {}
      const data = {}
      data.typeId = id
      params.page = 1000
      params.size = 1000
      ${t}Query(params, data).then(response => {
        this[p] = response.data
      }).catch(function(err) {
        console.log(err)
      })
    },
    <#else>
    get${t?cap_first}List() {
      ${t}QueryAll().then(response => {
        this.${t}Options = response.data
      }).catch(function(err) {
        console.log(err)
      })
    },
  </#if>
</#list>
    validate() {
    }
  }
}
</script>
