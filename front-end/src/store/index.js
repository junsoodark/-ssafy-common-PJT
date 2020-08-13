import Vue from "vue";
import Vuex from "vuex";
import Axios from "axios";
import router from "@/router";
import VueCookies from "vue-cookies";

import createPersistedState from "vuex-persistedstate";
import moduleName from "./test_moduleName";
import firebase from 'firebase'

Vue.use(Vuex);

const modules = {
  moduleName,
};
const plugins = [
  createPersistedState({
    paths: ["moduleName"],
  }),
];
const API_URL = process.env.VUE_APP_LOCAL_URL;
export default new Vuex.Store({
  state: {
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
    SET_TOKEN(state, token) {
      state.authToken = token;
      VueCookies.set("auth-token", token);
    },
  },
  actions: {
    login({ commit }, loginData) {
      const params = {
        'email' : loginData.email,
        'password' : loginData.password
      }
      var JsonForm = JSON.stringify(params)
      Axios({method:'POST',url:`${API_URL}login`,params:params,data:JsonForm,headers:{'Content-Type': 'application/json; charset=utf-8'}})
      .then(res => {
        commit('SET_TOKEN', res.data)
        commit('UPDATE_EMAIL', loginData.email)

        sessionStorage.setItem('jwt-auth-token', res.data);
        sessionStorage.setItem('user-email', loginData.email);
        let date = new Date()
        const loginTime = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds()
        commit('UPDATE_LOGIN_TIME', loginTime)

        // firebase 사용자 로그인
        firebase.auth().signInWithEmailAndPassword(loginData.email, loginData.password).catch(function(error) {
          // Handle Errors here.
          var errorCode = error.code;
          var errorMessage = error.message;
          console.log('파이어베이스 로그인 에러')
          console.log(errorCode)
          console.log(errorMessage)
          // ...
        })
        
        
        alert("로그인")

        router.push({ name: 'Home' })
      })
      .catch(err => {
        console.log(err)
        alert(err.response.data)
      })
    },
    signup(state, { code, age, email, nickname, password, sex }) {
      var params = new URLSearchParams();
      params.append("code", code);
      var form = {
        age: age,
        email: email,
        name: nickname,
        password: password,
        sex: sex,
      };
      var JsonForm = JSON.stringify(form);
      Axios({
        method: "POST",
        url: `${API_URL}user/signUp`,
        params: params,
        data: JsonForm,
        headers: { "Content-Type": "application/json; charset=utf-8" },
      })
        .then((res) => {
          alert(res.data);
          router.push({ name: "Login" });
        })
        .catch((err) => {
          alert(err.response.data);
          console.log(err);
        });
    },

    logout({ commit }) {
      commit("SET_TOKEN", null); // state 에서도 삭제
      VueCookies.remove("auth-token"); // cookie 에서는 삭제
      
      localStorage.clear();
      sessionStorage.clear();
      
      // 파이어베이스 로그아웃
      firebase.auth().signOut().then(function() {
        // Sign-out successful.
      }).catch(function(error) {
        // An error happened.
        console.log(error)
      });

      alert("로그아웃");
      router.push({ name: "Home" });
    },
    createTeam(state, form) {
      console.log("fffff", form);
      Axios.post(`${API_URL}study/create`, form)
        .then((res) => {
          alert("스터디 생성");
          console.log(res);
        })
        .catch((err) => {
          alert(err.response.data);
        });
    },
  },
  modules,
  plugins,
  namespaced: true,
});
