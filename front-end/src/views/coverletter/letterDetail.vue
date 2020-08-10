<template>
  <b-container class="my-3">
    <h1 class="text-center">{{title}}</h1>
    <h3 class="d-flex justify-content-end">글 작성자: {{writer}}</h3>
    <hr class="">
    <div id="content" class="d-flex justify-content-center">{{content}}</div>
  </b-container>
</template>

<script>
export default {
  data () {
    return {
      id: this.$route.params.id,
      title: "제목",
      writer: "작성자",
      content: "글내용"
    }
  }
}
</script>

<style>

</style>