import Mock from 'mockjs'
import { param2Obj } from '@/utils'

let res = [
    {id: 6, parent_id: 2, department_name: '部门名称-06', show_name: '显示名称-06', description: '部门描述-06'},
    {id: 7, parent_id: 3, department_name: '部门名称-07', show_name: '显示名称-07', description: '部门描述-07'},
    {id: 2, parent_id: 1, department_name: '部门名称-02', show_name: '显示名称-02', description: '部门描述-02'},
    {id: 4, parent_id: 2, department_name: '部门名称-04', show_name: '显示名称-04', description: '部门描述-04'},
    {id: 1, parent_id: 0, department_name: '部门名称-01', show_name: '显示名称-01', description: '部门描述-01'},
    {id: 9, parent_id: 5, department_name: '部门名称-09', show_name: '显示名称-09', description: '部门描述-09'},
    {id: 8, parent_id: 3, department_name: '部门名称-08', show_name: '显示名称-08', description: '部门描述-08'},
    {id: 3, parent_id: 1, department_name: '部门名称-03', show_name: '显示名称-03', description: '部门描述-03'},
    {id: 5, parent_id: 2, department_name: '部门名称-05', show_name: '显示名称-05', description: '部门描述-05'},
    {id: 10, parent_id:6, department_name: '部门名称-10', show_name: '显示名称-10', description: '部门描述-10'}
]

export default {
  getList: config => {
    return  {
      totalElements: res.length,
      content: res
    }
  }
}
