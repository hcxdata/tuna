<template>
  <b-form>
    <b-row>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="salesAreaCodeGroup"
          label="sales_area_code"
          label-for="salesAreaCodeInput">
          <b-form-input
            id="salesAreaCodeInput"
            v-model.lazy.trim="$v.form.salesAreaCode.$model"
            :state="chkState('salesAreaCode')"
            type="text"
            aria-describedby="salesAreaCodeFeedback"
            placeholder="sales_area_code"/>
          <b-form-invalid-feedback id="salesAreaCodeFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="salesAreaNameGroup"
          label="sales_area_name"
          label-for="salesAreaNameInput">
          <b-form-input
            id="salesAreaNameInput"
            v-model.lazy.trim="$v.form.salesAreaName.$model"
            :state="chkState('salesAreaName')"
            type="text"
            aria-describedby="salesAreaNameFeedback"
            placeholder="sales_area_name"/>
          <b-form-invalid-feedback id="salesAreaNameFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
    </b-row>
  </b-form>
</template>

<script>
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
      salesAreaCode: {
        required,
        maxLength: maxLength(20)
      },
      salesAreaName: {
        required,
        maxLength: maxLength(20)
      }
    }
  },
  created() {
  },
  methods: {
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
