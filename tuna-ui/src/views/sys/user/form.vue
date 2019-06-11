<template>
  <div class="animated fadeIn">
    <b-card no-body>
      <b-card-header>
        <i class="icon-note"/> 用户信息
      </b-card-header>
      <b-card-body>
        <b-form novalidate @submit.prevent="onSubmit" @reset.prevent="onReset">
          <cite>个人信息</cite>
          <hr>
          <b-row>
            <b-col lg="6">
              <b-form-group
                id="realNameInputGroup"
                label="用户姓名"
                label-for="realName">
                <b-form-input
                  id="realName"
                  v-model.trim="$v.form.realName.$model"
                  :state="chkState('realName')"
                  type="text"
                  aria-describedby="realNameLiveFeedback"
                  placeholder="用户姓名"/>
                <b-form-invalid-feedback id="realNameLiveFeedback">
                  不能为空
                </b-form-invalid-feedback>
              </b-form-group>
              <b-form-group
                id="genderInputGroup3"
                label="性别"
                label-for="gender">
                <treeselect
                  v-model.lazy.trim="$v.form.gender.$model"
                  :multiple="false"
                  :options="[ {id: 'MALE', label: '男'}, {id: 'FEMALE', label: '女'}]"
                  :state="chkState('gender')"
                  placeholder="请选择性别"
                  aria-describedby="genderLiveFeedback"/>
                <b-form-invalid-feedback id="genderLiveFeedback">
                  不能为空
                </b-form-invalid-feedback>
              </b-form-group>
            </b-col>
            <b-col lg="6">
              <b-form-group
                id="signatureInputGroup"
                label="个人签名"
                label-for="signature">
                <b-form-input
                  id="signature"
                  v-model.trim="$v.form.signature.$model"
                  :state="chkState('signature')"
                  type="text"
                  aria-describedby="signatureLiveFeedback"
                  placeholder="个人签名"
                  autocomplete="signature" />
                <b-form-invalid-feedback id="signatureLiveFeedback">
                  不能为空
                </b-form-invalid-feedback>
              </b-form-group>
              <b-form-group
                id="departmentIdInputGroup3"
                label="所属部门"
                label-for="departmentId">
                <treeselect
                  v-model.lazy.trim="$v.form.departmentId.$model"
                  :multiple="false"
                  :options="depOptions"
                  :default-expand-level="2"
                  :state="chkState('departmentId')"
                  placeholder="请选择性别"
                  aria-describedby="departmentIdLiveFeedback"/>
                <b-form-invalid-feedback id="departmentIdLiveFeedback">
                  不能为空
                </b-form-invalid-feedback>
              </b-form-group>
            </b-col>
          </b-row>
          <cite>联系方式</cite>
          <hr>
          <b-row>
            <b-col lg="6">
              <b-form-group
                id="phoneInputGroup"
                label="电话"
                label-for="phone">
                <b-form-input
                  id="phone"
                  v-model.trim="$v.form.phone.$model"
                  :state="chkState('phone')"
                  type="text"
                  aria-describedby="phoneLiveFeedback"
                  placeholder="电话"/>
                <b-form-invalid-feedback id="phoneLiveFeedback">
                  不能为空
                </b-form-invalid-feedback>
              </b-form-group>
              <b-form-group
                id="emailInputGroup4"
                label="邮箱"
                label-for="email">
                <b-form-input
                  id="email"
                  v-model.trim="$v.form.email.$model"
                  :state="chkState('email')"
                  type="email"
                  aria-describedby="emailLiveFeedback"
                  placeholder="邮箱"
                  autocomplete="email"/>
                <b-form-invalid-feedback id="emailLiveFeedback">
                  不能为空
                </b-form-invalid-feedback>
              </b-form-group>
            </b-col>
            <b-col lg="6">
              <b-form-group
                id="officePhoneInputGroup"
                label="公司电话"
                label-for="officePhone">
                <b-form-input
                  id="officePhone"
                  v-model.trim="$v.form.officePhone.$model"
                  :state="chkState('officePhone')"
                  type="text"
                  aria-describedby="officePhoneLiveFeedback"
                  placeholder="公司电话"/>
                <b-form-invalid-feedback id="officePhoneLiveFeedback">
                  不能为空
                </b-form-invalid-feedback>
              </b-form-group>
            </b-col>
          </b-row>
          <cite>登录信息</cite>
          <hr>
          <b-row>
            <b-col lg="6">
              <b-form-group
                id="usernameInputGroup"
                label="登录名"
                label-for="username">
                <b-form-input
                  id="username"
                  v-model.lazy.trim="$v.form.username.$model"
                  :state="chkState('username')"
                  type="text"
                  aria-describedby="usernameLiveFeedback"
                  placeholder="登录名"
                  autofocus />
                <b-form-invalid-feedback id="usernameLiveFeedback">
                  不能为空
                </b-form-invalid-feedback>
              </b-form-group>
            </b-col>
            <b-col lg="6">
              <b-form-group
                id="nicknameInputGroup"
                label="用户昵称"
                label-for="nickname">
                <b-form-input
                  id="nickname"
                  v-model.lazy.trim="$v.form.nickname.$model"
                  :state="chkState('nickname')"
                  type="text"
                  aria-describedby="nicknameLiveFeedback"
                  placeholder="用户昵称" />
                <b-form-invalid-feedback id="nicknameLiveFeedback">
                  不能为空
                </b-form-invalid-feedback>
              </b-form-group>
            </b-col>
          </b-row>
          <b-row v-if="!isEdit">
            <b-col md="6">
              <b-form-group
                id="passwordInputGroup"
                label="密码"
                label-for="password">
                <b-form-input
                  id="password"
                  v-model.trim="$v.form.password.$model"
                  :state="chkState('password')"
                  type="password"
                  aria-describedby="ipasswordLiveFeedback"
                  placeholder="密码"
                  autocomplete="new-password" />
                <b-form-invalid-feedback id="ipasswordLiveFeedback">
                  Required password containing at least: number, uppercase and lowercase letter, 8 characters
                </b-form-invalid-feedback>
              </b-form-group>

            </b-col>
            <b-col md="6">
              <b-form-group
                id="confPasswordInputGroup"
                label="确认密码"
                label-for="confirm_password">
                <b-form-input
                  id="confirm_password"
                  v-model.trim="$v.form.confirmPassword.$model"
                  :state="chkState('confirmPassword')"
                  type="password"
                  aria-describedby="confirmPasswordLiveFeedback"
                  placeholder="确认密码"
                  autocomplete="new-password" />
                <b-form-invalid-feedback id="confirmPasswordLiveFeedback">
                  Passwords must match
                </b-form-invalid-feedback>
              </b-form-group>

            </b-col>
          </b-row>
          <b-button :disabled="$v.form.$invalid || submitted" type="submit" variant="primary">
            提交
          </b-button>
          <b-button :disabled="!isDirty" class="ml-1" type="reset" variant="danger">重置</b-button>
          <router-link :to="'/system/user'">
            <b-button class="ml-1" type="submit" variant="success">
              返回
            </b-button>
          </router-link>
        </b-form>
      </b-card-body>
    </b-card>
  </div>
</template>

<script>
import { validationMixin } from 'vuelidate'
import Treeselect from '@riophae/vue-treeselect'
import { required, minLength, email, sameAs } from 'vuelidate/lib/validators'

import { createSysUser, fetchSysUser, updateSysUser } from '@/api/users'

import { fetchList } from '@/api/departments'
import { list2tree, tree2Options } from '@/utils'

import '@riophae/vue-treeselect/dist/vue-treeselect.css'

const defaultForm = {
  avatar: 'static/img/avatars/1.jpg',
  email: '',
  gender: 'MALE',
  nickname: '',
  officePhone: '',
  password: '',
  phone: '',
  realName: '',
  registeredAt: 0,
  signature: '',
  uid: -1,
  username: '',
  confirmPassword: '',
  departmentId: null
}

export default {
  name: 'UserForm',
  components: { Treeselect },
  mixins: [validationMixin],
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      form: Object.assign({}, defaultForm),
      feedBack: 'secondary',
      submitted: false,
      treeData: []
    }
  },
  computed: {
    formStr() { return JSON.stringify(this.form, null, 4) },
    isValid() { return !this.$v.form.$anyError },
    isDirty() { return this.$v.form.$anyDirty },
    invCheck() { return 'You must accept before submitting' },
    depOptions() {
      const res = []
      this.treeData.forEach(function(element) {
        res.push(tree2Options(element, { label: 'showName' }))
      })
      return res
    }
  },
  created() {
    this.getList()
    if (this.isEdit) {
      const id = this.$route.params && this.$route.params.id
      this.fetchData(id)
    } else {
      this.postForm = Object.assign({}, defaultForm)
    }
  },
  validations: {
    form: {
      username: {
        required,
        minLength: minLength(1)
      },
      nickname: {
        required,
        minLength: minLength(1)
      },
      realName: {
        required,
        minLength: minLength(1)
      },
      gender: {
        required,
        minLength: minLength(1)
      },
      signature: {
        minLength: minLength(1)
      },
      phone: {
        minLength: minLength(1)
      },
      officePhone: {
        minLength: minLength(1)
      },
      email: {
        email
      },
      password: {
        minLength: minLength(3)
      },
      confirmPassword: {
        sameAsPassword: sameAs('password')
      },
      departmentId: {
        required
      }
    }
  },
  methods: {
    getList() {
      fetchList().then(response => {
        this.treeData = list2tree(response.data, { pid: 'parentId', insertParentAttrs: ['showName'] })
      }).catch(function(err) {
        console.log(err)
      })
    },
    onSubmit() {
      if (this.validate()) {
        this.$nextTick(() => {
          if (this.isEdit) {
            updateSysUser(this.form).then(response => {
              this.showSuccessMsg()
              this.$router.push('/system/user')
            }).catch(function(err) {
              console.log(err)
            })
          } else {
            createSysUser(this.form).then(response => {
              this.showSuccessMsg()
              this.$router.push('/system/user')
            }).catch(function(err) {
              console.log(err)
            })
          }
          // this.submitted = true
          this.feedBack = 'info'
        })
      }
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
      fetchSysUser(id).then(response => {
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
