<template>
  <div class="animated fadeIn">
    <b-row>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-card >
          <div slot="header">
            地市字典检索条件
          </div>
          <search-form ref="searchForm" :param-form="searchForm"/>
          <b-row>
            <b-col sm="12" md="12" lg="12" xl="12" align="center">
              <b-button type="submit" variant="primary" @click="handleSearch">
                提交
              </b-button>
              <b-button class="ml-4" type="reset" variant="danger" @click="resetSearchForm">
                重置
              </b-button>
            </b-col>
          </b-row>
        </b-card>
      </b-col>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-card >
          <div slot="header">
            地市字典列表
            <div class="card-header-actions">
              <b-button size="sm" block variant="primary" @click="handlAdd"> 添加 </b-button>
            </div>
          </div>
          <b-table :items="tableItems" :fields="tableFields" hover stacked="md" responsive="sm" >
            <div slot="options" slot-scope="item">
              <b-button size="sm" variant="danger" @click="handleDel(item.item.id)">
                <span class="fa fa-trash-o"/>
              </b-button>
              <b-button size="sm" variant="primary" @click="handleEdit(item.item)">
                <span class="fa fa-edit"/>
              </b-button>
            </div>
          </b-table>
          <nav>
            <b-pagination :total-rows="totalRows" :per-page="listQuery.size" v-model="listQuery.page" prev-text="Prev" next-text="Next" hide-goto-end-buttons @change="handleCurrentChange"/>
          </nav>
        </b-card>
      </b-col>
    </b-row>
    <b-modal v-model="editModal" :no-close-on-backdrop="true" title="修改地市字典" hide-footer size="lg" centered>
      <b-row>
        <b-col sm="12" md="12" lg="12" xl="12">
          <base-form ref="editForm" :param-form="form"/>
        </b-col>
      </b-row>
      <b-row>
        <b-col sm="12" md="12" lg="12" xl="12" align="center">
          <b-button type="submit" variant="primary" @click="handleEditSubmit">
            更新
          </b-button>
          <b-button class="ml-1" type="submit" variant="success" @click="editModal = false">
            取消
          </b-button>
        </b-col>
      </b-row>
    </b-modal>
    <b-modal v-model="addModal" :no-close-on-backdrop="true" title="新增地市字典" hide-footer size="lg" centered>
      <b-row>
        <b-col sm="12" md="12" lg="12" xl="12">
          <base-form ref="addForm" :param-form="form"/>
        </b-col>
      </b-row>
      <b-row>
        <b-col sm="12" md="12" lg="12" xl="12" align="center">
          <b-button type="submit" variant="primary" @click="handleAddSubmit">
            保存
          </b-button>
          <b-button class="ml-1" type="submit" variant="success" @click="addModal = false">
            取消
          </b-button>
        </b-col>
      </b-row>
    </b-modal>
  </div>
</template>

<script>

import baseForm from './_base_form'
import searchForm from './_search_form'

import { clearObjectNullOrBlank } from '@/utils'
import { query, create, update, del } from '@/api/cityDict'

const defaultForm = {
  cityCode: '',
  cityName: '',
  cityPostCode: ''
}

const defaultSearchForm = {
  cityCode: '',
  cityName: ''
}

export default {
  components: {
    baseForm,
    searchForm
  },
  data: function() {
    return {
      form: Object.assign({}, defaultForm),
      searchForm: Object.assign({}, defaultSearchForm),
      tableItems: [],
      tableFields: {
        cityCode: {
          label: '地市代码',
          class: 'text-center'
        },
        cityName: {
          label: '地市名称',
          class: 'text-center'
        },
        cityPostCode: {
          label: '地市邮编',
          class: 'text-center'
        },
        options: {
          label: '操作'
        }
      },
      listQuery: {
        page: 1,
        size: 10
      },
      totalRows: 0,
      editModal: false,
      addModal: false
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      const params = {}
      const data = Object.assign({}, this.searchForm)
      params.page = this.listQuery.page > 0 ? this.listQuery.page - 1 : this.listQuery.page
      params.size = this.listQuery.size
      clearObjectNullOrBlank(data)
      query(params, data).then(response => {
        this.tableItems = response.data.content
        this.totalRows = response.data.totalElements
      }).catch(function(err) {
        console.log(err)
      })
    },
    handlAdd() {
      this.form = Object.assign({}, defaultForm)
      this.addModal = true
    },
    handleAddSubmit() {
      const flag = this.$refs.addForm.validate()
      if (flag) {
        create(this.form).then(response => {
          this.showSuccessMsg()
          this.addModal = false
          this.getList()
        }).catch(function(err) {
          console.log(err)
        })
      }
    },
    handleEdit(item) {
      this.form = Object.assign({}, item)
      this.editModal = true
    },
    handleEditSubmit() {
      const flag = this.$refs.editForm.validate()
      if (flag) {
        update(this.form).then(response => {
          this.editModal = false
          this.showSuccessMsg()
          this.getList()
        }).catch(function(err) {
          console.log(err)
        })
      }
    },
    handleDel(id) {
      this.$bvModal.msgBoxConfirm('确定要删除?', {
        buttonSize: 'sm',
        okVariant: 'danger',
        okTitle: '确定',
        cancelTitle: '取消',
        centered: true
      }).then(value => {
        if (value) {
          del(id).then(response => {
            this.showSuccessMsg()
            this.getList()
          }).catch(function(err) {
            console.log(err)
          })
        }
      }).catch(err => {
        console.log(err)
      })
    },
    handleCurrentChange(val) {
      this.listQuery.page = val
      this.getList()
    },
    handleSearch() {
      this.getList()
    },
    resetSearchForm() {
      this.searchForm = Object.assign({}, defaultSearchForm)
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
