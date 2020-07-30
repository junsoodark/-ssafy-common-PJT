<template>
  <div>
  <b-container>
    <br>
    <b-row>
      <b-col class="totheleft text-center" cols="12"><h1>{{ team.title }}</h1></b-col>
      <b-col class="totheright my-3 text-center" offset="10" cols="2">
        <b-button v-if="isLoggedIn" v-b-modal.modal-prevent-closing variant="info">가입신청</b-button>

        <b-modal
          id="modal-prevent-closing"
          ref="modal"
          title="스터디를 신청하시겠습니까?"
          @show="resetModal"
          @hidden="resetModal"
          @ok="joinOk"
        >
          <form ref="form" @submit.stop.prevent="handleSubmit">
            <b-form-group
              label="신청 메시지"
              label-for="name-input"
              invalid-feedback="message is required"
            >
              <b-form-input
                id="name-input"
                v-model="joinMsg"
                required
              ></b-form-input>
            </b-form-group>
          </form>
          <template v-slot:modal-footer="{ ok, cancel }">
            <!-- <b>Custom Footer</b> -->
            <!-- Emulate built in modal footer ok and cancel button actions -->
            <b-button size="sm" variant="success" @click="ok()">
              신청
            </b-button>
            <b-button size="sm" variant="danger" @click="cancel()">
              취소
            </b-button>
            <!-- Button with custom close trigger value -->
          </template>
        </b-modal>
      </b-col>
    </b-row>
    
  </b-container>
  <b-container>
    <b-card-group deck>
      <b-card header-tag="header" footer-tag="footer">
        <template v-slot:header>
          <b-icon icon="flag-fill" aria-hidden="true"></b-icon> 리더
        </template>
        <b-card-text>{{team.mgrName}}</b-card-text>
      </b-card>

      <b-card header-tag="header" footer-tag="footer">
        <template v-slot:header>
          <b-icon icon="people" aria-hidden="true"></b-icon> 인원
        </template>
        <b-card-text>4 / 5명</b-card-text>
      </b-card>
      
      <b-card header-tag="header" footer-tag="footer">
        <template v-slot:header>
          <b-icon icon="hand-thumbs-up" aria-hidden="true"></b-icon> 성실도
        </template>
        <b-card-text>88% 이상</b-card-text>
      </b-card>
      
      <b-card header-tag="header" footer-tag="footer">
        <template v-slot:header>
          <b-icon icon="file-text" aria-hidden="true"></b-icon> 분야
        </template>
        <b-card-text>개발 > Algorithm</b-card-text>
      </b-card>
      <b-card header-tag="header" footer-tag="footer">
        <template v-slot:header>
          <b-icon icon="house" aria-hidden="true"></b-icon> 진행장소
        </template>
        <b-card-text>온라인</b-card-text>
      </b-card>
    </b-card-group>
    <br>
    <b-card-group deck>
      <b-card header-tag="header" footer-tag="footer">
        <template v-slot:header>
          <b-icon icon="calendar3" aria-hidden="true"></b-icon> 진행기간
        </template>
        <b-card-text>{{team.startDate}} ~ {{team.endDate}}</b-card-text>
      </b-card>
      <b-card header-tag="header" footer-tag="footer">
        <template v-slot:header>
          <b-icon icon="list-check" aria-hidden="true"></b-icon> 일정
        </template>
        <b-card-text>추후 협의 2회</b-card-text>
      </b-card>
      <b-card header-tag="header" footer-tag="footer">
        <template v-slot:header>
          <b-icon icon="alarm" aria-hidden="true"></b-icon> 시간
        </template>
        <b-card-text>주말 시간 협의</b-card-text>
      </b-card>
    </b-card-group>
    <br>
    <b-card-group deck>
      <b-card header-tag="header" footer-tag="footer">
        <template v-slot:header>
          <b-icon icon="info-square-fill" aria-hidden="true"></b-icon> 스터디 소개
        </template>
        <b-card-text>{{ team.content }}</b-card-text>
      </b-card>
    </b-card-group>

    <b-row>
      <b-col class="totheright my-3 text-center" offset="10" cols="2">
        <b-button v-if="isLoggedIn" @click="$bvModal.show('modal-scoped')" variant="danger">스터디 삭제</b-button>

        <b-modal
          id="modal-scoped"
          ref="modal"
          title="스터디를 삭제 하시겠습니까?"
          @show="resetDeleteModal"
          @hidden="resetDeleteModal"
          @ok="deleteOk"
        >
          <form ref="form" @submit.stop.prevent="deleteSubmit">
            <b-form-group
              label="다음 글을 옮겨 적으세요"
              label-for="delete-input"
              invalid-feedback="message is required"
            >
              
              <div class="input-group input-group">
                <p class="form-control text-center" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">해당 스터디를 삭제하겠습니다.</p>
              </div>
              <b-form-input
                id="delete-input"
                v-model="checkDelete"
                required
                class="text-center"
              ></b-form-input>
            </b-form-group>
          </form>
          <template v-slot:modal-footer="{ ok, cancel }">
            <!-- <b>Custom Footer</b> -->
            <!-- Emulate built in modal footer ok and cancel button actions -->
            <b-button size="sm" variant="success" @click="ok()">
              신청
            </b-button>
            <b-button size="sm" variant="danger" @click="cancel()">
              취소
            </b-button>
            <!-- Button with custom close trigger value -->
          </template>
        </b-modal>
      </b-col>
    </b-row>
  </b-container>
  <Caffe class="mt-3 mb-5" v-bind:si="team.si" v-bind:gu="team.gu"></Caffe>
</div>
</template>


<script>
import Axios from 'axios'
import { mapGetters, mapState } from 'vuex'
import Caffe from '../../components/Caffe.vue'
const API_URL = process.env.VUE_APP_LOCAL_URL

export default {
  data() {
    return {
      joinMsg: '',
      study_id: this.$route.params.id,
      team: [],
      checkDelete: '',
      checkDeleteForm: '해당 스터디를 삭제하겠습니다.',
    }
  },
  computed: {
    ...mapState({
      email: state => state.moduleName.email,
    }),
    ...mapGetters(['isLoggedIn']),
  },
  methods: {
    // 삭제 모달
    deleteOk(bvModalEvt) {
      bvModalEvt.preventDefault()
      this.deleteSubmit()
    },
    deleteSubmit() {
      if (this.checkDelete === this.checkDeleteForm) {
        Axios.delete(`${API_URL}study/${this.study_id}`)
        .then(res => {
          alert('스터트가 삭제되었습니다.')
          console.log(res)
          this.$router.push({ name: "TeamList" })
        })
        .catch(err => {
          console.log(err)
          this.$router.push({ name: "NotFound" })
        })
      } else {
        alert('일치하지 않습니다.')
      }
    },
    resetDeleteModal() {
      this.checkDelete = ''
    },
    // 가입 신청 모달
    resetModal() {
      this.joinMsg = ''
    },
    joinOk(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault()
      // Trigger submit handler
      this.joinSubmit()
    },
    joinSubmit() {
      const params = {
        email: this.email,
        studyId: this.study_id
      }

      Axios({
        method: "POST",
        url: `${API_URL}study/member/join`,
        params: params,
        headers: { "Content-Type": "application/json; charset=utf-8" },
      })
      .then(res => {
        alert(res.data)
      })
      .catch(err => {
        alert('이미 가입된 멤버입니다.')
        console.log('aaaa', err)
      })


      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
      })
    }
  },
  created() {
    Axios.get(`${API_URL}study/${this.study_id}`)
    .then(res => {
      this.team = res.data
    })
    .catch(err => {
      console.log(err)
      this.$router.push({ name: "NotFound" })
    })
  },
  components: {
    Caffe
  }
}
</script>

<style>
  .totheright{
    text-align: right;
  }
  .totheleft{
    text-align: left;
  }
</style>
