<template>
  <b-container class="bv-example-row my-5">
    <div style="max-width:450px;" class="mx-auto">
      <h1>로그인창입니다</h1>
      
      <b-form @submit.prevent="login(loginData)">
        <b-form-group
          id="input-group-1"
          label="이메일:"
          label-for="input-1"
          description="저희는 절대로 당신의 개인정보를 누군가와 공유하지 않습니다"
        >
          <b-form-input
            id="input-1"
            v-model="loginData.email"
            type="email"
            required
            placeholder="이메일을 입력해주세요"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-2" label="비밀번호:" label-for="input-2">
          <b-form-input
            id="input-2"
            v-model="loginData.password"
            required
            placeholder="비밀번호를 입력해주세요"
          ></b-form-input>
        </b-form-group>

        <b-button type="submit" variant="primary">로그인</b-button>
      </b-form>
    </div>
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