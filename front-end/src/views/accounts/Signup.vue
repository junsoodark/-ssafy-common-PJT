<template>
  <b-container class="bv-example-row my-5">
    <div style="max-width:450px;" class="mx-auto middle">
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
            v-model="passwordConfirmType"
            type="password"
            required
            placeholder="위에 입력한 비밀번호와 똑같이 입력해주세요"
          ></b-form-input>
        </b-form-group>

        <b-button type="submit" variant="primary">회원가입</b-button>
      </b-form>
    </div>
  </b-container>
</template>

<script>
export default {
  data() {
    return {
      email: "",
      password: "",
      passwordConfirm: "",
      nickname: "",
      isTerm: false,
      passwordType: "password",
      passwordConfirmType: "password"
    };
  },
  methods: {
    signup() {
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
      this.$store.dispatch("signup", { email, password });
    }
  }
};
</script>

<style>
</style>
