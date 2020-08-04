<template>
  <div>
    <h1>게시판입니다</h1>
    <b-button v-b-modal.modal-1>글쓰기</b-button>

    <b-modal id="modal-1" title="BootstrapVue" hide-footer>
      <TextEditor></TextEditor>
    </b-modal>
    <b-table striped hover :items="articles"></b-table>
  </div>
</template>

<script>
// import Axios from 'axios'
import TextEditor from '../../components/common/TextEditor.vue'
export default {
  data () {
    return {
      studyId: this.$route.params.id,
      articles: []
    }
  },
  created () {
    // Axios
  },
  components: {
    TextEditor
  }
}
</script>

<style>

</style>