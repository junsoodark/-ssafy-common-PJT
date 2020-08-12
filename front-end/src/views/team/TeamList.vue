<template>
  <b-container>
    <br>
    <h1>스터디 목록</h1>
    <br>

    <div id="searchStudy">
      <b-row>
        <b-col md="7" offset-md="2">
          <b-input-group class="mt-3">
            <template v-slot:append>
              <b-input-group-text><strong>확인</strong></b-input-group-text>
            </template>
            <b-form-input list="my-list-id" placeholder="분야를 검색하세요"></b-form-input>

            <datalist id="my-list-id">
              <option v-for="size in sizes" :key="size">{{ size }}</option>
            </datalist>
          </b-input-group>
        </b-col>
        <b-col md="3">
          <b-button route :to="{name: 'CreateTeam'}" variant="info">스터디 만들기</b-button>
        </b-col>
      </b-row>
    </div>
    <div class="my-badges">
      <b-badge pill class="my-badge1">코딩테스트</b-badge>
      <b-badge pill class="my-badge2">NCS/인적성</b-badge>
      <b-badge pill class="my-badge3">면접</b-badge>
    </div>

    <br><br>
    <div class="row row-cols-3">
      <TeamListItem class="col mb-3" v-for="team in TeamList" :key="team.studyId" v-bind:team="team"></TeamListItem>
    </div>
  </b-container>
</template>

<script>
import TeamListItem from '../../components/TeamlistItem.vue'
import { mapGetters } from 'vuex'
import Axios from 'axios'

const API_URL = process.env.VUE_APP_LOCAL_URL

export default {
  components: {
    TeamListItem
  },
  data () {
    return {
      TeamList: [],
      sizes: ['코딩테스트', '인적성', '면접']
    }
  },
  created () {
    Axios.get(`${API_URL}study/all`)
    .then(res => {
      this.TeamList = res.data.reverse()
    })
    .catch(err => {
      console.log(err)
    })
  },
  computed: {
    ...mapGetters(['isLoggedIn'])
  }
}
</script>

<style>
#searchStudy {
  width: 500 px;
}
.my-badges {
  margin-top: 10px;
}
.my-badge1 {
  background-color: rgb(46, 204, 113) !important;
  padding: 5px !important;
  margin-right: 5px;
  margin-left: 5px;
}
.my-badge2 {
  background-color:rgb(242, 142, 240) !important;
  padding: 5px !important;
  margin-right: 5px;
  margin-left: 5px;
}
.my-badge3 {
  background-color:rgb(243, 156, 18) !important;
  padding: 5px !important;
  margin-right: 5px;
  margin-left: 5px;
}
</style>