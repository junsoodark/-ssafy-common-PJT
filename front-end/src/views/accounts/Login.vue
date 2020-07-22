<template>
  <div style="max-width:450px;">
    <h1>로그인창입니다</h1>
    <!-- <form @submit.prevent="onLoginSubmit">
      <input v-model="email" type="email" />
      <input v-model="password" type="password" />
      <button type="submit">제출</button>
    </form> -->

		<!-- 수정 -->
		
    <b-form @submit.prevent="login(loginData)">
      <b-form-group
        id="input-group-1"
        label="Email address:"
        label-for="input-1"
        description="We'll never share your email with anyone else."
      >
        <b-form-input
          id="input-1"
          v-model="loginData.email"
          type="email"
          required
          placeholder="Enter email"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Your password:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="loginData.password"
          required
          placeholder="Enter password"
        ></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary">Submit</b-button>
    </b-form>
  </div>
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
		// onLoginSubmit () {
		// 	const email = this.form.email
		// 	const password = this.form.password

		// 	if (!email || !password) {
		// 		alert('빈칸을 채워주세요')
		// 		return false
		// 	}

		// 	console.log('m', email, password)
		// 	this.$cookies.set('auth-token','qwer')
		// 	this.$store.dispatch('Login', { email, password })
		// 	// this.$emit('try-login')
		// 	this.$router.push({name:'Home'})
		// },
	}
}
</script>

<style>
</style>