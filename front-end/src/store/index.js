import Vue from "vue";
import Vuex from "vuex";
import Axios from "axios";
import router from "@/router";
import VueCookies from "vue-cookies";

import createPersistedState from "vuex-persistedstate";
import moduleName from "./test_moduleName";

Vue.use(Vuex);

const modules = {
  moduleName,
};
const plugins = [
  createPersistedState({
    paths: ["moduleName"],
  }),
];
const API_URL = process.env.VUE_APP_LOCAL_URI
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
      console.log(token);
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
        alert("로그인 성공")
        router.push({ name: 'Home' })
      })
      .catch(err => {
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
        url: `${API_URL}user`,
        params: params,
        data: JsonForm,
        headers: { "Content-Type": "application/json; charset=utf-8" },
      })
        .then((res) => {
          alert("회원가입 성공!");
          console.log(res);
          router.push({ name: "Login" });
        })
        .catch((err) => {
          alert("가입실패!");
          console.log(err);
        });
    },

    logout({ commit }) {
      alert('로그아웃')
      commit("SET_TOKEN", null); // state 에서도 삭제
      VueCookies.remove("auth-token"); // cookie 에서는 삭제
      commit("deleteUserInfo", null)
      router.push({ name: "Home" });
    },
    createTeam(state, form) {
      Axios.post(`${API_URL}study/create`, form)
        .then((res) => {
          alert("스터디 생성");
          console.log(res);
        })
        .catch((err) => {
          alert("생성 실패!");
          console.log(err);
        });
    },
  },
  modules,
  plugins,
  namespaced: true,
});
