<template>
  <b-container class="my-3">
    <h1 class="text-center">{{title}}</h1>
    <div class="d-flex justify-content-between">
      <h3>글 분류: {{category}}</h3>
      <h3>글 작성자: {{writer}}</h3>
    </div>
    <div v-if="isWriter" class="d-flex justify-content-end">
      <b-button @click="updateCover" variant="warning">글 수정</b-button> | 
      <b-button @click="deleteCover" variant="danger">글 삭제</b-button>
    </div>
    <hr class="">
    <span v-for="(item,index) in items" :key="item.id">
      <b-button @click="changeLetter(index)" variant="info" v-if="index != isSelected">{{index+1}} 번</b-button><b-button @click="changeLetter(index)" variant="primary" v-else>{{index+1}} 번</b-button><span v-if="index != items.length-1"> | </span>
    </span>
    <hr>
    <div>
      질문: {{letterTitle}}
      <hr>
      대답: {{letterContent}} <br>
      <div class="d-flex justify-content-end" v-if="isWriter">
        <b-button @click="updateQuest(letterId)">항목 수정</b-button> | <b-button @click="deleteQuestion(letterId)">항목 삭제</b-button>
      </div>
      <hr>
      <b-form-textarea
        id="textarea"
        v-model="text"
        placeholder="댓글을 작성해주세요"
        rows="3"
        max-rows="6"
      ></b-form-textarea>
      <b-button variant="success" class="my-2" @click="createReply">댓글 제출</b-button>
      <b-row v-for="reply in replies" :key="reply.id" style="border-top-width : 3px; border-top-style : dotted; border-top-color : red;">
        <a class="my-2 col-9 d-flex justify-content-between"><p>{{reply.content}}</p><p>작성자: {{reply.writerName}}</p></a>
        <div class="my-2 col-3" v-show="reply.writerId == userId">
          <b-button class="my-2" @click="putReply(reply.id)">댓글 수정</b-button> | 
          <b-button class="my-2" @click="deleteReply(reply.id)">댓글 삭제</b-button>
        </div>
      </b-row>
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
      items: [],
      isChanged: true,
      letterId: null,
      letterTitle: null,
      letterContent: null,
      isSelected: 0,
      userId: null,
      text: null,
      replies: [],
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
      this.letterTitle = res.data[0].title
      this.letterId = res.data[0].id
      this.letterContent = res.data[0].content
    })
    .catch(err => {
      alert(err.response.data.msg)
    })
    // 유저 id 가져오기
    Axios({
      method: "GET",
      url: `${API_URL}user/${this.email}`,
      headers: { 
        "Content-Type": "application/json; charset=utf-8", 
        'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
        'user-email': sessionStorage.getItem('user-email')
      }
    })
    .then(res => {
      this.userId = res.data.id
    })
    .catch(err => {
      alert(err.response.data.msg)
    })
    // 댓글 가져오기
    this.getReplies()
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
      this.isChanged = true
      this.$router.push({ name: "UpdateQuest", params: {articleId:this.id,id:id}})
    },
    changeLetter (index) {
      const item = this.items[index]
      this.letterTitle = item.title
      this.letterContent = item.content
      this.letterId = item.id
      this.isSelected = index
    },
    createReply () {
      const params = {
        'content': this.text,
        'resumeId': this.id,
        'userId': this.userId
      }
      Axios({
        method: "POST",
        url: `${API_URL}reply`,
        params: params,
        headers: { 
          "Content-Type": "application/json; charset=utf-8", 
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
          'user-email': sessionStorage.getItem('user-email')
        }
      })
      .then(res => {
        console.log(res)
        alert('댓글이 성공적으로 작성되었습니다')
        this.text = null
        this.getReplies()
      })
      .catch(err => {
        console.log(err)
      })
    },
    getReplies () {
      Axios({
        method: "GET",
        url: `${API_URL}reply/resume/${this.id}`,
        headers: { 
          "Content-Type": "application/json; charset=utf-8", 
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
          'user-email': sessionStorage.getItem('user-email')
        }
      })
      .then(response => {
        this.replies = response.data
      })
      .catch(error => {
        alert(error.response.data.msg)
      })
    },
    deleteReply (replyId) {
      const params = {
        'replyId': replyId
      }
      Axios({
        method: "DELETE",
        url: `${API_URL}reply`,
        params: params,
        headers: { 
          "Content-Type": "application/json; charset=utf-8", 
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
          'user-email': sessionStorage.getItem('user-email')
        }
      })
      .then(res => {
        console.log(res)
        alert("성공적으로 삭제했습니다")
        this.getReplies()
      })
      .catch(err => {
        alert(err.response.data.msg)
      })
    },
    putReply (replyId) {
      this.isChanged = true
      this.$router.push({ name: "UpdateReply", params: {articleId:this.id,replyId:replyId}})
    }
  },
  beforeUpdate () {
    if (this.isChanged) {
    console.log(this.isChanged)
    this.isChanged = false
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
    this.getReplies()
  }
  }
}
</script>

<style>

</style>