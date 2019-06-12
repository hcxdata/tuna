<template>
  <b-form>
    <b-row>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group id="departmentNameGroup"
                      label="部门编码"
                      label-for="departmentNameInput">
          <b-form-input id="departmentNameInput"
                        v-model.lazy.trim="$v.form.departmentName.$model"
                        :state="chkState('departmentName')"
                        type="text"
                        aria-describedby="departmentNameFeedback"
                        placeholder="部门编码"/>
          <b-form-invalid-feedback id="departmentNameFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group id="showNameGroup"
                      label="部门名称"
                      label-for="showNameInput">
          <b-form-input id="showNameInput"
                        v-model.lazy.trim="$v.form.showName.$model"
                        :state="chkState('showName')"
                        type="text"
                        aria-describedby="showNameFeedback"
                        placeholder="部门名称"/>
          <b-form-invalid-feedback id="showNameFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group id="descriptionGroup"
                      label="部门描述"
                      label-for="descriptionInput">
          <b-form-input id="descriptionInput"
                        v-model.lazy.trim="$v.form.description.$model"
                        :state="chkState('description')"
                        type="textarea"
                        aria-describedby="descriptionFeedback"
                        placeholder="部门描述"/>
          <b-form-invalid-feedback id="descriptionFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group id="createdAtGroup"
                      label="创建时间"
                      label-for="createdAtInput">
          <datetime input-id="issuePlanEnded"
                    v-model.lazy.trim="$v.form.createdAt.$model"
                    :state="chkState('createdAt')"
                    :value-zone="'local'"
                    type="datetime"
                    format="yyyy-MM-dd HH:mm"
                    aria-describedby="createdAtFeedback"
                    class="theme-primary"
                    placeholder="创建时间"/>
          <b-form-invalid-feedback id="createdAtFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group id="updatedAtGroup"
                      label="更新时间"
                      label-for="updatedAtInput">
          <datetime input-id="issuePlanEnded"
                    v-model.lazy.trim="$v.form.updatedAt.$model"
                    :state="chkState('updatedAt')"
                    :value-zone="'local'"
                    type="datetime"
                    format="yyyy-MM-dd HH:mm"
                    aria-describedby="updatedAtFeedback"
                    class="theme-primary"
                    placeholder="更新时间"/>
          <b-form-invalid-feedback id="updatedAtFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-form-group id="parentIdGroup"
                      label="parent_id"
                      label-for="parentIdInput">
          <b-form-select id="parentIdInput"
                         name="parentId"
                         v-model.lazy.trim="$v.form.parentId.$model"
                         :state="chkState('parentId')"
                         :options="departmentsOptions"
                         value-field="departmentName"
                         text-field="showName"
                         aria-describedby="parentIdFeedback"/>
          <b-form-invalid-feedback id="parentIdFeedback">
            不能为空
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
    </b-row>
  </b-form>
</template>

<script>
import { queryAll as departmentsQueryAll } from "@/api/departments"
import { validationMixin } from "vuelidate"

import { required, minLength, maxLength, maxValue, minValue, email, sameAs, helpers } from "vuelidate/lib/validators"

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
      departmentsOptions: [],
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
      departmentName: {
        required,
        maxLength: maxLength(32)
      },
      showName: {
        required,
        maxLength: maxLength(32)
      },
      description: {
        maxLength: maxLength(256)
      },
      createdAt: {
        required
      },
      updatedAt: {
        required
      },
      parentId: {
      }
    }
  },
  created() {
    this.getDepartmentsList()
  },
  methods: {
    getDepartmentsList() {
      departmentsQueryAll().then(response => {
        this.departmentsOptions = response.data
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
