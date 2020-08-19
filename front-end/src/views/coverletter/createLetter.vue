<template>
  <div class="my-3">
    <h1>자소서 작성</h1>
    <b-container>
      <hr>
      <b-row>
        <b-form-select v-model="category" :options="options" class="col-4 my-1"></b-form-select>
        <b-form-input v-model="title" placeholder="글 제목을 입력해주세요" class="col-8 my-1"></b-form-input>
        <b-form-input v-model="company" placeholder="회사를 입력해주세요" class="col-4"></b-form-input>
        <b-form-input v-model="job" placeholder="직무를 입력해주세요" class="col-8"></b-form-input>
      </b-row>
      <b-button class="m-3" @click="addQuestion" variant="primary">자소서 항목 추가</b-button><b-button class="m-3" @click="delQuestion" variant="danger">자소서 항목 삭제</b-button>
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
      ]
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
      cover = {num: numCover, title: null, answer: null}
      this.question += 1
      this.items.push(cover)
    },
    delQuestion () {
      this.question -= 1
      this.items.pop()
    },
    submitCoverLetter () {
      if (this.category == null) {
        alert('카테고리를 선택해주세요')
        return false
      }
      const params = {
        'category': this.category,
        'company': this.company,
        'email': this.email,
        'job':this.job,
        'title':this.title
      }
      Axios({
        method: "POST",
        url: `${API_URL}resume`,
        params: params,
        headers: { "Content-Type": "application/json; charset=utf-8",
                  'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
                  'user-email': sessionStorage.getItem('user-email')},
      })
      .then(res => {
        const resumeId = res.data
        var trigger = false
        if (resumeId == -1) {
          alert('글 작성이 알 수 없는 이유로 실패했습니다')
          return false
        }
        for (var i=0; i<this.items.length; i++) {
          this.submitQuestion(resumeId,this.items[i])
          if (i==this.items.length-1) {
            trigger = true
          }
        }
        alert('작성이 완료되었습니다')
        if (trigger) {this.$router.push({ name: "coverLetterDetail", params: {id:resumeId}})}
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
    }
  }
}
</script>

<style>
.collapsed > .when-open,
.not-collapsed > .when-closed {
  display: none;
}
</style>