<template>
  <div>
    <h1 class="my-3">{{teamTitle}} 팀의 게시판입니다</h1>
    <b-button v-b-modal.modal-1 class="my-3">글쓰기</b-button>

    <b-modal id="modal-1" title="글쓰기" hide-footer>
      <TextEditor v-bind:writer="writer" v-on:endSubmit="closeModal" v-bind:studyId="studyId"></TextEditor>
    </b-modal>
    <b-container>
      <b-list-group>
        <b-row>
          <b-col cols="4" class="p-0"><b-list-group-item >TITLE</b-list-group-item></b-col>
          <b-col cols="8" class="p-0"><b-list-group-item >CONTENT</b-list-group-item></b-col>
        </b-row>
        <hr>
        <b-row v-for="item in articles" :key="item.studyId">
          <b-col cols="4" class="p-0"><b-list-group-item route :to="{ name: 'ArticleDetail', params: {studyid:studyId, articleid:item.id} }" style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis; height:50px;">{{ item.title }}</b-list-group-item></b-col>
          <b-col cols="8" class="p-0"><b-list-group-item route :to="{ name: 'ArticleDetail', params: {studyid:studyId, articleid:item.id} }" style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis; height:50px;">{{ item.content }}</b-list-group-item></b-col>
        </b-row>
      </b-list-group>
    </b-container>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import Axios from 'axios'
import TextEditor from '../../components/common/TextEditor.vue'
const API_URL = process.env.VUE_APP_LOCAL_URL
export default {
  data () {
    return {
      studyId: this.$route.params.id,
      isMember: false,
      teamTitle: null,
      articles: [{title:'안녕'}],
      writer: null,
    }
  },
  computed: {
    ...mapState({
      email: state => state.moduleName.email,
    })
  },
  created () {
    Axios.get(`${API_URL}study/email?email=${this.email}`)
    .then(res => {
      for (var i=0; i<res.data.length; i++) {
        if (res.data[i].studyId == this.studyId) {
          this.isMember = true
          this.teamTitle = res.data[i].title
        }
      }
      if (!this.isMember) {
        console.log(this.isMember)
        this.$router.push({name: 'Home'})
      }
    })
    Axios({
      method: "GET",
      url: `${API_URL}post/study/${this.studyId}`,
      headers: { "Content-Type": "application/json; charset=utf-8",
                'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
                'user-email': sessionStorage.getItem('user-email')},
    })
    .then(res => {
      console.log(res)
      this.articles = res.data
    })
    .catch(() => {alert('스터디팀 정보를 불러올 수 없습니다')})
    Axios({
      method: "GET",
      url: `${API_URL}user/${this.email}`,
      headers: { "Content-Type": "application/json; charset=utf-8",
                'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
                'user-email': sessionStorage.getItem('user-email')},
    })
    .then(res => {
      this.writer = res.data.id
    })
    .catch(() => {alert('사용자 정보를 불러올 수 없습니다')})
  },
  components: {
    TextEditor
  },
  methods: {
    closeModal () {
      Axios({
        method: "GET",
        url: `${API_URL}post/study/${this.studyId}`,
        headers: { "Content-Type": "application/json; charset=utf-8",
                  'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
                  'user-email': sessionStorage.getItem('user-email')},
      })
      .then(res => {
        console.log(res)
        this.articles = res.data
      })
      .catch(() => {alert('스터디팀 정보를 불러올 수 없습니다')})
      this.$bvModal.hide('modal-1')
    }
  }
}
</script>

<style>

</style>