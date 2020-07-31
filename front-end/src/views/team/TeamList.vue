<template>
  <div>
    <h1>TeamList</h1>
    <router-link v-if="isLoggedIn" :to="{ name: 'CreateTeam'}">스터디 만들기</router-link>
    <b-container class="bv-example-row">
      <b-row>
        <TeamListItem class="col-4" v-for="team in TeamList" :key="team" v-bind:team="team"></TeamListItem>
      </b-row>
    </b-container>
  </div>
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
        TeamList: []
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

</style>