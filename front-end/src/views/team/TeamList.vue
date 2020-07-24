<template>
  <div>
    <!-- <TeamListItem v-for="team in teamList" :key="team"></TeamListItem> -->
    <h1>TeamList</h1>
    <router-link :to="{ name: 'CreateTeam'}">스터디 만들기</router-link>
    <div>
      <b-card
        title="Card Title"
        img-src="https://picsum.photos/600/300/?image=25"
        img-alt="Image"
        img-top
        tag="article"
        style="max-width: 20rem;"
        class="mb-2"
      >
        <b-card-text>
          Some quick example text to build on the card title and make up the bulk of the card's content.
        </b-card-text>

        <b-button route to="/study/detail" variant="primary">Go somewhere</b-button>
      </b-card>
    </div>
  </div>
</template>

<script>
// import TeamListItem from '../../components/TeamlistItem.vue'
export default {
    components: {
        // TeamListItem
    },
    data () {
        return {
            teamList: []
        }
    },
    created () {

    }
}
</script>

<style>

</style>