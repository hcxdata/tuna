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
      <#if col.config.isEdit == true>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group id="${ camelize(col.columnName) }Group"
                      label="${ col.config.fieldLabel }"
                      label-for="${ camelize(col.columnName) }Input">
          <#if col.config.fieldType == 'number'>
          <b-form-input id="${ camelize(col.columnName) }Input"
                        v-model.lazy.trim="$v.form.${ camelize(col.columnName) }.$model"
                        :state="chkState('${ camelize(col.columnName) }')"
                        type="number"
                        aria-describedby="${ camelize(col.columnName) }Feedback"
                        placeholder="${ col.config.fieldLabel }"/>
          <#elseif col.config.fieldType == 'datetime'>
          <datetime input-id="issuePlanEnded"
                    v-model.lazy.trim="$v.form.${ camelize(col.columnName) }.$model"
                    :state="chkState('${ camelize(col.columnName) }')"
                    :value-zone="'local'"
                    type="datetime"
                    format="yyyy-MM-dd HH:mm"
                    aria-describedby="${ camelize(col.columnName) }Feedback"
                    class="theme-primary"
                    placeholder="${ col.config.fieldLabel }"/>
          <#elseif col.config.fieldType == 'date'>
          <datetime input-id="issuePlanEnded"
                    v-model.lazy.trim="$v.form.${ camelize(col.columnName) }.$model"
                    :state="chkState('${ camelize(col.columnName) }')"
                    :value-zone="'local'"
                    type="datetime"
                    format="yyyy-MM-dd"
                    aria-describedby="${ camelize(col.columnName) }Feedback"
                    class="theme-primary"
                    placeholder="${ col.config.fieldLabel }"/>
          <#elseif col.config.fieldType == 'time'>
          <datetime input-id="issuePlanEnded"
                    v-model.lazy.trim="$v.form.${ camelize(col.columnName) }.$model"
                    :state="chkState('${ camelize(col.columnName) }')"
                    :value-zone="'local'"
                    type="time"
                    aria-describedby="${ camelize(col.columnName) }Feedback"
                    class="theme-primary"
                    placeholder="${ col.config.fieldLabel }"/>
          <#elseif col.config.fieldType == 'textarea'>
          <b-form-input id="${ camelize(col.columnName) }Input"
                        v-model.lazy.trim="$v.form.${ camelize(col.columnName) }.$model"
                        :state="chkState('${ camelize(col.columnName) }')"
                        type="textarea"
                        aria-describedby="${ camelize(col.columnName) }Feedback"
                        placeholder="${ col.config.fieldLabel }"/>
          <#elseif col.config.fieldType == 'radio'>
          <#if col.config.data.source.category == 'd'>
          <b-form-radio id="${ camelize(col.columnName) }Input"
                        name="${camelize(col.columnName)}"
                        v-for="item in ${ camelize(col.columnName) }Options"
                        :value="item.paramCode"
                        v-model.lazy.trim="$v.form.${ camelize(col.columnName) }.$model"
                        :state="chkState('${ camelize(col.columnName) }')"
                        aria-describedby="${ camelize(col.columnName) }Feedback">
            {{ item.paramName }}
          </b-form-radio>
          <#else>
          <b-form-radio id="${ camelize(col.columnName) }Input"
                        name="${camelize(col.columnName)}"
                        v-for="item in ${ camelize(col.config.data.source.name) }Options"
                        :value="item.${ col.config.data.source.value }"
                        v-model.lazy.trim="$v.form.${ camelize(col.columnName) }.$model"
                        :state="chkState('${ camelize(col.columnName) }')"
                        aria-describedby="${ camelize(col.columnName) }Feedback">
            {{ item.${ col.config.data.source.label } }}
          </b-form-radio>
          </#if>
          <#elseif col.config.fieldType == 'checkbox'>
          <b-form-checkbox-group id="${ camelize(col.columnName) }Input"
                                 name="${camelize(col.columnName)}"
                                 stacked
                                 v-model.lazy.trim="$v.form.${ camelize(col.columnName) }.$model"
                                 :state="chkState('${ camelize(col.columnName) }')"
                                 :plain="true"
                                 aria-describedby="${ camelize(col.columnName) }Feedback">
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
                        v-model.lazy.trim="$v.form.${ camelize(col.columnName) }.$model"
                        :state="chkState('${ camelize(col.columnName) }')"
                        :options="${ camelize(col.columnName) }Options"
                        value-field="paramCode"
                        text-field="paramName"
                        aria-describedby="${ camelize(col.columnName) }Feedback"/>
          <#else>
          <b-form-select id="${ camelize(col.columnName) }Input"
                         name="${camelize(col.columnName)}"
                         v-model.lazy.trim="$v.form.${ camelize(col.columnName) }.$model"
                         :state="chkState('${ camelize(col.columnName) }')"
                         :options="${ camelize(col.config.data.source.name) }Options"
                         value-field="${ camelize(col.config.data.source.value) }"
                         text-field="${ camelize(col.config.data.source.label) }"
                         aria-describedby="${ camelize(col.columnName) }Feedback"/>
          </#if>
          <#elseif col.config.fieldType == 'textarea'>
          <b-form-input id="${ camelize(col.columnName) }Input"
                        v-model.lazy.trim="$v.form.${ camelize(col.columnName) }.$model"
                        :state="chkState('${ camelize(col.columnName) }')"
                        type="textarea"
                        aria-describedby="${ camelize(col.columnName) }Feedback"
                        placeholder="${ col.config.fieldLabel }"/>
          <#else>
          <b-form-input id="${ camelize(col.columnName) }Input"
                        v-model.lazy.trim="$v.form.${ camelize(col.columnName) }.$model"
                        :state="chkState('${ camelize(col.columnName) }')"
                        type="text"
                        aria-describedby="${ camelize(col.columnName) }Feedback"
                        placeholder="${ col.config.fieldLabel }"/>
          </#if>
          <b-form-invalid-feedback id="${ camelize(col.columnName) }Feedback">
            不能为空
          </b-form-invalid-feedback>
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
</#list>
import { validationMixin } from "vuelidate"

import { required, minLength, maxLength, maxValue, minValue, email, sameAs, helpers } from "vuelidate/lib/validators"

<#list root.columns as col >
  <#if col.config.pattern!''?length gt 0>
const ${camelize(col.columnName)}Pattern = helpers.regex('${camelize(col.columnName)}Pattern', /${col.config.pattern}/)
  </#if>
</#list>
export default {
  name: "baseForm",
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
  <#if col.config.fieldType == 'radio' || col.config.fieldType == 'checkbox' || col.config.fieldType == 'select'>
    <#if col.config.data.source.category == 'd'>
      ${ camelize(col.columnName) }Options: [],
    <#else>
      ${ camelize(col.config.data.source.name) }Options: [],
    </#if>
  </#if>
</#list>
      submitted: false
    }
  },
  computed: {
    form() { return this.paramForm },
    isValid() { return !this.$v.form.$anyError },
    isDirty() { return this.$v.form.$anyDirty },
    invCheck() { return 'You must accept before submitting' },
  },
  mixins: [validationMixin],
  validations: {
    form: {
<#assign validationsCol>
  <#list root.columns as col >
    <#if col.config.isEdit>
      ${ camelize(col.columnName) }: {
      <#assign validations>
        <#if !col.config.allowBlank>
        required,
        </#if>
        <#if col.config.fieldType == 'textfield' || col.config.fieldType == 'textarea'>
          <#if (col.config.minLength!'0')?number gt 0>
        minLength: minLength(${col.config.minLength}),
          </#if>
          <#if col.maximumLength?is_number && col.maximumLength gt 0 && col.maximumLength lt 2000>
        maxLength: maxLength(${col.maximumLength}),
          <#else>
            <#if (col.config.maxLength!'0')?number gt 0>
        maxLength: maxLength(${col.config.maxLength}),
            </#if>
          </#if>
        <#elseif col.config.fieldType == 'number'>

          <#if (col.config.minValue!'0')?number gt 0>
        min: minValue(${col.config.minValue}),
          </#if>

          <#if (col.config.maxValue!'0')?number gt 0>
        max: maxValue(${col.config.maxValue}),
          </#if>

          <#if col.config.pattern!''?length gt 0>
        ${camelize(col.columnName)}Pattern,
          </#if>

        </#if>
      </#assign>
        <#if validations?trim?length gt 2>
        ${ validations?trim[0..validations?trim?length-2] }
        </#if>
      },
    </#if>
  </#list>
</#assign>
    <#if validationsCol?trim?length gt 2>
      ${ validationsCol?trim[0..validationsCol?trim?length-2] }
    </#if>
    }
  },
  created() {
<#assign createdStr>
  <#list root.columns as col >
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
    chkState(val) {
      const field = this.$v.form[val]
      return !field.$dirty || !field.$invalid
    },
    findFirstError(component = this) {
      if (component.state === false) {
        if (component.$refs.input) {
          component.$refs.input.focus()
          return true
        }
        if (component.$refs.check) {
          component.$refs.check.focus()
          return true
        }
      }
      let focused = false
      component.$children.some((child) => {
        focused = this.findFirstError(child)
        return focused
      })

      return focused
    },
    validate() {
      this.$v.$touch()
      this.$nextTick(() => this.findFirstError())
      return this.isValid
    }
  }
}
</script>
