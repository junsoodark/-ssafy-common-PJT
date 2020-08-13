<template>
  <div></div>
</template>

<script>
// import Axios from 'axios'
import { mapState } from "vuex"
// const API_URL = process.env.VUE_APP_LOCAL_URL
export default {
  data () {
    return {
      title: null,
      content: null,
      id: this.$route.params.id,
      articleId: this.$route.params.articleId,
      writerId: null
    }
  },
  computed: {
    ...mapState({
      email: (state) => state.moduleName.email,
    })
  },
  methods: {}
}
</script>

<style>

</style>