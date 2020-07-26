<template>
  <b-container>
    <h1>마이페이지</h1>
    <b-form id="checkemail" @submit.prevent="checkemail">
      <h1>이메일과 비밀번호를 한번 더 입력해주세요</h1>
      <b-row>이메일 아이디</b-row>
      <b-row>
        <b-form-input
          id="input-1"
          v-model="userInfo.chemail"
          type="email"
          required
          placeholder="이메일 아이디를 입력하세요"
        ></b-form-input>
      </b-row>
      <br>
      <b-row>비밀번호</b-row>
      <b-row>
        <b-form-input
          id="input-2"
          v-model="userInfo.chpassword"
          required
          placeholder="비밀번호를 입력하세요"
        ></b-form-input>
      </b-row>
      <b-button type="submit" block variant="info" size="lg">내 정보 조회하기</b-button>
    </b-form>
    <div class="d-none" id="user-window">
      <b-media>
        <template v-slot:aside>
          <b-img blank blank-color="#abc" width="150" rounded="circle" alt="placeholder"></b-img>
        </template>
        <b-row>
          <b-col class="text-left"><h3>{{userInfo.name}}</h3></b-col>
        </b-row>
        <!-- <h3 >랄랄라</h3> -->
          <b-row align-h="start" class="text-left">
            <b-col cols="4">참여중인 스터디 0</b-col>
            <b-col cols="4">개설한 스터디 0</b-col>
          </b-row>
          <b-row>
            <b-col>
              <b-progress :value="value" :max="max" show-progress animated></b-progress>
            </b-col>
          </b-row>
          <b-row align-h="start" class="text-left">
            <b-col cols="4">관심사</b-col>
            <b-col cols="8">#Web</b-col>
          </b-row>
          <b-row align-h="start" class="text-left">
            <b-col cols="4">한마디</b-col>
            <b-col cols="8">올해안에 취업한다!!!</b-col>
          </b-row>
          <br>
          <b-row align-h="end" class="text-right">
            <b-col cols="2"><b-button variant="warning">회원정보 수정</b-button></b-col>
            <b-col cols="2"><b-button variant="danger" v-b-modal.my-modal>회원탈퇴</b-button></b-col>
          </b-row>
      </b-media>
      
      <b-row class="text-left">
        <h3>내 스터디</h3>
      </b-row>
      <b-row>
        <b-col class="text-left">
          <h6>현재 스터디가 없습니다.</h6>
        </b-col>
      </b-row>
      <b-row class="text-left">
        <h3>스터디 요청</h3>
      </b-row>
      <b-row>
        <b-table
          :no-border-collapse="true"
          :items="items"
          :fields="fields"
        ></b-table>
      </b-row>
    </div>
    <b-modal id="my-modal">
      <b-form id="checkemail" @submit.prevent="authDelete(deleteInfo)">
        <h1>정말 삭제를 원하시면 이메일과 비밀번호를 한번 더 입력해주세요</h1>
        <b-row>이메일 아이디</b-row>
        <b-row>
          <b-form-input
            id="input-1"
            v-model="deleteInfo.email"
            type="email"
            required
            placeholder="이메일 아이디를 입력하세요"
          ></b-form-input>
        </b-row>
        <br>
        <b-row>비밀번호</b-row>
        <b-row>
          <b-form-input
            id="input-2"
            v-model="deleteInfo.password"
            required
            placeholder="비밀번호를 입력하세요"
          ></b-form-input>
        </b-row>
        <b-button type="submit" block variant="danger" size="lg">정말 삭제하기</b-button>
      </b-form>
    </b-modal>
  </b-container>
</template>

<script>
import {mapState,mapActions} from 'vuex'
import MD5 from 'md5'
import Axios from 'axios'
export default {
  data() {
    return {
      mainProps: { blank: true, blankColor: '#777', width: 75, height: 75, class: 'm1' },
      value: 90,
      max: 100,
      
      fields: ['상태', '스터디명', '진행 기간', '버튼'],
      items: [
        { 상태: '요청 중', 스터디명: '알고리즘 초급 스터디(코드 리뷰)', '진행 기간': '2020-03-01 ~ 2020-04-03', '버튼': '요청취소'},
        { 상태: '가입', 스터디명: '즐거운 vuex', '진행 기간': '2020-03-01 ~ 2020-04-03', '버튼': '탈퇴'},
        { 상태: '가입', 스터디명: '같이해요 spring', '진행 기간': '2020-03-01 ~ 2020-04-03', '버튼': '탈퇴'}
      ],
      userInfo: {
        chemail: null,
        chpassword: null,
        sex: null,
        name: null,
        age: null
      },
      deleteInfo: {
        email: null,
        password: null
      }
    }
  },

  computed: mapState([
    'email',
    'password'
  ]),

  methods: {
    checkemail () {
      if (MD5(this.userInfo.chemail) != this.email || MD5(this.userInfo.chpassword) != this.password) {
        alert("이메일과 비밀번호를 한번더 확인해주세요!")
        return false
      }
      const checkWindow = document.querySelector('#checkemail')
      checkWindow.className = 'd-none'
      const userWindow = document.querySelector('#user-window')
      userWindow.className = ''
      Axios.get(`http://localhost:3000/user/${this.userInfo.chemail}`)
      .then( res => {
        this.userInfo.sex = res.data.sex
        this.userInfo.age = res.data.age
        this.userInfo.name = res.data.name
      })
      .catch( err => {console.log(err)} )
    },
    ...mapActions(['authDelete']),
  }
}
</script>

<style>

</style>