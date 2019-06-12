<template>
  <b-form>
    <b-row>
    </b-row>
  </b-form>
</template>

<script>
export default {
  name: 'SearchForm',
  props: {
    paramForm: {
      type: Object,
      default: function() {
        return {}
      }
    }
  },
  data() {
    return {
      submitted: false
    }
  },
  computed: {
    form() { return this.paramForm }
  },
  created() {
  },
  methods: {
    validate() {
    }
  }
}
</script>
