<template>
  <b-form>
    <b-row>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="proviceCodeGroup"
          label="省属代码"
          label-for="proviceCodeInput">
          <b-form-input
            id="proviceCodeInput"
            v-model.lazy.trim="$v.form.proviceCode.$model"
            :state="chkState('proviceCode')"
            type="text"
            aria-describedby="proviceCodeFeedback"
            placeholder="省属代码"/>
          <b-form-invalid-feedback id="proviceCodeFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="proviceNameGroup"
          label="省属名称"
          label-for="proviceNameInput">
          <b-form-input
            id="proviceNameInput"
            v-model.lazy.trim="$v.form.proviceName.$model"
            :state="chkState('proviceName')"
            type="text"
            aria-describedby="proviceNameFeedback"
            placeholder="省属名称"/>
          <b-form-invalid-feedback id="proviceNameFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group
          id="postCodeGroup"
          label="省属邮编"
          label-for="postCodeInput">
          <b-form-input
            id="postCodeInput"
            v-model.lazy.trim="$v.form.postCode.$model"
            :state="chkState('postCode')"
            type="text"
            aria-describedby="postCodeFeedback"
            placeholder="省属邮编"/>
          <b-form-invalid-feedback id="postCodeFeedback">
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
      proviceCode: {
        required,
        maxLength: maxLength(20)
      },
      proviceName: {
        required,
        maxLength: maxLength(20)
      },
      postCode: {
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
