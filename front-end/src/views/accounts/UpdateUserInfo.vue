<template>
  <b-container class="bv-example-row my-5">
    <h1>회원정보 수정 페이지</h1>
    <br>
    <!-- 이메일 -->
    <b-row>
      <b-col class="input-group input-group-lg" >
        <div class="input-group-prepend">
          <span class="input-group-text" style="width: 9rem;" id="inputGroup-sizing-default">이메일</span>
        </div>
        <p class="form-control text-center" style="background-color: #F8F8FF" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" >{{ email }}</p>
      </b-col>
    </b-row>
    <br>
    <!-- 이름 -->
    <b-form>
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;" id="inputGroup-sizing-default">이름</span>
          </div>
          <input :value="form.name" @input="updateNickname" type="text" class="form-control text-center" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
        </b-col>
      </b-row>
      <br>
      <!-- 나이 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;" id="inputGroup-sizing-default">나이</span>
          </div>
          <input :value="form.age" @input="updateAge" type="number" class="form-control text-center" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
        </b-col>
      </b-row>
      <br>
      <!-- 성별 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;" id="inputGroup-sizing-default">성별</span>
          </div>

          <b-form-radio v-model="form.sex" @input="updateSex" type="number" name="some-radios" value="1" class="form-control text-center"
            >남자</b-form-radio
          >
          <b-form-radio v-model="form.sex" @input="updateSex" type="number" name="some-radios" value="2" class="form-control text-center"
            >여자</b-form-radio
          >

        </b-col>
      </b-row>

      <br>
      <b-button v-b-modal.modal-prevent-closing block size="lg" variant="info">수정하기</b-button>

      <b-modal
        id="modal-prevent-closing"
        ref="modal"
        title="비밀번호를 입력해주세요"
        @show="resetModal"
        @hidden="resetModal"
        @ok="handleOk"
      >
        <form ref="form" @submit.stop.prevent="update_user_info">
          <b-form-group
            :state="passwordState"
            label="비밀번호"
            label-for="password-input"
            invalid-feedback="Password is required"
          >
            <b-form-input
              id="password-input"
              v-model="password"
              type="password"
              :state="passwordState"
              required
            ></b-form-input>
          </b-form-group>
        </form>
      </b-modal>
    </b-form>
  </b-container>
</template>

<script>
import { mapState } from 'vuex';
import Axios from 'axios';
const API_URL = process.env.VUE_APP_LOCAL_URL
export default {
  data() {
    return {
      password: '',
      passwordState: null,
      form: {
        name : '',
        sex : '',
        age : '',
      },
      tempSex: '',
      selected: null,
    };
  },
  computed: {
    ...mapState({
      email: state => state.moduleName.email,
    }),
  },

  methods: {
    updateNickname(e) {
      this.form.name = e.target.value
    },
    updateSex(e) {
      console.log('ee', e)
      this.form.sex = e
    },
    updateAge(e) {
      this.form.age = e.target.value
    },
    // modal
    checkFormValidity() {
      const valid = this.$refs.form.checkValidity()
      this.passwordState = valid
      return valid
    },
    resetModal() {
      this.password = ''
      this.passwordState = null
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

      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
      })
      
      const params = {
        'name' : this.form.name,
        'sex' : this.form.sex,
        'age' : this.form.age,
        'email' : this.email,
        'password' : this.password
      }
      if (this.form.name === '') (
        params.name = this.newName
      ) 
      if (this.form.sex === '') (
        params.sex = this.newSex
      )
      if (this.form.age === '') (
        params.age = this.newAge
      )
      
      console.log(params)
      Axios.put(`${API_URL}user`, params, {
        headers: {
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
          'user-email': sessionStorage.getItem('user-email')
        }
      })
      .then((res) => {
        alert(res.data)
        this.$store.commit('EDIT_USER_INFO', params)
      })
      .catch((err) => {
        alert("비밀번호를 확인해주세요")
        console.log(err)
      })
    },
    
  },
  created () {
    Axios.get(`${API_URL}user/${this.email}`, {
        headers: {
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
          'user-email': sessionStorage.getItem('user-email')
        }
      })
    .then(res => {
      this.form.name = res.data.name
      this.form.sex = res.data.sex
      this.form.age = res.data.age
    })
    .catch(err => {
      console.log(err)
    })
  }

}
</script>

<style>

</style>