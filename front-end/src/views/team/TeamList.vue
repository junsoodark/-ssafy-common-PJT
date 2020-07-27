<template>
  <div>
    <!-- <TeamListItem v-for="team in teamList" :key="team"></TeamListItem> -->
    <h1>TeamList</h1>
    <router-link :to="{ name: 'CreateTeam'}">스터디 만들기</router-link>
    <b-container class="bv-example-row">
      <b-row>
          <TeamListItem class="col-4" v-for="team in TeamList" :key="team"></TeamListItem>
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
            TeamList: [0,0,0,0,0,0,0,0,0]
        }
    },
    created () {

    }
}
</script>

<style>

</style>