<template>
  <div>
    <h1>로그인창입니다</h1>
    <form @submit.prevent="Login">
      <input v-model="email" type="email">
			<input v-model="password" type="password">
			<button type="submit">제출</button>
    </form>
  </div>
</template>

<script>
export default {
	data () {
		return {
			email: null,
			password: null,
		}
	},
	methods: {
		Login () {
			const email = this.email
			const password = this.password
			if (!email||!password) {
				alert('빈칸을 채워주세요')
				return false
			}
			console.log(email,password)
			this.$store.dispatch('Login',{email,password})
			this.$cookies.set('auth-token','asdf')
			this.$emit('try-login')
			this.$router.push({name:'Home'})
		}
	}
}
</script>

<style>

</style>