import Vue from 'vue'
import Vuex from 'vuex'
// import Axios from 'axios'
// import VueCookies from 'vue-cookies'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    authToken: 'asdf',
    email: null,
    password: null,
    passwordConfirm: null,
  },
  mutations: {
    // Login (state,{cooky}) {
    //   state.authToken = cooky
    // },
    // Logout (state) {
    //   state.authToken = null
    // }
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
      alert(email,password)
      console.log(email, password)
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

    }
  },
  modules: {
  },
  getters: {
    IsLoggedIn: state => !!state.authToken
  }
})
