<template>
  <div>
    <b-card
      img-src=""
      img-alt="Image"
      img-top
      tag="article"
      style="max-width: 20rem; background-color: #87CEFA; "
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
        <!-- 일정 -->
        <b-row class="p-1">
          <b-col style="background-color: #FFFFF0" ><p class="m-1" style="color: black;">일정/시간</p></b-col>
        </b-row>
        <!-- 온/오프라인 -->
        <b-row class="p-1">
          <b-col style="background-color: #FFFFF0" ><p class="m-1" style="color: black">온/오프라인</p></b-col>
        </b-row>
        <!-- 콘텐트 -->
        <b-row class="p-1">
          <b-col style="background-color: #FFFFF0" ><p class="m-1" style="color: black">콘텐트: {{ team.content }}</p></b-col>
        </b-row>
      </b-container>
      
      <!-- <router-link :to="{ name: 'StudyDetail', params: {id:team.studyId} }" variant="primary" tag="b-button">{{team.title}}팀의 상세정보</router-link> -->
    </b-card>
  </div>
</template>

<script>
import router from "@/router";

export default {
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

</style>