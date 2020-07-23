<template>
  <b-container>
    <h1>마이페이지</h1>

    <b-media>
      <template v-slot:aside>
        <b-img blank blank-color="#abc" width="150" rounded="circle" alt="placeholder"></b-img>
      </template>
      <b-row>
        <b-col class="text-left"><h3>랄랄라</h3></b-col>
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
          <b-col cols="2"><b-button>회원정보 수정</b-button></b-col>
          <b-col cols="2"><b-button>비밀번호 변경</b-button></b-col>
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
  </b-container>
</template>

<script>
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
    }
  }

}
</script>

<style>

</style>