import Vue from "vue";
import Vuex from "vuex";
import Axios from 'axios'
import router from "@/router";
import VueCookies from "vue-cookies";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    email: null,
    password: null,
    passwordConfirm: null,
    authToken: VueCookies.get("auth-token"),
  },
  getters: {
    // auth
    isLoggedIn: (state) => !!state.authToken,
    // auth, articles
    config: (state) => ({
      headers: { Authorization: `Token ${state.authToken}` },
    }),
  },
  mutations: {
    // auth
    SET_TOKEN(state, token) {
      console.log(token);
      state.authToken = token;
      VueCookies.set("auth-token", token);
    },

    Logout(state) {
      state.authToken = null;
    },
  },
  actions: {
    postAuthData({ commit }, loginData) {
      // Axios.post('http://localhost:3000/accounts/login', loginData)
      //   .then(res => {
      //     commit('SET_TOKEN', res.data.key)
      //     router.push({ name: 'Home' })
      //   })
      //   .catch(err => console.log(err.response.data))

      commit("SET_TOKEN", "qwer" + loginData);
      router.push({ name: "Home" });
    },

    login({ dispatch }, loginData) {
      dispatch("postAuthData", loginData);
    },

    signup (state, {code, age, email, nickname, password, sex}) {
      var params = new URLSearchParams()
      params.append('code',code)
      var form = new FormData()
      form.append('age',age)
      form.append('email',email)
      form.append('name',nickname)
      form.append('password',password)
      form.append('sex',sex)
      Axios.post('http://localhost:3000/user',params,form)
      .then(res => {
        alert("회원가입 성공!")
        console.log(res)
        router.push({ name: 'Login' })
      })
      .catch((err) => {
        alert("가입실패!");
        console.log(err);
      });
    },

    logout({ commit }) {
      // Axios.post('http://localhost:3000/accounts/logout', null, getters.config)
      //   .then(() => {  // Django DB 에서는 삭제 | cookie, state 에는 남아있음
      //     commit('SET_TOKEN', null)  // state 에서도 삭제
      //     cookies.remove('auth-token')  // cookie 에서는 삭제
      //     router.push({ name: 'Home' })
      //   })
      //   .catch(err => console.log(err.response.data))
      commit("SET_TOKEN", null); // state 에서도 삭제
      VueCookies.remove("auth-token"); // cookie 에서는 삭제
      router.push({ name: "Home" });
    },
    createTeam() {},
  },
  modules: {},
});
