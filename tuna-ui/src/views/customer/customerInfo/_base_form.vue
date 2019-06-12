<template>
  <b-form>
    <b-row>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="custNameGroup"
          label="客户名称"
          label-for="custNameInput">
          <b-form-input
            id="custNameInput"
            v-model.lazy.trim="$v.form.custName.$model"
            :state="chkState('custName')"
            type="text"
            aria-describedby="custNameFeedback"
            placeholder="客户名称"/>
          <b-form-invalid-feedback id="custNameFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="custAliasGroup"
          label="客户别名"
          label-for="custAliasInput">
          <b-form-input
            id="custAliasInput"
            v-model.lazy.trim="$v.form.custAlias.$model"
            :state="chkState('custAlias')"
            type="text"
            aria-describedby="custAliasFeedback"
            placeholder="客户别名"/>
          <b-form-invalid-feedback id="custAliasFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="salesAreaCodeGroup"
          label="销售区域"
          label-for="salesAreaCodeInput">
          <b-form-select
            id="salesAreaCodeInput"
            v-model.lazy.trim="$v.form.salesAreaCode.$model"
            :state="chkState('salesAreaCode')"
            :options="salesAreaDictOptions"
            name="salesAreaCode"
            value-field="salesAreaCode"
            text-field="salesAreaName"
            aria-describedby="salesAreaCodeFeedback"/>
          <b-form-invalid-feedback id="salesAreaCodeFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="custProviceCodeGroup"
          label="客户省属"
          label-for="custProviceCodeInput">
          <b-form-select
            id="custProviceCodeInput"
            v-model.lazy.trim="$v.form.custProviceCode.$model"
            :state="chkState('custProviceCode')"
            :options="proviceDictOptions"
            name="custProviceCode"
            value-field="proviceCode"
            text-field="proviceName"
            aria-describedby="custProviceCodeFeedback"/>
          <b-form-invalid-feedback id="custProviceCodeFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="custCityCodeGroup"
          label="客户市属"
          label-for="custCityCodeInput">
          <b-form-select
            id="custCityCodeInput"
            v-model.lazy.trim="$v.form.custCityCode.$model"
            :state="chkState('custCityCode')"
            :options="cityDictOptions"
            name="custCityCode"
            value-field="cityCode"
            text-field="cityName"
            aria-describedby="custCityCodeFeedback"/>
          <b-form-invalid-feedback id="custCityCodeFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="custAddressGroup"
          label="客户地址"
          label-for="custAddressInput">
          <b-form-input
            id="custAddressInput"
            v-model.lazy.trim="$v.form.custAddress.$model"
            :state="chkState('custAddress')"
            type="textarea"
            aria-describedby="custAddressFeedback"
            placeholder="客户地址"/>
          <b-form-invalid-feedback id="custAddressFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="custGradeGroup"
          label="客户等级"
          label-for="custGradeInput">
          <b-form-input
            id="custGradeInput"
            v-model.lazy.trim="$v.form.custGrade.$model"
            :state="chkState('custGrade')"
            type="number"
            aria-describedby="custGradeFeedback"
            placeholder="客户等级"/>
          <b-form-invalid-feedback id="custGradeFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="custWebsiteGroup"
          label="客户网站"
          label-for="custWebsiteInput">
          <b-form-input
            id="custWebsiteInput"
            v-model.lazy.trim="$v.form.custWebsite.$model"
            :state="chkState('custWebsite')"
            type="text"
            aria-describedby="custWebsiteFeedback"
            placeholder="客户网站"/>
          <b-form-invalid-feedback id="custWebsiteFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="custEnableGroup"
          label="客户可用"
          label-for="custEnableInput">
          <b-form-input
            id="custEnableInput"
            v-model.lazy.trim="$v.form.custEnable.$model"
            :state="chkState('custEnable')"
            type="text"
            aria-describedby="custEnableFeedback"
            placeholder="客户可用"/>
          <b-form-invalid-feedback id="custEnableFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="initalCreateDateGroup"
          label="初建日期"
          label-for="initalCreateDateInput">
          <datetime
            v-model.lazy.trim="$v.form.initalCreateDate.$model"
            :state="chkState('initalCreateDate')"
            :value-zone="'local'"
            input-id="issuePlanEnded"
            type="datetime"
            format="yyyy-MM-dd HH:mm"
            aria-describedby="initalCreateDateFeedback"
            class="theme-primary"
            placeholder="初建日期"/>
          <b-form-invalid-feedback id="initalCreateDateFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="initalCreatorIdGroup"
          label="初建人员"
          label-for="initalCreatorIdInput">
          <b-form-input
            id="initalCreatorIdInput"
            v-model.lazy.trim="$v.form.initalCreatorId.$model"
            :state="chkState('initalCreatorId')"
            type="number"
            aria-describedby="initalCreatorIdFeedback"
            placeholder="初建人员"/>
          <b-form-invalid-feedback id="initalCreatorIdFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="descriptionGroup"
          label="备注"
          label-for="descriptionInput">
          <b-form-input
            id="descriptionInput"
            v-model.lazy.trim="$v.form.description.$model"
            :state="chkState('description')"
            type="textarea"
            aria-describedby="descriptionFeedback"
            placeholder="备注"/>
          <b-form-invalid-feedback id="descriptionFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
    </b-row>
  </b-form>
</template>

<script>
import { queryAll as salesAreaDictQueryAll } from '@/api/salesAreaDict'
import { queryAll as proviceDictQueryAll } from '@/api/proviceDict'
import { queryAll as cityDictQueryAll } from '@/api/cityDict'
import { validationMixin } from 'vuelidate'

import { required, minLength, maxLength, maxValue, minValue, email, sameAs, helpers } from 'vuelidate/lib/validators'

export default {
  name: 'BaseForm',
  mixins: [validationMixin],
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
      proviceDictOptions: [],
      cityDictOptions: [],
      submitted: false
    }
  },
  computed: {
    form() { return this.paramForm },
    isValid() { return !this.$v.form.$anyError },
    isDirty() { return this.$v.form.$anyDirty },
    invCheck() { return 'You must accept before submitting' }
  },
  validations: {
    form: {
      custName: {
        required,
        maxLength: maxLength(100)
      },
      custAlias: {
        required,
        maxLength: maxLength(100)
      },
      salesAreaCode: {
      },
      custProviceCode: {
      },
      custCityCode: {
      },
      custAddress: {
        maxLength: maxLength(200)
      },
      custGrade: {
      },
      custWebsite: {
        maxLength: maxLength(100)
      },
      custEnable: {
      },
      initalCreateDate: {
        required
      },
      initalCreatorId: {
        required
      },
      description: {
        maxLength: maxLength(200)
      }
    }
  },
  created() {
    this.getSalesAreaDictList(),
    this.getProviceDictList(),
    this.getCityDictList()
  },
  methods: {
    getSalesAreaDictList() {
      salesAreaDictQueryAll().then(response => {
        this.salesAreaDictOptions = response.data
      }).catch(function(err) {
        console.log(err)
      })
    },
    getProviceDictList() {
      proviceDictQueryAll().then(response => {
        this.proviceDictOptions = response.data
      }).catch(function(err) {
        console.log(err)
      })
    },
    getCityDictList() {
      cityDictQueryAll().then(response => {
        this.cityDictOptions = response.data
      }).catch(function(err) {
        console.log(err)
      })
    },
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
