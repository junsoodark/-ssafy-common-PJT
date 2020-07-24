<template>
  <b-container class="bv-example-row my-5">
    <div style="max-width:450px;" class="mx-auto middle" id="email-window">
      <h1>이메일 인증 창입니다</h1>
      <b-form @submit.prevent="checkEmail">
        <b-form-group
          id="input-group-4"
          label="이메일:"
          label-for="input-4"
          description="인증 번호를 받을 수 있는 이메일을 입력해주세요"
        >
          <b-form-input
            id="input-4"
            v-model="email"
            type="email"
            required
            placeholder="이메일을 입력해주세요"
          ></b-form-input>
        </b-form-group>
        <b-button type="submit" variant="primary">이메일 제출</b-button>
      </b-form>
    </div>
    <div style="max-width:450px;" class="mx-auto middle d-none" id="signup-window">
      <h1>회원가입창입니다</h1>
      
      <b-form @submit.prevent="signup">
        <b-form-group
          id="input-group-1"
          label="이메일:"
          label-for="input-1"
          description="인증 번호를 받을 수 있는 이메일을 입력해주세요"
        >
          <b-form-input
            id="input-1"
            v-model="email"
            type="email"
            required
            placeholder="이메일을 입력해주세요"
            disabled
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="input-group-5"
          label="인증 코드:"
          label-for="input-5"
          description="이메일을 확인하여 코드를 입력해주세요"
        >
          <b-form-input
            id="input-5"
            v-model="code"
            type="text"
            required
            placeholder="코드를 입력해주세요"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-2" label="비밀번호:" label-for="input-2">
          <b-form-input
            id="input-2"
            v-model="password"
            type="password"
            required
            placeholder="비밀번호를 입력해주세요"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-3" label="비밀번호 확인:" label-for="input-3">
          <b-form-input
            id="input-3"
            v-model="passwordConfirm"
            type="password"
            required
            placeholder="위에 입력한 비밀번호와 똑같이 입력해주세요"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-6" label="닉네임:" label-for="input-6">
          <b-form-input
            id="input-6"
            v-model="nickname"
            type="text"
            required
            placeholder="닉네임을 입력해주세요"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-7" label="나이:" label-for="input-7">
          <b-form-input
            id="input-7"
            v-model="age"
            type="number"
            required
            placeholder="나이를 입력해주세요"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-8" label="성별:">
          <b-form-radio v-model="sex" type="number" name="some-radios" value="1">남자</b-form-radio>
          <b-form-radio v-model="sex" type="number" name="some-radios" value="2">여자</b-form-radio>
        </b-form-group>

        <b-form-checkbox
          id="checkbox-1"
          v-model="isTerm"
          name="checkbox-1"
          value="accepted"
          unchecked-value="not_accepted"
        >
          I accept the terms and use
        </b-form-checkbox>

        <b-button type="submit" variant="primary">회원가입</b-button>
      </b-form>
    </div>
  </b-container>
</template>

<script>
import Axios from "axios"
export default {
  data() {
    return {
      email: "",
      password: "",
      passwordConfirm: "",
      nickname: "",
      isTerm: false,
      passwordType: "password",
      passwordConfirmType: "password",
      code: "",
      age: 0,
      sex: 1,
    };
  },
  methods: {
    signup() {
      const code = this.code
      const age = this.age
      const nickname = this.nickname
      const sex = this.sex
      const email = this.email;
      const password = this.password;
      const passwordConfirm = this.passwordConfirm;
      const isTerm = this.isTerm;
      if (password != passwordConfirm) {
        alert("비밀번호 확인과 비밀번호가 다릅니다!");
        return false;
      } else if (!isTerm) {
        alert("동의를 해주세요!");
        return false;
      } else if (email.indexOf("@") === -1) {
        alert("이메일 형식을 사용해야합니다!");
        return false;
      }
      this.$store.dispatch("signup", { code, age, email, nickname, password, sex });
    },
    checkEmail() {
      var params = new URLSearchParams()
      const email = this.email
      params.append('email',email)
      if (email.indexOf("@") === -1) {
        alert("이메일 형식을 사용해야 합니다!")
        return false
      }
      Axios.post("http://localhost:3000/verify",params)
      .then(res => {
        console.log(res)
        const EmailWindow = document.querySelector('#email-window')
        EmailWindow.className = 'd-none'
        const SignUpWindow = document.querySelector('#signup-window')
        SignUpWindow.className = 'mx-auto middle'
      })
      .catch(err => {console.log(err)})
    }
  }
};
</script>

<style>
</style>
