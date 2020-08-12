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
              <b-input-group-text @click="Search"><strong>확인</strong></b-input-group-text>
            </template>
            <b-form-input list="my-list-id" placeholder="분야를 검색하세요" @keyup.enter="Search" v-model="SearchText"></b-form-input>

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
    <br><br>
    <b-row class="mx-1">
      <!-- <b-col cols="4">
        <TeamListItem v-for="team in TeamList" :key="team.studyId" v-bind:team="team"></TeamListItem>
      </b-col> -->
      <TeamListItem class="col-4" v-for="team in TeamList" :key="team.studyId" v-bind:team="team"></TeamListItem>
    </b-row>
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
      sizes: ['코딩테스트', '인적성', '면접'],
      SaveTeamList: [],
      SearchText: null,
      SearchData: [],
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
  },
  methods: {
    Search () {
      if (this.SaveTeamList.length != 0) {
        this.TeamList = this.SaveTeamList
      }
      this.SaveTeamList = this.TeamList
      this.SearchData = []
      for (var i=0; i<this.TeamList.length; i++) {
        if (this.TeamList[i].content.indexOf(this.SearchText) != -1 || this.TeamList[i].title.indexOf(this.SearchText) != -1) {
          this.SearchData.push(this.TeamList[i])
        }
      }
      this.TeamList = this.SearchData
    }
  }
}
</script>

<style>
#searchStudy {
  width: 500 px;
}
</style>