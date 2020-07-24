<template>
  <b-container style="max-width:450px;">
        <h1>로그인</h1>
		<br>
    <b-form @submit.prevent="login(loginData)">
      <b-row>이메일 아이디</b-row>
      <b-row>
        <b-form-input
          id="input-1"
          v-model="loginData.email"
          type="email"
          required
          placeholder="이메일 아이디를 입력하세요"
        ></b-form-input>
      </b-row>
      <br>
      <b-row>비밀번호</b-row>
      <b-row>
        <b-form-input
          id="input-2"
          v-model="loginData.password"
          required
          placeholder="비밀번호를 입력하세요"
        ></b-form-input>
      </b-row>
      <b-row>
        <b-col class="text-right py-1 pr-1" >
          <router-link :to="{ name: Signup }">아이디/비밀번호 찾기</router-link>
        </b-col>
      </b-row>
      <br>
      <b-row>
        <b-button type="submit" block variant="info" size="lg">로그인</b-button>
      </b-row>
    </b-form>
  </b-container>
</template>

<script>
import { mapActions } from 'vuex'

export default {
	data () {
		return {
      loginData: {
        username: null,
        password: null
			},
		}
	},
	methods: {
		...mapActions(['login']),
	}
}
</script>

<style>
</style>