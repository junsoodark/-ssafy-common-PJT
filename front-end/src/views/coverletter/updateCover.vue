<template>
  <div class="my-3">
    <h1>자소서 수정</h1>
    <b-container>
      <hr>
      <b-row>
        <b-form-select v-model="category" :options="options" class="col-4 my-1"></b-form-select>
        <b-form-input v-model="title" placeholder="글 제목을 입력해주세요" class="col-8 my-1"></b-form-input>
        <b-form-input v-model="company" placeholder="회사를 입력해주세요" class="col-4"></b-form-input>
        <b-form-input v-model="job" placeholder="직무를 입력해주세요" class="col-8"></b-form-input>
      </b-row>
      <b-button class="my-2" @click="addQuestion">자소서 항목 추가</b-button> | <b-button class="my-2" @click="delQuestion">자소서 항목 삭제</b-button>
      <div v-for="item in items" :key="item.num" class="my-3">
        <b-button v-b-toggle="'my-'+item.num">
            <span class="when-open">{{item.num+1}}번 질문 닫기</span><span class="when-closed">{{item.num+1}}번 질문 열기</span>
        </b-button>
        <b-collapse :id="'my-'+item.num" class="my-1">
          <div class="my-3">
            <b-form-input v-model="item.title" placeholder="질문을 입력해주세요"></b-form-input>
            <b-form-textarea
              v-model="item.answer"
              placeholder="Tall textarea"
              rows="8"
            ></b-form-textarea>
          </div>
        </b-collapse>
      </div>
      <br>
      <b-button @click="submitCoverLetter">제출하기</b-button>
    </b-container>
  </div>
</template>

<script>
import Axios from 'axios'
import { mapState } from "vuex"
const API_URL = process.env.VUE_APP_LOCAL_URL
export default {
  data () {
    return {
      id: this.$route.params.id,
      items: [],
      question:0,
      title: null,
      company: null,
      job: null,
      category: '자소서 컨펌',
      options: [
        { value: null, text: '항목을 선택해주세요' },
        { value: '자소서 컨펌', text: '자소서 컨펌'},
        { value: '자소서 면접', text: '자소서 면접'}
      ],
      isWritter: false,
      delItems: []
    }
  },
  computed: {
    ...mapState({
      email: (state) => state.moduleName.email,
    })
  },
  methods: {
    addQuestion () {
      var cover = new Object
      const numCover = this.question
      cover = {num: numCover, title: null, answer: null, id: null}
      this.question += 1
      this.items.push(cover)
    },
    delQuestion () {
      this.question -= 1
      const delItem = this.items.pop()
      if (delItem.id != null) {
        this.delItems.push(delItem.id)
      }
    },
    submitCoverLetter () {
      if (this.category == null) {
        alert('카테고리를 선택해주세요')
        return false
      }
      const params = {
        'category': this.category,
        'company': this.company,
        'job': this.job,
        'resumeId': this.id,
        'title': this.title
      }
      Axios({
        method: "PUT",
        url: `${API_URL}resume`,
        params: params,
        headers: { "Content-Type": "application/json; charset=utf-8",
                  'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
                  'user-email': sessionStorage.getItem('user-email')},
      })
      .then((res) => {
        console.log(res)
        for (var j=0; j<this.delItems.length; j++) {
          this.deleteQuest(this.delItems[j])
        }
        for (var i=0; i<this.items.length; i++) {
          if (this.items[i].id == null) {
            this.submitQuestion(this.id,this.items[i])
          } else {
            this.updateQuestion(this.items[i])
          }
        }
        this.$router.push({ name: "coverLetterDetail", params: {id:this.id}})
      })
      .catch(err => {
        console.log(err)
      })
    },
    submitQuestion (resumeId,quest) {
      const params = {
        'content': quest.answer,
        'resumeId': resumeId,
        'title': quest.title
      }
      Axios({
        method: "POST",
        url: `${API_URL}resumeitem`,
        params: params,
        headers: { "Content-Type": "application/json; charset=utf-8",
                  'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
                  'user-email': sessionStorage.getItem('user-email')},
      })
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        alert(err.response.data.msg)
      })
    },
    updateQuestion (quest) {
      const params = {
        'content': quest.answer,
        'resumeitemId': quest.id,
        'title': quest.title
      }
      Axios({
        method: "PUT",
        url: `${API_URL}resumeitem`,
        params: params,
        headers: { "Content-Type": "application/json; charset=utf-8",
                  'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
                  'user-email': sessionStorage.getItem('user-email')},
      })
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
    },
    deleteQuest (id) {
      Axios({
        method: "DELETE",
        url: `${API_URL}resumeitem`,
        params: {'resumeitemId':id},
        headers: { "Content-Type": "application/json; charset=utf-8",
                  'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
                  'user-email': sessionStorage.getItem('user-email')},
      })
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
    }
  },
  created () {
    // 자소서 정보 가져오기
    Axios({
      method: "GET",
      url: `${API_URL}resume/${this.id}`,
      headers: { "Content-Type": "application/json; charset=utf-8",
                  'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
                  'user-email': sessionStorage.getItem('user-email')},
    })
    .then(res => {
      if (res.data.email != this.email) {
        this.isWritter = false
        this.$router.push({ name: "coverLetterDetail", params: {id: this.id}})
      } else { 
        this.isWritter = true 
      }
      this.title = res.data.title
      this.category = res.data.category
      this.job = res.data.job
      this.company = res.data.company
    })
    .catch(err => {
      console.log(err)
    })
    // 자소서 항목 가져오기
    Axios({
      method: "GET",
      url: `${API_URL}resumeitem/resume/${this.id}`,
      headers: { "Content-Type": "application/json; charset=utf-8",
                  'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
                  'user-email': sessionStorage.getItem('user-email')},
    })
    .then(res => {
      for (var i=0; i<res.data.length; i++) {
        const item = res.data[i]
        item.num = this.question
        this.question += 1
        this.items.push(item)
      }
    })
    .catch(err => {
      console.log(err)
    })
  }
}
</script>

<style>
.collapsed > .when-open,
.not-collapsed > .when-closed {
  display: none;
}
</style>