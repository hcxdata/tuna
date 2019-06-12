<template>
  <b-form>
    <b-row>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="cityCodeGroup"
          label="地市代码"
          label-for="cityCodeInput">
          <b-form-input
            id="cityCodeInput"
            v-model.lazy.trim="$v.form.cityCode.$model"
            :state="chkState('cityCode')"
            type="text"
            aria-describedby="cityCodeFeedback"
            placeholder="地市代码"/>
          <b-form-invalid-feedback id="cityCodeFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="cityNameGroup"
          label="地市名称"
          label-for="cityNameInput">
          <b-form-input
            id="cityNameInput"
            v-model.lazy.trim="$v.form.cityName.$model"
            :state="chkState('cityName')"
            type="text"
            aria-describedby="cityNameFeedback"
            placeholder="地市名称"/>
          <b-form-invalid-feedback id="cityNameFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="cityPostCodeGroup"
          label="地市邮编"
          label-for="cityPostCodeInput">
          <b-form-input
            id="cityPostCodeInput"
            v-model.lazy.trim="$v.form.cityPostCode.$model"
            :state="chkState('cityPostCode')"
            type="text"
            aria-describedby="cityPostCodeFeedback"
            placeholder="地市邮编"/>
          <b-form-invalid-feedback id="cityPostCodeFeedback">
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
      cityCode: {
        required,
        maxLength: maxLength(20)
      },
      cityName: {
        required,
        maxLength: maxLength(20)
      },
      cityPostCode: {
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
