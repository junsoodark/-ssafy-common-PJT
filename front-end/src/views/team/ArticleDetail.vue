<template>
  <b-container>
    <h1 class="text-center">{{article.title}}</h1>
    <h3 class="d-flex justify-content-end">글 작성자: {{writer.name}}</h3>
    <hr class="">
    <div id="content" class="d-flex justify-content-center"></div>
    <hr>
    <div class="d-flex justify-content-end">
      <b-button @click="updatePost">글 수정</b-button> | 
      <b-button @click="deletePost">글 삭제</b-button>
    </div>
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
      writer: {},
      isWriter: false
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
      if (this.email==res.data.email) {
        this.isWriter = true
      }
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
  },
  methods: {
    deletePost () {
      Axios({
        method: 'DELETE',
        url: `${API_URL}post`,
        params: {'id':this.article.writer},
        headers: { "Content-Type": "application/json; charset=utf-8",
                'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
                'user-email': sessionStorage.getItem('user-email')},
      })
      .then( () => {
        alert('삭제되었습니다')
        this.$router.push({ name: "StudyArticle" , params:{id:this.studyId}})
      })
      .catch( err => {
        alert(err.response.data)
      })
    },
    updatePost () {
      this.$router.push({ name: "updateArticle" , params: {id:this.articleId,}})
    }
  }
}
</script>

<style>

</style>