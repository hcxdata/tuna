<template>
  <div class="app flex-row align-items-center">
    <div class="container">
      <b-row class="justify-content-center">
        <b-col md="8">
          <b-card-group>
            <b-card no-body class="p-4">
              <b-card-body>
                <b-form ref="loginForm" :model="loginForm" :rules="loginRules">
                  <h1>{{ $t('login.title') }}</h1>
                  <p class="text-muted">{{ $t('login.secondTitle') }}</p>
                  <b-input-group class="mb-3">
                    <b-input-group-prepend>
                      <b-input-group-text>
                        <i class="icon-user"/>
                      </b-input-group-text>
                    </b-input-group-prepend>
                    <b-form-input v-model="loginForm.username" :placeholder="$t('login.username')" type="text" class="form-control" autocomplete="username email" />
                  </b-input-group>
                  <b-input-group class="mb-4">
                    <b-input-group-prepend>
                      <b-input-group-text>
                        <i class="icon-lock"/>
                      </b-input-group-text>
                    </b-input-group-prepend>
                    <b-form-input v-model="loginForm.password" :placeholder="$t('login.password')" :type="passwordType" class="form-control" autocomplete="current-password" @keyup.enter.native="handleLogin"/>
                  </b-input-group>
                  <b-row>
                    <b-col cols="6">
                      <b-button variant="primary" class="px-4" @click.prevent="handleLogin" >{{ $t('login.logIn') }}</b-button>
                    </b-col>
                    <b-col cols="6" class="text-right">
                      <b-button variant="link" class="px-0">{{ $t('login.forgotPassword') }}</b-button>
                    </b-col>
                  </b-row>
                </b-form>
              </b-card-body>
            </b-card>
            <b-card no-body class="text-white py-5 d-md-down-none" style="width:44%">
              <b-card-body class="text-center">
                <div>
                  <img src="static/img/automotive.png" width="100%" >
                </div>
              </b-card-body>
            </b-card>
          </b-card-group>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import { isvalidUsername } from '@/utils/validate'
export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!isvalidUsername(value)) {
        callback(new Error('Please enter the correct user name'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('The password can not be less than 6 digits'))
      } else {
        callback()
      }
    }
    return {
      errors: [],
      loginForm: { },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      passwordType: 'password',
      loading: false,
      showDialog: false,
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }

  },
  created() {
    // window.addEventListener('hashchange', this.afterQRScan)
  },
  destroyed() {
    // window.removeEventListener('hashchange', this.afterQRScan)
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
    },
    handleLogin(e) {
      this.errors = []

      if (!this.loginForm.username) {
        this.errors.push('Name required.')
      }

      if (!this.loginForm.password) {
        this.errors.push('Password required.')
      }

      if (!this.errors.length) {
        this.loading = true
        this.$store.dispatch('LoginByUsername', this.loginForm).then(() => {
          this.loading = false
          this.$router.push({ path: this.redirect || '/' })
        }).catch(() => {
          this.loading = false
        })
      } else {
        e.preventDefault()
      }
    }
  }
}
</script>
