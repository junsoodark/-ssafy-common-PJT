<template>
  <b-container class="my-3">
    <h1 class="text-center">{{title}}</h1>
    <div class="d-flex justify-content-between">
      <h3>글 분류: {{category}}</h3>
      <h3>글 작성자: {{writer}}</h3>
    </div>
    <div v-if="isWriter" class="d-flex justify-content-end">
      <b-button>글 수정</b-button>
      <b-button @click="deleteCover">글 삭제</b-button>
    </div>
    <hr class="">
    <div id="content" class="d-flex justify-content-center">{{content}}</div>
  </b-container>
</template>

<script>
import Axios from 'axios'
import { mapState } from "vuex"
const API_URL = process.env.VUE_APP_LOCAL_URL
export default {
  data () {
    return {
      isWriter: false,
      id: this.$route.params.id,
      title: "제목",
      writer: "작성자",
      content: "글내용",
      company: null,
      job: null,
      category: null
    }
  },
  computed: {
    ...mapState({
      email: (state) => state.moduleName.email,
    })
  },
  created () {
    Axios({
      method: "GET",
      url: `${API_URL}resume/${this.id}`,
      headers: { 
        "Content-Type": "application/json; charset=utf-8", 
        'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
        'user-email': sessionStorage.getItem('user-email')
      },
    })
    .then(res => {
      this.title = res.data.title
      this.company = res.data.company
      this.category = res.data.category
      if (this.email == res.data.email) {
        this.isWriter = true
      }
    })
  },
  methods: {
    deleteCover () {
      Axios({
      method: "DELETE",
      url: `${API_URL}resume?resumeId=${this.id}`,
      headers: { 
        "Content-Type": "application/json; charset=utf-8", 
        'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
        'user-email': sessionStorage.getItem('user-email')
      },
    })
    .then( res => {
      console.log(res)
      this.$router.push({ name: "LetterList"})
    })
    .catch( err => {
      alert(err.response.data)
    })
    },
    updateCover () {
      this.$router.push({ name: "UpdateCover", params: {id:this.id}})
    }
  }
}
</script>

<style>

</style>