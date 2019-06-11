<template>
  <user-form :is-edit="false"/>
</template>

<script>
import userForm from './form'

export default {
  name: 'CreateForm',
  components: { userForm }
}
</script>

