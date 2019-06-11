<template>
  <div class="animated fadeIn">
    <b-row>
      <b-col lg="12">
        <b-form novalidate @submit.prevent="onSubmit" @reset.prevent="onReset">
          <b-form-group
            id="departmentNameInputGroup"
            label="部门名称"
            label-for="departmentName">
            <b-form-input
              id="departmentName"
              v-model.lazy.trim="$v.form.departmentName.$model"
              :state="chkState('departmentName')"
              type="text"
              aria-describedby="departmentNameFeedback"
              placeholder="部门名称"
              autofocus />
            <b-form-invalid-feedback
              id="departmentNameFeedback">
              不能为空
            </b-form-invalid-feedback>
          </b-form-group>
          <b-form-group
            id="showNameInputGroup"
            label="部门显示名"
            label-for="showName">
            <b-form-input
              id="showName"
              v-model.lazy.trim="$v.form.showName.$model"
              :state="chkState('showName')"
              type="text"
              aria-describedby="showNameLiveFeedback"
              placeholder="部门显示名" />
            <b-form-invalid-feedback id="showNameLiveFeedback">
              不能为空
            </b-form-invalid-feedback>
          </b-form-group>
          <b-form-group
            id="parentIdInputGroup"
            label="上级部门"
            label-for="parentId">
            <treeselect
              v-model.lazy.trim="$v.form.parentId.$model"
              :multiple="false"
              :options="options"
              :default-expand-level="2"
              :class="{'is-valid': chkState('parentId'), 'is-invalid': !chkState('parentId')}"
              placeholder="请选择上级部门" />
            <b-form-invalid-feedback id="parentIdLiveFeedback" :force-show="!chkState('parentId')">
              不能为空
            </b-form-invalid-feedback>
          </b-form-group>
          <b-form-group
            id="descriptionInputGroup"
            label="部门描述"
            label-for="description">
            <b-form-input
              id="description"
              v-model.lazy.trim="$v.form.description.$model"
              :state="chkState('description')"
              type="text"
              aria-describedby="descriptionLiveFeedback"
              placeholder="部门描述" />
            <b-form-invalid-feedback id="descriptionLiveFeedback">
              不能为空
            </b-form-invalid-feedback>
          </b-form-group>
          <b-button :disabled="$v.form.$invalid || submitted" type="submit" variant="primary">
            提交
          </b-button>
          <b-button class="ml-1" variant="success" @click="hiddenModal">
            返回
          </b-button>
        </b-form>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { validationMixin } from 'vuelidate'
import { tree2Options } from '@/utils'
import Treeselect from '@riophae/vue-treeselect'

import { required, minLength } from 'vuelidate/lib/validators'

import { create, update } from '@/api/departments'

import '@riophae/vue-treeselect/dist/vue-treeselect.css'

const defaultForm = {
  departmentName: '',
  showName: '',
  parentId: 0,
  description: ''
}

export default {
  components: { Treeselect },
  mixins: [validationMixin],
  props: {
    isEdit: {
      type: Boolean,
      default: function() {
        return false
      }
    },
    item: {
      type: Object,
      default: function() {
        return {}
      }
    },
    treeData: {
      type: Array,
      default: function() {
        return []
      }
    }
  },
  data() {
    return {
      form: Object.assign({ departmentName: '', showName: '', parentId: 0, description: '' }, this.item),
      feedBack: 'secondary',
      submitted: false
    }
  },
  computed: {
    formStr() {
      return JSON.stringify(this.form, null, 4)
    },
    isValid() {
      return !this.$v.form.$anyError
    },
    isDirty() {
      return this.$v.form.$anyDirty
    },
    invCheck() {
      return 'You must accept before submitting'
    },
    options() {
      const res = []
      res.push({ id: 0, label: '顶级部门' })
      this.treeData.forEach(function(element) {
        res.push(tree2Options(element, { label: 'showName' }))
      })
      return res
    }
  },
  validations: {
    form: {
      departmentName: {
        required,
        minLength: minLength(1)
      },
      showName: {
        required,
        minLength: minLength(1)
      },
      parentId: {
        required
      },
      description: {
      }
    }
  },
  methods: {
    onSubmit() {
      if (this.validate()) {
        this.$nextTick(() => {
          const targetForm = Object.assign({}, this.form)
          if (targetForm.parentId === 0) {
            targetForm.parentId = null
          }
          if (this.isEdit) {
            update(targetForm).then(response => {
              this.showSuccessMsg()
              this.form = defaultForm
              this.$emit('updateFreshByModal', false)
            }).catch(function(err) {
              console.log(err)
            })
          } else {
            create(targetForm).then(response => {
              this.showSuccessMsg()
              this.form = defaultForm
              this.$emit('updateFreshByModal', false)
            }).catch(function(err) {
              console.log(err)
            })
          }
          // this.submitted = true
          this.feedBack = 'info'
        })
      }
    },
    hiddenModal() {
      this.form = defaultForm
      this.$emit('update:editModal', false)
    },
    onReset() {
      // Reset validation
      this.form = Object.assign({}, defaultForm)
      this.submitted = false
      this.$nextTick(() => {
        this.$v.$reset()
        this.feedBack = 'secondary'
      })
    },
    fetchData(id) {
      fetch(id).then(response => {
        this.form = response.data
      }).catch(err => {
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
  },
  notifications: {
    showSuccessMsg: {
      type: 'success',
      message: '操作成功!'
    }
  }
}
</script>

<style scoped>
.btn.disabled {
  cursor: auto;
}
</style>
