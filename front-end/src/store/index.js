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
        commit('SET_TOKEN', res.data.token)
        commit('UPDATE_EMAIL', loginData.email)

        sessionStorage.setItem('jwt-auth-token', res.data.token);
        sessionStorage.setItem('user-email', loginData.email);

        // 로그인 시간 저장
        let date = new Date()
        var loginH = date.getHours()
        var loginM = date.getMinutes()
        var loginS = date.getSeconds()

        if (loginH < 10) {
          loginH = '0' + loginH
        }
        if (loginM < 10) {
          loginM = '0' + loginM
        }
        if (loginS < 10) {
          loginS = '0' + loginS
        }

        const loginTime = loginH + ":" + loginM + ":" + loginS
        commit('UPDATE_LOGIN_TIME', loginTime)

        var fbpassword = res.data.fbpassword
        // firebase 사용자 로그인
        firebase.auth().signInWithEmailAndPassword(loginData.email, fbpassword)
        .catch(function(error) {
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
        alert(err.response.data)
      })
    },
    signup(state, { code, age, email, nickname, password, sex }) {
      console.log(code, age, email, nickname, password, sex)
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
          console.log(res)
          var fbPassword = res.data.fbPassword
        // // firebase 회원가입
        // firebase.auth().createUserWithEmailAndPassword(email, fbPassword)
        // .then((res) => {
        //   console.log(res)
        //   var file = 'https://previews.123rf.com/images/salamatik/salamatik1801/salamatik180100019/92979836-%ED%94%84%EB%A1%9C%ED%95%84-%EC%9D%B5%EB%AA%85%EC%9D%98-%EC%96%BC%EA%B5%B4-%EC%95%84%EC%9D%B4%EC%BD%98-%ED%9A%8C%EC%83%89-%EC%8B%A4%EB%A3%A8%EC%97%A3-%EC%82%AC%EB%9E%8C%EC%9E%85%EB%8B%88%EB%8B%A4-%EB%82%A8%EC%84%B1-%EA%B8%B0%EB%B3%B8-%EC%95%84%EB%B0%94%ED%83%80-%EC%82%AC%EC%A7%84-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C-%EC%9E%90-%ED%9D%B0%EC%83%89-%EB%B0%B0%EA%B2%BD%EC%97%90-%EA%B3%A0%EB%A6%BD-%EB%B2%A1%ED%84%B0-%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8-%EB%A0%88%EC%9D%B4-%EC%85%98.jpg'
        //   var storageRef = firebase.storage().ref(`images/${email}/${email}`);
        //   var task = storageRef.put(file);
        //   task.on('state_changed',
        //     //progress Bar
        //     function progess(snapshot){
        //       console.log(snapshot)
        //     },
        //     // error
        //     function error(err){
        //       console.log(err)
        //     },
        //     // complete
        //     function (){
        //     }
        //   )
        // })
        .catch(function (error) {
          // Handle Errors here.
          var errorCode = error.code;
          var errorMessage = error.message;
          console.log("firebase 인증 에러", email, fbPassword);
          console.log(errorCode);
          console.log(errorMessage);
          // ...
        });
        alert(res.data.msg);
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
