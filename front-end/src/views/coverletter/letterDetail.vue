<template>
  <b-container class="my-3">
    <h1 class="text-center">{{title}}</h1>
    <div class="d-flex justify-content-between">
      <h3>글 분류: {{category}}</h3>
      <h3>글 작성자: {{writer}}</h3>
    </div>
    <div v-if="isWriter" class="d-flex justify-content-end">
      <b-button @click="updateCover">글 수정</b-button>
      <b-button @click="deleteCover">글 삭제</b-button>
    </div>
    <hr class="">
    <div id="content" class="my-2" v-for="item in items" :key="item.title">
      질문: {{item.title}}
      <hr>
      대답: {{item.content}} <br>
      <div class="d-flex justify-content-end" v-if="isWriter">
        <b-button @click="updateQuest(item.id)">항목 수정</b-button> | <b-button @click="deleteQuestion(item.id)">항목 삭제</b-button>
      </div>
      <hr>
    </div>
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
      company: null,
      job: null,
      category: null,
      items: []
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
      this.writer = res.data.name
      this.title = res.data.title
      this.company = res.data.company
      this.category = res.data.category
      if (this.email == res.data.email) {
        this.isWriter = true
      }
    })
    Axios({
      method: "GET",
      url: `${API_URL}resumeitem/resume/${this.id}`,
      headers: { 
        "Content-Type": "application/json; charset=utf-8", 
        'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
        'user-email': sessionStorage.getItem('user-email')
      }
    })
    .then(res => {
      this.items = res.data
    })
    .catch(err => {
      alert(err.response.data.msg)
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
    },
    deleteQuestion (id) {
      Axios({
        method: "DELETE",
        url: `${API_URL}resumeitem`,
        params: {'resumeitemId':id},
        headers: { 
          "Content-Type": "application/json; charset=utf-8", 
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
          'user-email': sessionStorage.getItem('user-email')
        }
      })
      .then(res => {
        console.log(res)
        var newQuest = []
        for (var i=0; i<this.items.length; i++) {
          if (this.items[i].id != id) {
            newQuest.push(this.items[i])
          }
        }
        this.items = newQuest
      })
    },
    updateQuest (id) {
      this.$router.push({ name: "UpdateQuest", params: {articleId:this.id,id:id}})
    }
  }
}
</script>

<style>

</style>