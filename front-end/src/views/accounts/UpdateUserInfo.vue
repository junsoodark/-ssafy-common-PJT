<template>
  <b-container class="bv-example-row my-5">
    <h1>회원정보 수정 페이지</h1>
    
    <b-row>
      <b-col cols="2">이메일 : </b-col>
      <b-col cols='10'>
        <b-list-group>
          <b-list-group-item>{{ email }}</b-list-group-item>
        </b-list-group>
      </b-col>
    </b-row>
    <br>
    <b-form >
      <b-row>
        <b-col cols="2">닉네임 : </b-col>
        <b-col cols="10">
          <input :value="newName" @input="updateNickname">
          <!-- <b-form-input
            id="input-6"
            :value="newName"
            type="text"
            required
            size="lg"
            placeholder="닉네임을 입력해주세요"
            @input="updateNickname"
          ></b-form-input> -->
        </b-col>
      </b-row>
      <br>
      <b-row>
        <b-col cols="2">나이 : </b-col>
        <b-col cols="10">
          <input :value="newAge" @input="updateAge">
          <!-- <b-form-group id="input-group-7">
            <b-form-input
              id="input-7"
              :value="newAge"
              type="number"
              required
              size="lg"
              placeholder="나이를 입력해주세요"
            ></b-form-input>
          </b-form-group> -->
        </b-col>
      </b-row>
      <br>
      <b-row>
        <b-col cols="2">성별 : </b-col>
        <b-col cols="5">
          <b-form-group id="input-group-8">
            <b-form-radio v-model="newSex" type="number" name="some-radios" value="1">남자</b-form-radio>
          </b-form-group>
        </b-col>
        <b-col cols="5">
          <b-form-group id="input-group-8">
            <b-form-radio v-model="newSex" type="number" name="some-radios" value="2">여자</b-form-radio>
          </b-form-group>
        </b-col>
      </b-row>

      <br>
      <b-button v-b-modal.modal-prevent-closing variant="primary">수정하기</b-button>

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
      }
    };
  },
  computed: {
    ...mapState({
      email: state => state.moduleName.email,
      newName: state => state.moduleName.name,
      newSex: state => state.moduleName.sex,
      newAge: state => state.moduleName.age,
    }),
  
  },

  methods: {
    updateNickname(e) {
      this.form.name = e.target.value
    },
    updateSex(e) {
      this.form.sex = e.target.value
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
      Axios.put(`${API_URL}user`, params)
      .then((res) => {
        alert(res.data)
        this.$store.commit('EDIT_USER_INFO', params)
      })
      .catch((err) => {
        alert("비밀번호를 확인해주세요")
        console.log(err)
      })
    },
    
  }

}
</script>

<style>

</style>