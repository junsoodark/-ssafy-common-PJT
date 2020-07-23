<template>
  <form @submit.prevent="signup" class="middle">
    <h1>회원가입</h1>
    <div class="form-wrap">
      <div class="input-wrap">
        <input v-model="email" id="email" placeholder="이메일을 입력해주세요" type="email" />
      </div>
      <div class="input-wrap password-wrap">
        <input v-model="password" id="password" :type="passwordType" placeholder="비밀번호를 입력해주세요" />
        <span :class="{active : passwordType==='text'}">
          <i class="fas fa-eye"></i>
        </span>
      </div>
      <div class="input-wrap password-wrap">
        <input
          v-model="passwordConfirm"
          id="password-confirm"
          :type="passwordConfirmType"
          placeholder="비밀번호를 한번 더 입력해주세요"
        />
        <span :class="{active : passwordConfirmType==='text'}">
          <i class="fas fa-eye"></i>
        </span>
      </div>
      <div class="input-wrap">
        <input v-model="nickname" type="text" id="nickname" placeholder="닉네임을 입력해주세요" />
      </div>
    </div>
    <label>
      <input v-model="isTerm" type="checkbox" id="term" />
      <span>약관에 동의 합니다</span>
    </label>
    <button class="btn" type="submit">
      <span>가입하기</span>
    </button>
  </form>
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
      this.$store.dispatch("signup", { email, password, passwordConfirm });
    }
  }
};
</script>

<style>
</style>
