<template>

  <div class="animated fadeIn">
    <b-row>
      <b-col >
        <b-card >
          <div slot="header">
            <i class="fa fa-align-justify"/> {{ $t('system.userListTitle') }}
            <div class="card-header-actions">
              <router-link :to="'/system/user/add'">
                <b-button size="sm" block variant="primary"> 添加 </b-button>
              </router-link>
            </div>
          </div>
          <b-table :items="tableItems" :fields="tableFields" stacked="md" class="mb-0 table-outline" responsive="sm" hover>
            <div slot="avatar" slot-scope="item" class="avatar">
              <img :src="item.value.url" class="img-avatar" alt="">
              <span
                :class="{ 'bg-success': item.value.status == 'success', 'bg-warning': item.value.status == 'warning', 'bg-danger': item.value.status == 'danger', 'bg-secondary': item.value.status == '' }"
                class="avatar-status"/>
            </div>
            <div slot="user" slot-scope="item">
              <div>{{ item.value.name }}</div>
              <div class="small text-muted">
                注册: {{ item.value.registered }}
              </div>
            </div>
            <i slot="country" slot-scope="item" :class="flag(item.value.flag)" :title="item.value.flag" :id="item.value.flag" class="h4 mb-0"/>
            <i id="pw" class="flag-icon flag-icon-pw h1" title="pw"/>
            <div slot="nickname" slot-scope="item">
              {{ item.value }}
            </div>
            <div slot="realname" slot-scope="item">
              {{ item.value }}
            </div>
            <div slot="officePhone" slot-scope="item">
              {{ item.value }}
            </div>
            <div slot="phone" slot-scope="item">
              {{ item.value }}
            </div>
            <div slot="email" slot-scope="item">
              {{ item.value }}
            </div>
            <div slot="options" slot-scope="item">
              <b-button size="sm" variant="danger" @click="deleteUser(item.item.uid)">删除</b-button>
              <router-link :to="'/system/user/edit/'+item.item.uid" class="link-type">
                <b-button size="sm" variant="primary">修改</b-button>
              </router-link>
              <b-button size="sm" variant="warning" @click="handleEdit(item.item)">岗位</b-button>
            </div>
          </b-table>
          <nav>
            <b-pagination :total-rows="totalRows" :per-page="listQuery.size" v-model="listQuery.page" prev-text="Prev" next-text="Next" hide-goto-end-buttons @change="handleCurrentChange"/>
          </nav>
          <b-modal v-model="editModal" :no-close-on-backdrop="true" title="岗位信息" hide-footer size="lg" @ok="editModal = false">
            <c-form v-if="editModal" :item="editItem" @update:editModal="editModal= $event" />
          </b-modal>
        </b-card>
      </b-col>
    </b-row>
  </div>

</template>

<script>
import cForm from './user_role_form'

import { fetchSysUserList, deleteSysUser } from '@/api/users'

import { formatTime } from '@/utils'

export default {
  components: { cForm },
  data: function() {
    return {
      tableItems: [],
      tableFields: {
        avatar: {
          label: '头像',
          class: 'text-center'
        },
        user: {
          label: '用户名'
        },
        country: {
          label: '国别',
          class: 'text-center'
        },
        nickname: {
          label: '昵称',
          class: 'text-center'
        },
        realname: {
          label: '姓名'
        },
        officePhone: {
          label: '公司电话',
          class: 'text-center'
        },
        phone: {
          label: '手机号',
          class: 'text-center'
        },
        email: {
          label: '邮箱'
        },
        options: {
          label: '操作'
        }
      },
      editModal: false,
      editItem: {},
      listQuery: {
        page: 1,
        size: 10
      },
      totalRows: 0
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      const query = { }
      query.page = this.listQuery.page > 0 ? this.listQuery.page - 1 : this.listQuery.page
      query.size = this.listQuery.size
      fetchSysUserList(query).then(response => {
        this.tableItems = this.list2TableData(response.data.content)
        this.totalRows = response.data.totalElements
      }).catch(function(err) {
        console.log(err)
      })
    },
    list2TableData(data) {
      const res = []
      data.forEach((row) => {
        const tmp = {}
        tmp.uid = row.uid
        tmp.options = {}
        tmp.avatar = {}
        tmp.avatar.url = row.avatar
        tmp.avatar.status = 'success'
        tmp.user = {}
        tmp.user.name = row.username
        tmp.user.new = false
        tmp.user.registered = formatTime(row.registeredAt / 1000)
        tmp.country = {}
        tmp.country.name = 'China'
        tmp.country.flag = 'cn'
        tmp.nickname = row.nickname
        tmp.realname = row.realName
        tmp.officePhone = row.officePhone
        tmp.phone = row.phone
        tmp.email = row.email
        res.push(tmp)
      })
      return res
    },
    deleteUser(uid) {
      deleteSysUser(uid).then(response => {
        this.getList()
        this.showSuccessMsg()
      }).catch(function(err) {
        console.log(err)
      })
    },
    handleCurrentChange(val) {
      this.listQuery.page = val
      this.getList()
    },
    handleEdit(item) {
      this.editItem = item
      this.editModal = true
    },
    getBadge(status) {
      return status === 'Active' ? 'success'
        : status === 'Inactive' ? 'secondary'
          : status === 'Pending' ? 'warning'
            : status === 'Banned' ? 'danger' : 'primary'
    },
    variant(value) {
      let $variant
      if (value <= 25) {
        $variant = 'info'
      } else if (value > 25 && value <= 50) {
        $variant = 'success'
      } else if (value > 50 && value <= 75) {
        $variant = 'warning'
      } else if (value > 75 && value <= 100) {
        $variant = 'danger'
      }
      return $variant
    },
    flag(value) {
      return 'flag-icon flag-icon-' + value
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
