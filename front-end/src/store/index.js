import Vue from 'vue'
import Vuex from 'vuex'
// import Axios from 'axios'
import router from '@/router'
import VueCookies from 'vue-cookies'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    email: null,
    password: null,
    passwordConfirm: null,
    authToken: VueCookies.get('auth-token'),
  },
  getters: {
    // auth
    isLoggedIn: state => !!state.authToken,
    // auth, articles
    config: state => ({ headers: { Authorization: `Token ${state.authToken}` } })
  },
  mutations: {
    // auth
    SET_TOKEN(state, token) {
      console.log(token)
      state.authToken = token
      VueCookies.set('auth-token', token)
    },

    setLogin (state, cooky) {
      console.log('cookie', cooky)
      console.log(state.authToken)
      state.authToken = cooky
      console.log(state.authToken)
    },
    Logout (state) {
      state.authToken = null
    }
  },
  actions: {
    Login({ commit }, loginObj) {
      console.log('e', loginObj)
      console.log(VueCookies.get('auth-token'),)
      commit('setLogin', VueCookies.get('auth-token'),)
      // Axios.get('http://localhost:3000/accounts/login',loginObj)
      //   .then(res => {
      //     commit('setLogin', res.data.object)
      //     this.$cookies.set('auth-token', res.data.object)
      //     this.IsLoggedIn = true
      //   })
      //   .catch(err => {console.log(err)})
      // })
      alert(loginObj)
    },

    postAuthData({ commit }, loginData) {
      // Axios.post('http://localhost:3000/accounts/login', loginData)
      //   .then(res => {
      //     commit('SET_TOKEN', res.data.key)
      //     router.push({ name: 'Home' })
      //   })
      //   .catch(err => console.log(err.response.data))
      
      commit('SET_TOKEN', 'qwer'+loginData)
      router.push({ name: 'Home' })
    },

    login({ dispatch }, loginData) {
      dispatch('postAuthData', loginData)
    },

    Signup (state, event) {
      console.log('email:', event.target[0].value)
      console.log('password:', event.target[1].value)
      
      if (event.target[1].value != event.target[0].value) {
        alert('비밀번호와 비밀번호 확인이 다릅니다.')
        return false
      }
      
      // console.log(input)
      
      // Axios.post('http://localhost:3000/accounts/signup', {
      //   params: {
      //     email: email,
      //     password: password,
      //     // 등등
      //   }
      //   .then(res => {
      //     this.commit('Signup', res.data.object)
      //   })
      //   .cathc(err => {
      //     alert('로그인 실패')
      //     console.error(err)
      //   })
      // })
    },
    Logout () {
      this.commit('Logout')
      this.$cookies.remove('auth-token')
    }
  },
  modules: {
  },
})
