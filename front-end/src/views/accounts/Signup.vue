<template>
  <div>
    <h1>회원가입 페이지</h1>
    <form @submit.prevent="signup">
      <input v-model="email" type="email" />
      <input v-model="password" type="passwordType" />
      <input v-model="passwordConfirm" type="passwordConfirmType" />
      <input v-model="isTerm" type="checkbox" />
      <button type="submit">제출</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: "",
      password: "",
      passwordConfirm: "",
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
      this.$store.dispatch("signup", { email, password, passwordConfirm });
    }
  }
};
</script>

<style>
</style>