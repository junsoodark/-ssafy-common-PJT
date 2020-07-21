<template>
  <div>
    <h1>signup</h1>
    <form @submit.prevent="Signup">
      <input type="email"><br>
      <input type="password"><br>
      <input type="password"><br>
      <button type="submit">제출</button>
    </form>
  </div>
    
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'Signup',
  methods: {
    ...mapActions([
      'Signup'
    ])
  },
  data() {
    return {
			email: null,
			password: null,
    }
  }
}
</script>

<style>

</style>