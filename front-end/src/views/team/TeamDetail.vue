<template>
  <div>
  <b-container>
    <b-row>
      <b-col class="totheleft text-center" cols="12"><h1>{{team.title}} 팀의 상세 정보</h1></b-col>
      <b-col class="totheright my-3 text-center" offset="8" cols="4">
        <b-button  v-if="isLoggedIn" v-b-modal.modal-prevent-closing variant="info">가입신청</b-button>


        <b-modal
          id="modal-prevent-closing"
          ref="modal"
          title="스터디를 신청하시겠습니까?"
          @show="resetModal"
          @hidden="resetModal"
          @ok="handleOk"
        >
          <form ref="form" @submit.stop.prevent="handleSubmit">
            <b-form-group
              :state="nameState"
              label="신청 메시지"
              label-for="name-input"
              invalid-feedback="message is required"
            >
              <b-form-input
                id="name-input"
                v-model="name"
                :state="nameState"
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
        <b-card-text>박준성</b-card-text>
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
        <b-card-text>매일 깃 허브에 1문제 이상 풀이해서 올리고, 행아웃을 통해 코드 리뷰를 함께 하실 스터디원을 모집하고 있습니다.</b-card-text>
      </b-card>
    </b-card-group>

  </b-container>

</div>
</template>

<script>
import Axios from 'axios'
import { mapGetters } from 'vuex'
const API_URL = process.env.VUE_APP_LOCAL_URL
export default {
  data() {
    return {
      name: '',
      nameState: null,
      submittedNames: [],
      study_id: this.$route.params.id,
      team: []
    }
  },
  methods: {
    checkFormValidity() {
      const valid = this.$refs.form.checkValidity()
      this.nameState = valid
      return valid
    },
    resetModal() {
      this.name = ''
      this.nameState = null
    },
    handleOk(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault()
      // Trigger submit handler
      this.handleSubmit()
    },
    handleSubmit() {
      // Exit when the form isn't valid
      if (!this.checkFormValidity()) {
        return
      }
      // Push the name to submitted names
      this.submittedNames.push(this.name)
      // Hide the modal manually
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
  computed: {
    ...mapGetters(['isLoggedIn'])
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