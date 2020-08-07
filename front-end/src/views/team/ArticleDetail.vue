<template>
  <b-container>
    <h1 class="text-center">{{article.title}}</h1>
    <h3 class="d-flex justify-content-end">글 작성자: {{writer.name}}</h3>
    <hr class="">
    <div id="content" class="d-flex justify-content-center"></div>
  </b-container>
</template>

<script>
import Axios from 'axios'
import { mapState } from 'vuex'
const API_URL = process.env.VUE_APP_LOCAL_URL
export default {
  data () {
    return {
      studyId: this.$route.params.studyid,
      articleId: this.$route.params.articleid,
      article: {},
      writer: {}
    }
  },
  computed: {
    ...mapState({
      email: state => state.moduleName.email,
    })
  },
  created () {
    Axios({
      method: "GET",
      url: `${API_URL}user/${this.email}`,
      headers: { "Content-Type": "application/json; charset=utf-8",
                'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
                'user-email': sessionStorage.getItem('user-email')},
    })
    .then(res => {
      console.log(res)
      this.writer = res.data
    })
    .catch(() => {alert('사용자 정보를 불러올 수 없습니다')})

    Axios({
      method: "GET",
      url: `${API_URL}post/${this.articleId}`,
      headers: { "Content-Type": "application/json; charset=utf-8",
                'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
                'user-email': sessionStorage.getItem('user-email')},
    })
    .then(res => {
      console.log(res)
      this.article = res.data
      const articleContent = document.getElementById('content')
      articleContent.innerHTML = this.article.content
    })
    .catch(() => {alert('글 정보를 불러올 수 없습니다')})
  }
}
</script>

<style>

</style>