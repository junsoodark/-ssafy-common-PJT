<template>
  <div>
  <b-container>
    <br>
    <b-row>
      <b-col class="totheleft text-center" cols="12"><h1>{{ team.title }}</h1></b-col>
      <b-col v-if="isLoggedIn" class="totheright my-3 text-center" offset="8" cols="4">
        <b-button v-if="isMember" @click="toAricle" class="mr-1">게시판 보기</b-button>
        <!-- 탈퇴 -->
        <b-button v-if="isMember" v-b-modal.modal-secession variant="info">탈퇴신청</b-button>

        <b-modal
          id="modal-secession"
          ref="modal"
          title="스터디를 탈퇴하시겠습니까?"
          @show="resetModal"
          @hidden="resetModal"
          @ok="secessionOk"
        >
          <form ref="form" @submit.stop.prevent="secessionSubmit">
            <b-form-group
              label="다음 글을 옮겨 적으세요"
              label-for="name-input"
              invalid-feedback="message is required"
            >
              <div class="input-group input-group">
                <p class="form-control text-center" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">해당 스터디를 탈퇴하겠습니다.</p>
              </div>
              <b-form-input
                id="name-input"
                v-model="checkDelete"
                class="text-center"
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
        <!-- 여기까지 -->
        <b-button v-if="!isMember" v-b-modal.modal-prevent-closing variant="info">가입신청</b-button>

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
        <b-card-text>{{ team.numMembers }} / {{ team.maxMembers }} 명</b-card-text>
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
        <b-card-text>{{ team.si }} {{ team.gu }}</b-card-text>
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
      <b-col v-if="email == team.mgrEmail" class="totheright my-3 text-center" offset="8" cols="4">
        <router-link :to="{ name: 'UpdateTeam', params: {id:study_id} }" variant="primary" tag="b-button">스터디 수정</router-link> |
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
      checkSecessionForm: '해당 스터디를 탈퇴하겠습니다.',
      isMember: false,
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
        var params = new URLSearchParams()
        params.append('studyId',this.study_id)
        Axios({method:'DELETE',url:`${API_URL}study`,params:params,headers:{'Content-Type': 'application/json; charset=utf-8'}})
        .then(() => {
          alert('스터디가 삭제되었습니다.')
          this.checkDelete = ''
          this.$router.push({ name: "StudyList" })
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
        this.isMember = true
        this.team.numMembers += 1
      })
      .catch(err => {
        alert('이미 가입된 멤버입니다.')
        console.log('aaaa', err)
      })


      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
      })
    },
    toAricle () {
      this.$router.push({ name: "StudyArticle", params: {id:this.study_id}})
    },
    secessionSubmit () {
      if (this.checkDelete === this.checkSecessionForm) {
        var params = new URLSearchParams()
        params.append('email',this.email)
        params.append('studyId',this.study_id)
        Axios({method:'DELETE',url:`${API_URL}study/member`,params:params,headers:{'Content-Type': 'application/json; charset=utf-8'}})
        .then(() => {
          alert('탈퇴가 성공적으로 진행되었습니다.')
          this.checkDelete = ''
          this.isMember = false
          this.team.numMembers -= 1
          this.$nextTick(() => {
            this.$bvModal.hide('modal-secession')
          })
        })
        .catch(err => {
          alert(err.response.data)
        })
      } else {
        alert('일치하지 않습니다.')
      }
    },
    secessionOk(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault()
      // Trigger submit handler
      this.secessionSubmit()
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
    Axios.get(`${API_URL}study/email?email=${this.email}`)
    .then(res => {
      for (var i=0; i<res.data.length; i++) {
        if (res.data[i].studyId == this.study_id) {
          this.isMember = true
        }
      }
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
