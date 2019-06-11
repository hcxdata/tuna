<template>
  <b-form>
    <b-row>
      <b-col lg="12">
        <b-form-group
          id="usernameInputGroup"
          label="岗位名称"
          label-for="roleName">
          <b-form-input
            id="roleName"
            v-model.lazy.trim="$v.form.roleName.$model"
            :state="chkState('roleName')"
            type="text"
            aria-describedby="roleNameLiveFeedback"
            placeholder="岗位名称"
            autofocus />
          <b-form-invalid-feedback
            id="roleNameLiveFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
        <b-form-group
          id="showNameInputGroup"
          label="显示名称"
          label-for="showName">
          <b-form-input
            id="showName"
            v-model.lazy.trim="$v.form.showName.$model"
            :state="chkState('showName')"
            type="text"
            aria-describedby="showNameLiveFeedback"
            placeholder="显示名称" />
          <b-form-invalid-feedback id="showNameLiveFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
        <b-form-group
          id="descriptionInputGroup"
          label="岗位描述"
          label-for="description">
          <b-form-input
            id="description"
            v-model.trim="$v.form.description.$model"
            :state="chkState('description')"
            type="text"
            aria-describedby="descriptionLiveFeedback"
            placeholder="岗位描述"/>
          <b-form-invalid-feedback id="descriptionLiveFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
    </b-row>
  </b-form>
</template>

<script>
import { validationMixin } from 'vuelidate'

import { required, minLength } from 'vuelidate/lib/validators'

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
      feedBack: 'secondary',
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
      roleName: {
        required,
        minLength: minLength(1)
      },
      showName: {
        required,
        minLength: minLength(1)
      },
      description: {
      }
    }
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

<style scoped>
.btn.disabled {
  cursor: auto;
}
</style>
