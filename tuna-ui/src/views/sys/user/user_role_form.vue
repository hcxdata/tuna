<template>
  <div class="animated fadeIn">
    <b-row>
      <b-col lg="12">
        <!--<h6>Simple Form</h6>-->
        <b-form novalidate @submit.prevent="onSubmit" @reset.prevent="onReset">
          <b-form-group
            id="nicknameInputGroup"
            label="登录名"
            label-for="nickname">
            <b-form-input
              id="nickname"
              v-model.lazy.trim="$v.form.nickname.$model"
              :state="chkState('nickname')"
              :disabled="true"
              type="text"
              aria-describedby="nicknameFeedback"
              placeholder="登录名"
              autofocus/>
            <b-form-invalid-feedback id="nicknameFeedback">
              不能为空
            </b-form-invalid-feedback>
          </b-form-group>
          <b-form-group
            id="realnameInputGroup"
            label="姓名"
            label-for="realname">
            <b-form-input
              id="realname"
              v-model.lazy.trim="$v.form.realname.$model"
              :state="chkState('realname')"
              :disabled="true"
              type="text"
              aria-describedby="realnameLiveFeedback"
              placeholder="姓名"/>
            <b-form-invalid-feedback id="realnameLiveFeedback">
              不能为空
            </b-form-invalid-feedback>
          </b-form-group>
          <b-form-group
            id="roleIdsInputGroup"
            label="岗位"
            label-for="roleIds">
            <treeselect
              v-model.lazy.trim="$v.form.roleIds.$model"
              :multiple="true"
              :options="options"
              :default-expand-level="2"
              :class= "{'is-valid': chkState('roleIds'), 'is-invalid': !chkState('roleIds')}"
              placeholder="请分配岗位"/>
            <b-form-invalid-feedback
              id="roleIdsLiveFeedback"
              :force-show="!chkState('roleIds')">
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

import { fetchList } from '@/api/roles'

import { updateSysUserRoles, fetchSysUserRoles } from '@/api/users'

import '@riophae/vue-treeselect/dist/vue-treeselect.css'

const defaultForm = {
  nickname: '',
  realname: '',
  roleIds: []
}

export default {
  components: { Treeselect },
  mixins: [validationMixin],
  props: {
    item: {
      type: Object,
      default: function() {
        return {}
      }
    }
  },
  data() {
    return {
      form: Object.assign({ nickname: '', realname: '', roleIds: [] }, this.item),
      feedBack: 'secondary',
      submitted: false,
      treeData: []
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
      this.treeData.forEach(function(element) {
        res.push(tree2Options(element, { label: 'showName' }))
      })
      return res
    }
  },
  created() {
    this.getUserRoles()
    this.getRoles()
  },
  validations: {
    form: {
      realname: {
        required,
        minLength: minLength(1)
      },
      nickname: {
        required,
        minLength: minLength(1)
      },
      roleIds: {
        required
      }
    }
  },
  methods: {
    getUserRoles() {
      fetchSysUserRoles(this.item.uid).then(response => {
        this.form.roleIds = response.data.roleIds
      }).catch(function(err) {
        console.log(err)
      })
    },
    getRoles() {
      fetchList().then(response => {
        if (response.data.content) {
          this.treeData = response.data.content
        }
      }).catch(function(err) {
        console.log(err)
      })
    },
    onSubmit() {
      if (this.validate()) {
        this.$nextTick(() => {
          // submit
          console.log('submit:', this.formStr)
          if (this.form.uid && this.form.roleIds && this.form.roleIds.length > 0) {
            const data = {}
            data.userId = this.form.uid
            data.roleIds = this.form.roleIds
            updateSysUserRoles(data).then(response => {
              this.showSuccessMsg()
              this.$emit('update:editModal', false)
            }).catch(function(err) {
              console.log(err)
            })
          }
        })
      }
    },
    hiddenModal() {
      this.form = defaultForm
      this.$emit('update:editModal', false)
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
