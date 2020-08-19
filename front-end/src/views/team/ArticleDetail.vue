<template>
  <b-container>
    <br>
    <b-row align-h="start" class="mb-3">
      <b-col md="2" offset-md="1" class="text-left"><h2>게시글</h2></b-col>
    </b-row>
    <b-row >
      <b-col md="10" offset-md="1" >
        <h2 class="text-left"><h1>{{article.title}}</h1></h2>
      </b-col>
    </b-row>
    <b-row class="my-3">
      <b-col md="3" offset-md="1" class="text-left"><h5>작성일: {{ article.date }}</h5></b-col>
      <b-col md="2" offset-md="5" class="text-right"><h5>작성자: {{ article.writer }}</h5> </b-col>
      <b-col offset-md="1"></b-col>
    </b-row>
    <br>
    <b-row>
      <b-col md="1" class="mx-1"></b-col>
      <b-col md="10" class="text-left border py-3">
        <div id="content"></div>
      </b-col>
    </b-row>
    <br>
    <b-row align-h="end" >
      <b-col md="4">
        <b-button variant="info" class="mx-2" @click="updatePost">글 수정</b-button>
        <b-button variant="danger" class="mx-2" @click="deletePost">글 삭제</b-button>
      </b-col>
    </b-row>
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
    // 유저 정보
    Axios({
      method: "GET",
      url: `${API_URL}user/${this.email}`,
      headers: { "Content-Type": "application/json; charset=utf-8",
                'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
                'user-email': sessionStorage.getItem('user-email')},
    })
    .then(res => {
      if (this.email==res.data.email) {
        this.isWriter = true
      }
    })
    .catch(() => {alert('사용자 정보를 불러올 수 없습니다')})
    // article 정보
    Axios({
      method: "GET",
      url: `${API_URL}post/${this.articleId}`,
      headers: { "Content-Type": "application/json; charset=utf-8",
                'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
                'user-email': sessionStorage.getItem('user-email')},
    })
    .then(res => {
      this.article = res.data
      this.findWriterName(this.article.writer)
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
      this.$router.push({ name: "updateArticle" , params: { id:this.articleId }})
    },
    
    // 작성자 id로 name 찾기
    findWriterName(userId) {
      Axios({
        method: "GET",
        url: `${API_URL}user/id/${userId}`,
        headers: { "Content-Type": "application/json; charset=utf-8",
                  'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
                  'user-email': sessionStorage.getItem('user-email')},
      })
      .then(res => {
        this.article.writer = res.data.name
      })
      .catch(() => {alert('스터디팀 정보를 불러올 수 없습니다')})
    }
  }
}
</script>

<style>
#content {
  background-color:white;
}
</style>