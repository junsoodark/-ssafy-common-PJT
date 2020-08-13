<template>

    <!-- <b-card
      img-src=""
      img-alt="Image"
      img-top
      tag="article"
      style=" background-color: #87CEFA; "
      class="mb-2 text-left cursor:pointer"
      text-variant="white"
      header="대분류"
      header-text-variant="right"
      @click="clickCard"
      footer="팀장 @@@"
    > 
      <b-container class="p-0" style="cursor:pointer">
        <b-row>
          <b-col cols="8"><h5>{{ team.title }}</h5></b-col>
          <b-col cols="4"><p>인원수</p></b-col>
        </b-row>
        <b-row class="p-1">
          <b-col style="background-color: #FFFFF0" ><p class="m-1" style="color: black;">일정/시간</p></b-col>
        </b-row>
        <b-row class="p-1">
          <b-col style="background-color: #FFFFF0" ><p class="m-1" style="color: black">온/오프라인</p></b-col>
        </b-row>
        <b-row class="p-1">
          <b-col style="background-color: #FFFFF0" ><p class="m-1" style="color: black">콘텐트: {{ team.content }}</p></b-col>
        </b-row>
      </b-container>
      
    </b-card> -->


  <b-container class="border rounded p-0">

    <!-- 주황색 rgb(243, 156, 18) -->
    <!-- 핑크색 rgb(242, 142, 240) -->
    <b-row class="mx-2 pl-0 text-left text-light" style="text-color: white">
      <b-col cols="6" class="mt-4" style="background-color: rgb(46, 204, 113); ">
        <b-icon icon="person-circle" font-scale="1"></b-icon> 4/6
      </b-col>
      <b-row>
        <b-col style="" md="8" offset-md="4"><img :src="defaultImageUrl" width="200"  alt="" class="rounded-circle"></b-col>
      </b-row>
    </b-row>


    <b-row class="my-1">
      <b-col>{{ team.title }}</b-col>
    </b-row>
    <b-row class="my-1">
      <b-col md="1" offset-md="2"><b-icon icon="building" font-scale="1"></b-icon></b-col>
      <b-col md="9" offset-md="0" class="text-left"> <b-badge pill variant="secondary" class="studyCard">서울 / 강남</b-badge></b-col>
    </b-row>
    <b-row class="my-1">
      <b-col md="1" offset-md="2"><b-icon icon="calendar2-check-fill" font-scale="1"></b-icon></b-col>
      <b-col md="9" offset-md="0" class="text-left"> <b-badge pill variant="secondary" class="studyCard">추후 협의 / 2회</b-badge></b-col>
    </b-row>
    <b-row class="my-1">
      <b-col md="1" offset-md="2"><b-icon icon="tv-fill" font-scale="1"></b-icon></b-col>
      <b-col md="9" offset-md="0" class="text-left"><b-badge pill variant="secondary"  class="studyCard">오프라인</b-badge></b-col>
    </b-row>
  </b-container>
</template>

<script>
import router from "@/router";

export default {
  data () {
    return {
      defaultImageUrl: 'https://previews.123rf.com/images/salamatik/salamatik1801/salamatik180100019/92979836-%ED%94%84%EB%A1%9C%ED%95%84-%EC%9D%B5%EB%AA%85%EC%9D%98-%EC%96%BC%EA%B5%B4-%EC%95%84%EC%9D%B4%EC%BD%98-%ED%9A%8C%EC%83%89-%EC%8B%A4%EB%A3%A8%EC%97%A3-%EC%82%AC%EB%9E%8C%EC%9E%85%EB%8B%88%EB%8B%A4-%EB%82%A8%EC%84%B1-%EA%B8%B0%EB%B3%B8-%EC%95%84%EB%B0%94%ED%83%80-%EC%82%AC%EC%A7%84-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C-%EC%9E%90-%ED%9D%B0%EC%83%89-%EB%B0%B0%EA%B2%BD%EC%97%90-%EA%B3%A0%EB%A6%BD-%EB%B2%A1%ED%84%B0-%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8-%EB%A0%88%EC%9D%B4-%EC%85%98.jpg',
    }
  },
  props: {
    team: Object
  },
  computed: {

  },
  methods: {
    clickCard() {
      router.push({ name: 'StudyDetail', params: {id:this.team.studyId}})
    }
  }
}
</script>

<style>
.studyCard {
  width:200px;
}
</style>