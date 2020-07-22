<template>
  <div id="app">
    <Header></Header>
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/login">Login</router-link> |
      <router-link to="/signup">Signup</router-link>
    </div>
    <router-view @try-login="Login"/>
  </div>
</template>

<script>
import Header from './components/common/Header.vue'
export default {
  data () {
    return {
      IsLoggedIn: false
    }
  },
  created () {
    this.IsLoggedIn = this.$cookies.isKey('auth-token')
  },
  methods: {
    Login () {
      this.IsLoggedIn = true
    }
  },
  components: {
    Header
  }
}

</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
