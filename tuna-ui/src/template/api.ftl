<#function parseJSON json>
  <#local null = 'null'> <#-- null is not a keyword in FTL -->
  <#return json?eval>
</#function>
<#assign root = parseJSON(RootDataModel)>
<#assign params = parseJSON(RunnerParams)>
<#assign modelBean = '${root.tableName?replace("_", " ")?capitalize?replace(" ", "")}'>
import request from '@/utils/request'

export function query(params, data = {}) {
  return request({
    url: '/api/${modelBean}/query',
    method: 'post',
    params: params,
    data: data
  })
}

export function create(data) {
  return request({
    url: '/api/${modelBean}',
    method: 'post',
    data
  })
}

export function fetch(id) {
  return request({
    url: '/api/${modelBean}/' + id,
    method: 'get'
  })
}

export function update(data) {
  return request({
    url: '/api/${modelBean}/' + data.id,
    method: 'put',
    data
  })
}

export function del(id) {
  return request({
    url: '/api/${modelBean}/' + id,
    method: 'delete'
  })
}
