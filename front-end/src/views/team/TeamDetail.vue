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
        <div v-if="!isMember">
          <b-button v-if="!isReady" v-b-modal.modal-prevent-closing variant="info">가입신청</b-button>
          <b-button v-if="isReady" variant="warning" @click="cancleSubmit">가입 신청 취소</b-button>
        </div>
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
        <router-link :to="{ name: 'UpdateTeam', params: {id:study_id} }" variant="primary" tag="b-button">스터디 수정</router-link>
        <b-button v-if="isLoggedIn" class="ml-2" @click="$bvModal.show('modal-scoped')" variant="danger">스터디 삭제</b-button>

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
    <b-row class="my-2" v-if="email == team.mgrEmail">
      <div class="col-6">
        <h1>팀원들</h1>
        <b-row>
          <b-col cols="8" class="p-0"><b-list-group-item >이름</b-list-group-item></b-col>
          <b-col cols="4" class="p-0"><b-list-group-item >강퇴</b-list-group-item></b-col>
        </b-row>
        <hr>
        <b-row v-for="item in memberList" :key="item.name">
          <b-col cols="8" class="p-0"><b-list-group-item style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis; height:50px;">{{ item.name }}</b-list-group-item></b-col>
          <b-col cols="4" class="p-0"><b-list-group-item style="height:50px;" class="d-flex justify-content-center" @click="banMember(item.email)"><b-button variant="danger" v-if="item.email != team.mgrEmail">강퇴</b-button></b-list-group-item></b-col>
        </b-row>
      </div>
      <div class="col-6">
        <h1>가입 신청자</h1>
        <b-row>
          <b-col cols="6" class="p-0"><b-list-group-item >이름</b-list-group-item></b-col>
          <b-col cols="3" class="p-0"><b-list-group-item >승인</b-list-group-item></b-col>
          <b-col cols="3" class="p-0"><b-list-group-item >거부</b-list-group-item></b-col>
        </b-row>
        <hr>
        <b-row v-for="item in applyList" :key="item.name">
          <b-col cols="6" class="p-0"><b-list-group-item style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis; height:50px;">{{ item.name }}</b-list-group-item></b-col>
          <b-col cols="3" class="p-0"><b-list-group-item style="height:50px;" class="d-flex justify-content-center"><b-button variant="success" @click="approveMember(item.email,item.name)">승인</b-button></b-list-group-item></b-col>
          <b-col cols="3" class="p-0"><b-list-group-item style="height:50px;" class="d-flex justify-content-center"><b-button variant="danger" @click="disapproveMember(item.email)">거부</b-button></b-list-group-item></b-col>
        </b-row>
      </div>
    </b-row>
  </b-container>
</div>
</template>


<script>
import Axios from 'axios'
import { mapGetters, mapState } from 'vuex'
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
      memberList: [],
      applyList: [],
      isMember: false,
      isReady: false
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
        Axios({method:'DELETE',url:`${API_URL}study`,params:params,headers:{'Content-Type': 'application/json; charset=utf-8','jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),'user-email': sessionStorage.getItem('user-email')}})
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
        url: `${API_URL}study/member/apply`,
        params: params,
        headers: { 
          "Content-Type": "application/json; charset=utf-8", 
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
          'user-email': sessionStorage.getItem('user-email')
        },
      })
      .then(res => {
        alert(res.data)
        this.isReady = true
      })
      .catch(err => {
        alert(err.response.data.msg)
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
        Axios({method:'DELETE',url:`${API_URL}study/member`,params:params,headers:{'Content-Type': 'application/json; charset=utf-8','jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),'user-email': sessionStorage.getItem('user-email')}})
        .then(() => {
          alert('탈퇴가 성공적으로 진행되었습니다.')
          this.checkDelete = ''
          this.isMember = false
          this.isReady = false
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
    },

    cancleSubmit () {
      Axios({
        method: "DELETE",
        url: `${API_URL}study/member/disapply`,
        params: {'email':this.email,'studyId':this.study_id},
        headers: { 
          "Content-Type": "application/json; charset=utf-8", 
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
          'user-email': sessionStorage.getItem('user-email')
        }
      })
      .then(res => {
        console.log(res)
        this.isReady = false
      })
      .catch(err => {
        alert(err.response.msg)
      })
    },

    banMember (banEmail) {
      if (banEmail == this.team.mgrEmail) {
        alert('팀장님 탈주하시면 안됩니다')
        return false
      }
      Axios({
        method: "DELETE",
        url: `${API_URL}study/member`,
        params: {'email':banEmail,'studyId':this.study_id},
        headers: { 
          "Content-Type": "application/json; charset=utf-8", 
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
          'user-email': sessionStorage.getItem('user-email')
        }
      })
      .then(res => {
        console.log(res)
        var newMember = []
        for (var i=0; i<this.memberList.length; i++) {
          if (this.memberList[i].email != banEmail) {
            newMember.push(this.memberList[i])
          }
        }
        this.memberList = newMember
        alert('성공적으로 탈퇴시켰습니다')
      })
      .catch(err => {
        alert(err.response.data)
      })
    },

    approveMember (approveEmail,approveName) {
      Axios({
        method: "POST",
        url: `${API_URL}study/member/approve`,
        params: {'email':approveEmail,'studyId':this.study_id},
        headers: { 
          "Content-Type": "application/json; charset=utf-8", 
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
          'user-email': sessionStorage.getItem('user-email')
        }
      })
      .then(res => {
        console.log(res)
        var newMember = []
        for (var i=0; i<this.applyList.length; i++) {
          if (this.applyList[i].email != approveEmail) {
            newMember.push(this.applyList[i])
          }
        }
        this.applyList = newMember
        this.memberList.push({'name':approveName,'email':approveEmail})
        alert("성공적으로 승인했습니다")
      })
      .catch(err => {
        alert(err.response.data)
      })
    },

    disapproveMember (disEmail) {
      Axios({
        method: "DELETE",
        url: `${API_URL}study/member/disapprove`,
        params: {'email':disEmail,'studyId':this.study_id},
        headers: { 
          "Content-Type": "application/json; charset=utf-8", 
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
          'user-email': sessionStorage.getItem('user-email')
        }
      })
      .then(res => {
        console.log(res)
        var newMember = []
        for (var i=0; i<this.applyList.length; i++) {
          if (this.applyList[i].email != disEmail) {
            newMember.push(this.applyList[i])
          }
        }
        this.applyList = newMember
        alert('성공적으로 거부했습니다')
      })
      .catch(err => {
        alert(err.response.data)
      })
    }
  },
  created() {
    // 스터디 디테일 정보 가져오기
    Axios({
      method: "GET",
      url: `${API_URL}study/${this.study_id}`,
      headers: { 
        "Content-Type": "application/json; charset=utf-8", 
        'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
        'user-email': sessionStorage.getItem('user-email')
      }
    })
    .then(res => {
      this.team = res.data
      console.log(this.team)
    })
    .catch(err => {
      console.log(err)
      this.$router.push({ name: "NotFound" })
    })
    if (!this.isLoggedIn) { 
      return false 
    }

    // 해당 스터디의
    Axios({
      method: "GET",
      url: `${API_URL}study/${this.study_id}/list`,
      headers: { 
        "Content-Type": "application/json; charset=utf-8", 
        'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
        'user-email': sessionStorage.getItem('user-email')
      }
    })
    .then(res => {
      this.memberList = res.data
    })
    .catch(err => {
      alert(err.response.msg)
    })

    Axios({
      method: "GET",
      url: `${API_URL}study/${this.study_id}/approvelist`,
      headers: { 
        "Content-Type": "application/json; charset=utf-8", 
        'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
        'user-email': sessionStorage.getItem('user-email')
      }
    })
    .then(res => {
      this.applyList = res.data
    })
    .catch(err => {
      alert(err.response.msg)
    })
    // 로그인 후 내가 스터디 멤버 또는 가입 신청한 멤버인가
    Axios({
      method: "GET",
      url: `${API_URL}study/email?email=${this.email}`,
      headers: { 
        'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
        'user-email': sessionStorage.getItem('user-email')
      }
    })
    .then(res => {
      for (var i=0; i<res.data.length; i++) {
        if (res.data[i].studyId == this.study_id) {
          this.isMember = true
          break
        }
      }
      for (var j=0; j<this.applyList.length; j++) {
        if (this.applyList[j].email == this.email) {
          this.isReady = true
          break
        }
      }
    })
  },
  components: {
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
