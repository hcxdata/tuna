<#function parseJSON json>
  <#local null = 'null'> <#-- null is not a keyword in FTL -->
  <#return json?eval>
</#function>
<#assign root = parseJSON(RootDataModel)>
<#assign params = parseJSON(RunnerParams)>
<#function camelize x>
  <#return x?replace("_", " ")?capitalize?replace(" ", "")?uncap_first>
</#function>
<template>
  <div class="animated fadeIn">
    <b-row>
      <b-col sm="12" md="12" lg="12" xl="12">
        <b-card >
          <div slot="header">
            ${ camelize(root.tableComment) }检索条件
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
            ${ camelize(root.tableComment) }列表
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
    <b-modal v-model="editModal" :no-close-on-backdrop="true" title="修改${ camelize(root.tableComment) }" hide-footer size="lg" centered>
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
    <b-modal v-model="addModal" :no-close-on-backdrop="true" title="新增${ camelize(root.tableComment) }" hide-footer size="lg" centered>
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
import { query, create, update, del } from '@/api/${ camelize(root.tableName) }'

const defaultForm = {
<#assign defaultFormCol>
<#list root.columns as col >
<#if col.config.isEdit>
  ${ camelize(col.columnName) }: ''<#if col_has_next>,</#if>
</#if>
</#list>
</#assign>
<#if defaultFormCol?trim?length gt 2>
  ${ defaultFormCol?trim[0..defaultFormCol?trim?length-2] }
</#if>
}

const defaultSearchForm = {
<#assign defaultSearchFormCol>
<#list root.columns as col >
<#if col.config.isQuery>
  ${ camelize(col.columnName) }: ''<#if col_has_next>,</#if>
</#if>
</#list>
</#assign>
<#if defaultSearchFormCol?trim?length gt 2>
  ${ defaultSearchFormCol?trim[0..defaultSearchFormCol?trim?length-2] }
</#if>
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
      <#list root.columns as col >
      <#if col.config.isShow>
        ${ camelize(col.columnName) }: {
          label: '${ col.config.fieldLabel }',
          class: 'text-center'
        },
      </#if>
      </#list>
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
