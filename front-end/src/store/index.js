import Vue from 'vue'
import Vuex from 'vuex'
//import Axios from 'axios'
import VueCookies from 'vue-cookies'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    authToken:  VueCookies.get('auth-token')
  },
  mutations: {
    Login (state,{cooky}) {
      state.authToken = cooky
    },
    Logout (state) {
      state.authToken = null
    }
  },
  actions: {
    Login (state,{email,password}) {
      // Axios.get('http://localhost:3000/accounts/login',{
      //   params: {
      //     email: email,
      //     password: password
      //   }
      //   .then(res => {
      //     this.commit('Login',res.data.object)
      //     this.$cookies.set('auth-token',res.data.object)
      //     this.IsLoggedIn = true
      //   })
      //   .catch(err => {console.log(err)})
      // })
      console.log(email,password)
    },
    Logout () {
      this.commit('Logout')
      this.$cookies.remove('auth-token')
    }
  },
  modules: {
  },
  getters: {
  }
})
