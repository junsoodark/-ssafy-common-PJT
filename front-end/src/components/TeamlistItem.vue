<template>
  <div>
    <b-card
      :title="team.title"
      img-src="https://picsum.photos/600/300/?image=25"
      img-alt="Image"
      img-top
      tag="article"
      style="max-width: 20rem;"
      class="mb-2"
    >
      <b-card-text>
        {{team.content}}
      </b-card-text>

      <router-link :to="{ name: 'StudyDetail', params: {id:team.studyId} }" variant="primary" tag="b-button">{{team.title}}팀의 상세정보</router-link>
    </b-card>
  </div>
</template>

<script>
export default {
  props: {
    team: Object
  }
}
</script>

<style>

</style>