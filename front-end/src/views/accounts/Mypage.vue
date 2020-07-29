<template>
  <b-container>
    <h1>마이페이지</h1>
    
    <div >
      <b-media>
        <template v-slot:aside>
          <b-img blank blank-color="#abc" width="150" rounded="circle" alt="placeholder"></b-img>
        </template>
        <b-row>
          <b-col class="text-left"><h3>{{ email }}</h3></b-col>
          <b-col class="text-left"><h3>{{ name }}</h3></b-col>
          <b-col class="text-left"><h3 v-if="sex == 1">남자</h3><h3 v-if="sex == 2">여자</h3></b-col>
          <b-col class="text-left"><h3>{{ age }}</h3></b-col>
        </b-row>
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
            <b-col cols="2"><b-button route :to="{name: 'UpdateUserInfo'}" variant="warning">회원정보 수정</b-button></b-col>
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
      <b-form id="checkemail" @submit.prevent="deleteUserAccount(confirmPassword)">
        <h1>정말 삭제를 원하시면 비밀번호를 입력해주세요</h1>
        <br>
        <b-row>비밀번호</b-row>
        <b-row>
          <b-form-input
            id="input-2"
            required
            type="password"
            v-model="confirmPassword"
            placeholder="비밀번호를 입력하세요"
          ></b-form-input>
        </b-row>
        <b-button type="submit" block variant="danger" size="lg">정말 삭제하기</b-button>
      </b-form>
    </b-modal>
  </b-container>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import Axios from 'axios';
import router from "@/router";
import VueCookies from "vue-cookies";
const API_URL = process.env.VUE_APP_LOCAL_URL

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
      confirmPassword: null,
    }
  },

  computed: {
    ...mapState({
      email: state => state.moduleName.email,
      name: state => state.moduleName.name,
      sex: state => state.moduleName.sex,
      age: state => state.moduleName.age
    }),
    ...mapState(['userInfo'])
  },

  methods: {
    ...mapActions(['authDelete']),
    deleteUserAccount(data) {
      const params = {
        email: this.email,
        password: data,
      }
      Axios({method:'DELETE', url:`${API_URL}user`,params:params,headers:{'Content-Type': 'application/json; charset=utf-8'}})
      .then(res => {
        alert(res.data)
        VueCookies.remove("auth-token")
        this.$store.dispatch('logout')
        router.push({ name: "Home" })
      })
      .catch(err => {
        console.log(err)
        alert('삭제 실패. 비밀번호를 확인해주세요!')
      })
    },
  }

}
</script>

<style>

</style>