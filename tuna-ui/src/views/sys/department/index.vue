

<template>
  <div className="animated">
    <b-card>
      <div slot="header">
        <i class="icon-menu mr-1"></i> 部门列表
        <div class="card-header-actions">
          <b-button size="sm" variant="primary" @click="handleAdd()"> 添加部门</b-button>
        </div>
      </div>
      <b-card-body>
          <zk-table
            ref="table"
            sum-text="sum"
            index-text="#"
            :data="data"
            :columns="columns"
            :stripe="props.stripe"
            :border="props.border"
            :show-header="props.showHeader"
            :show-summary="props.showSummary"
            :show-row-hover="props.showRowHover"
            :show-index="props.showIndex"
            :tree-type="props.treeType"
            :is-fold="props.isFold"
            :expand-type="props.expandType"
            :selection-type="props.selectionType">
            <template slot="empower" scope="scope">
              <b-button size="sm" variant="danger" @click='handleDel(scope.row)' :disabled='scope.row.children.length > 0'>删除</b-button>
              <b-button size="sm" variant="primary" @click='handleEdit(scope.row)'>编辑</b-button>
            </template>
          </zk-table>
          <b-modal title="修改部门信息" hide-footer size="lg" v-model="editModal" @ok="editModal = false" :no-close-on-backdrop='true'>
            <c-form v-if="editModal" :is-edit="isEdit" :item="editItem" v-on:update:editModal="editModal= $event" v-on:updateFreshByModal="handleUpdateFreshByModal($event) " :tree-data='data'/>
          </b-modal>
      </b-card-body>
    </b-card>
  </div>
</template>

<script>
  import cForm from './form'
  import { query, del } from '@/api/departments'
  import { list2tree } from '@/utils'

  export default {
    name: 'example',
    components: { cForm },
    data() {
      return {
        props: {
          stripe: true,
          border: false,
          showHeader: true,
          showSummary: false,
          showRowHover: true,
          showIndex: false,
          treeType: true,
          isFold: false,
          expandType: false,
          selectionType: false,
          isEdit: false
        },
        data: [ ],
        columns: [
          {
            label: '部门名称',
            prop: 'departmentName',
            minWidth: '100px',
          },
          {
            label: '部门显示名',
            prop: 'showName'
          },
          {
            label: '上级部门',
            prop: 'p_showName',
          },
          {
            label: '部门描述',
            prop: 'description'
          },
          {
            label: "操作",
            type: "template",
            template: "empower"
          }
        ],
        editModal: false,
        editItem: {}
      };
    },
  created() {
    this.getList()
  },
  computed: {
    propList() {
      return Object.keys(this.props).map(item => ({
        name: item,
      }));
    }
  },
  methods: {
    getList(){
      query().then(response => {
        this.data = list2tree(response.data,{ pid: 'parentId', insertParentAttrs: ['showName'] })
      }).catch(function(err){
        console.log(err);
      })
    },
    handleAdd() {
      this.editItem = { }
      this.isEdit = false
      this.editModal = true
    },
    handleEdit(item){
      this.editItem = item
      this.isEdit = true
      this.editModal = true
    },
    handleDel(item){
      del(item.id).then(response => {
        this.showSuccessMsg()
        this.getList()
      }).catch(function(err){
        console.log(err);
      })
    },
    handleUpdateFreshByModal(e){
      this.getList()
      this.editModal = false
    }
  },
  notifications: {
    showSuccessMsg: {
      type:  'success',
      message: '操作成功!'
    }
  }
}
</script>
