<template>
  <div id="app">
    <Header :isHeader="isHeader" :isLoggedIn="isLoggedIn" @try-logout="Logout" @submit-login="login"/>
    <router-view @submit-login="login"/>
  </div>
</template>

<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>  
<script> 
import './assets/css/style.scss' 
import Header from './components/common/Header.vue'
import constants from './lib/constants' 

export default {
  name: 'App',
  components: { 
    Header
  },
  created() {
    this.isLoggedIn = this.$cookies.isKey('auth-token')
    
    let url = this.$route.name;

    this.checkUrl(url);
  },
  watch: {
      $route (to){

          this.checkUrl(to.name);
      }
  },
  methods : {
      checkUrl(url) { 

          let array = [
              constants.URL_TYPE.USER.LOGIN,
              constants.URL_TYPE.USER.JOIN,
          ];

          let isHeader = true;
          array.map(path => {
              if (url === path)
                  isHeader = false;
          })
          this.isHeader = isHeader;

      },
      login(cooky){
        this.$cookies.set('auth-token',cooky)
        this.isLoggedIn = true
      },
      Logout(){
        this.$cookies.remove('auth-token')
        this.isLoggedIn = false
        this.$router.push({name:'main'})
      }
  },
  data: function () {
        return {
            isLoggedIn: false,
            isHeader: true,
            constants
        } 
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
  margin-top: 60px;
}
</style>
