<template>
  <div>
    <TeamListItem v-for="team in teamList" :key="team"></TeamListItem>
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
            teamList: []
        }
    },
    created () {

    }
}
</script>

<style>

</style>