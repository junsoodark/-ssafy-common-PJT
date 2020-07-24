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
      this.state.email = loginData.email
      this.state.password = loginData.password
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
      this.state.email = null
      this.state.password = null
      router.push({ name: "Home" });
    },

    authDelete({ state }, {email,password}) {
      if (state.email != email || state.password != password) {
        alert('삭제 실패! 이메일과 패스워드를 확인해주세요!')
        return false
      }
      var params = new URLSearchParams()
      params.append('email',email)
      params.append('password',password)
      Axios.delete('http://localhost:3000/user',params)
      .then(res => {
        console.log(res)
        alert('삭제 완료!')
        router.push({ name: "Home" })
      })
      .catch(err => {
        console.log(err)
        alert('삭제 실패!')
        router.push({ name: "Home" })
      })
    },
    createTeam() {},
  },
  modules: {},
});
