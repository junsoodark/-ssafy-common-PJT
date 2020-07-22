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

    Logout (state) {
      state.authToken = null
    }
  },
  actions: {
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
    logout({ commit }) {
      // Axios.post('http://localhost:3000/accounts/logout', null, getters.config)
      //   .then(() => {  // Django DB 에서는 삭제 | cookie, state 에는 남아있음
      //     commit('SET_TOKEN', null)  // state 에서도 삭제
      //     cookies.remove('auth-token')  // cookie 에서는 삭제
      //     router.push({ name: 'Home' })
      //   })
      //   .catch(err => console.log(err.response.data))
      commit('SET_TOKEN', null)  // state 에서도 삭제
      VueCookies.remove('auth-token')  // cookie 에서는 삭제
      router.push({ name: 'Home' })
    },

    Logout () {
      this.commit('Logout')
      this.$cookies.remove('auth-token')
    }
  },
  modules: {
  },
})
