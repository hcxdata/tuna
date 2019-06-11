<template>
  <user-form :is-edit="true"/>
</template>

<script>
import userForm from './form'

export default {
  name: 'EditForm',
  components: { userForm }
}
</script>

