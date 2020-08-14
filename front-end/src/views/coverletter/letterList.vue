<template>
  <div>
    <h1 class="my-3">자소서 게시판</h1>
    <b-button :to="{name:'createLetter'}">글쓰기</b-button>
    <b-container class="my-3">
      <b-list-group>
        <b-row>
          <b-col cols="4" class="p-0"><b-list-group-item >분류</b-list-group-item></b-col>
          <b-col cols="8" class="p-0"><b-list-group-item >제목</b-list-group-item></b-col>
        </b-row>
        <hr>
        <b-row v-for="item in letters" :key="item.studyId">
          <b-col cols="4" class="p-0"><b-list-group-item route :to="{ name: 'coverLetterDetail', params: {id:item.id} }" style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis; height:50px;">{{ item.category }}</b-list-group-item></b-col>
          <b-col cols="8" class="p-0"><b-list-group-item route :to="{ name: 'coverLetterDetail', params: {id:item.id} }" style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis; height:50px;">{{ item.title }}</b-list-group-item></b-col>
        </b-row>
      </b-list-group>
    </b-container>
  </div>
</template>

<script>
import Axios from 'axios'
const API_URL = process.env.VUE_APP_LOCAL_URL
export default {
  data () {
    return {
      letters: [
        {title: '자소서 컨펌좀',id:3,category:"자소서 컨펌"},
        {title: '이런 자소서엔 어떤 질문이 들어올까?',id:4,category:"면접 질문"},
        {title: '자소서 하나 쓰는데 이거 좀 걸린다',id:5,category:"자소서 컨펌"},
        {title: '면접 어떤 질문 옴?',id:6,category:"면접 질문"}
      ]
    }
  },
  created () {
    Axios({
        method: "GET",
        url: `${API_URL}resume/all`,
        headers: { 
          "Content-Type": "application/json; charset=utf-8", 
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
          'user-email': sessionStorage.getItem('user-email')
        },
      })
    .then(res => {
      this.letters = res.data.reverse()
    })
    .catch(err => {
      const msg = err.response.data.msg
      if (msg == '토큰이 존재하지 않습니다.') {
        alert('로그인을 해주셔야 이용이 가능합니다')
      } else { alert(msg) }
    })
  }
}
</script>

<style>

</style>