import Vue from "vue";
import Vuex from "vuex";
import Axios from "axios";
import router from "@/router";
import VueCookies from "vue-cookies";
import MD5 from 'md5'

import createPersistedState from 'vuex-persistedstate'
import moduleName from './test_moduleName'

Vue.use(Vuex);

const modules = {
  moduleName
};
const plugins = [
  createPersistedState({
    paths: [
      'moduleName',
    ]
  })
];

export default new Vuex.Store({
  state: {
    email: VueCookies.get("auth-user"),
    password: VueCookies.get("auth-user-what"),
    authToken: VueCookies.get("auth-token"),
    userInfo: {
      email: "",
      password: "",
      passwordConfirm: "",
      nickname: "",
      isTerm: false,
      passwordType: "password",
      passwordConfirmType: "password",
      code: "",
      age: 0,
      sex: 1,
    }
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
      console.log(state)
      VueCookies.remove("auth-token");
      VueCookies.remove("auth-user")
      VueCookies.remove("auth-user-what")
      this.state.email = null
      this.state.password = null
      this.state.authToken = null
    },
  },
  actions: {
    postAuthData({ commit }, loginData) {
      const params = {
        'email' : loginData.email,
        'password' : loginData.password
      }
      var JsonForm = JSON.stringify(params)
      Axios({method:'POST',url:'http://localhost:3000/login',params:params,data:JsonForm,headers:{'Content-Type': 'application/json; charset=utf-8'}})
        .then(res => {
          console.log(res)
          commit('SET_TOKEN', res.data)
          alert("로그인 성공")
          router.push({ name: 'Home' })
        })
        .catch(err => console.log(err.response.data))

      // commit("SET_TOKEN", "qwer" + loginData);
      // router.push({ name: "Home" });
      // this.state.email = MD5(loginData.email)
      // this.state.password = MD5(loginData.password)
      // VueCookies.set("auth-user",MD5(loginData.email))
      // VueCookies.set("auth-user-what",MD5(loginData.password))
    },

    login({ dispatch }, loginData) {
      dispatch("postAuthData", loginData);
    },
    signup (state, {code, age, email, nickname, password, sex}) {
      var params = new URLSearchParams()
      params.append('code',code)
      var form = {
        'age': age,
        'email': email,
        'name': nickname,
        'password': password,
        'sex': sex
      }
      var JsonForm = JSON.stringify(form)
      Axios({method:'POST',url:'http://localhost:3000/user',params:params,data:JsonForm,headers:{'Content-Type': 'application/json; charset=utf-8'}})
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
      VueCookies.remove("auth-user")
      VueCookies.remove("auth-user-what")
      this.state.email = null
      this.state.password = null
      this.state.authToken = null
      router.push({ name: "Home" });
    },
    createTeam(state, form) {
      Axios.post("http://localhost:3000/study/create", form)
        .then((res) => {
          alert("스터디 생성");
          console.log(res);
        })
        .catch((err) => {
          alert("생성 실패!");
          console.log(err);
        });
    },
    authDelete({ state }, {email,password}) {
      if (state.email != MD5(email) || state.password != MD5(password)) {
        alert('삭제 실패! 이메일과 패스워드를 확인해주세요!')
        return false
      }
      var params = new URLSearchParams()
      params.append('email',email)
      params.append('password',password)
      Axios({method:'DELETE',url:'http://localhost:3000/user',params:params,headers:{'Content-Type': 'application/json; charset=utf-8'}})
      .then(res => {
        alert(res.data)
        VueCookies.remove("auth-token");
        VueCookies.remove("auth-user")
        VueCookies.remove("auth-user-what")
        this.state.email = null
        this.state.password = null
        this.state.authToken = null
        router.push({ name: "Home" })
      })
      .catch(err => {
        console.log(err)
        alert('삭제 실패!')
      })
    },
  },
  modules,
  plugins,
  namespaced: true
});
