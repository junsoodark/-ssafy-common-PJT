import Vue from "vue";
import Vuex from "vuex";
import Axios from "axios";
// import Axios from 'axios'
// import VueCookies from 'vue-cookies'
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    authToken: "asdf",
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
    Login(state, { email, password }) {
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
      alert(email, password);
    },
    signup(state, { email, password }) {
      Axios.post("http://localhost:3000/account/signup", { email, password })
        .then((res) => {
          if (res.status == 200) {
            alert("가입성공");
            console.log(res);
          }
        })
        .catch((err) => {
          alert("가입실패!");
          console.log(err);
        });
    },
  },
  modules: {},
  getters: {
    IsLoggedIn: (state) => !!state.authToken,
  },
});
