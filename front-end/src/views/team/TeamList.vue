<template>
  <div>
    <h1>TeamList</h1>
    <router-link :to="{ name: 'CreateTeam'}">스터디 만들기</router-link>
    <b-container class="bv-example-row">
      <b-row>
        <TeamListItem class="col-4" v-for="team in TeamList" :key="team" v-bind:team="team"></TeamListItem>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import TeamListItem from '../../components/TeamlistItem.vue'
export default {
  components: {
      TeamListItem
  },
  data () {
      return {
          TeamList: [0,1,2,3,4,5,6,7,8]
      }
  },
  created () {
  }
}
</script>

<style>

</style>