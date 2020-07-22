<template>
  <div id="app">
    <Header :IsLoggedIn="IsLoggedIn" @try-logout="Logout"/>
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
    },
    Logout () {
      this.IsLoggedIn = false
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
