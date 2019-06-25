<template>
  <b-form>
    <b-row>
    </b-row>
  </b-form>
</template>

<script>
import { query as paramInfoQuery } from '@/api/paramInfo'
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
      searchEngineNameOptions: [],
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
    this.getParamInfoList(3, 'searchEngineNameOptions'
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
