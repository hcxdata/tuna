<template>
  <b-form>
    <b-row>
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
