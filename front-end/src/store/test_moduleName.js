import Axios from "axios";
import router from "@/router";
const API_URL = process.env.VUE_APP_LOCAL_URI

const initialState = {
  email: '',
  name: "",
  age: 0,
  sex: 1,
};

const mutations = {
  UPDATE_EMAIL(state, value) {
    state.email = value.data.email
    state.age = value.data.age
    state.name = value.data.name
    state.sex = value.data.sex
  },
  deleteUserInfo(state , value) {
    state.email = value
    state.age = value
    state.name = value
    state.sex = value
  },
  EDIT_USER_INFO(state, value) {
    state.email = value.email
    state.age = value.age
    state.name = value.name
    state.sex = value.sex
    console.log('vvvvalue', value)
    router.push({ name: 'Mypage' })
  },
};

const actions = {
  update_email({commit}, $data) {
    const email = $data
    Axios.get(`${API_URL}user/${email}`)
    .then(res => {
      commit('UPDATE_EMAIL', res)
    })
    .catch( err => {console.log(err)} )
  },

  update_user_info(params) {
    console.log(params)
      
      Axios.put(`${API_URL}user`, params)
      .then((res) => {
        alert(res.data)
        console.log(res)
      })
      .catch((err) => {
        alert("비밀번호를 확인해주세요")
        console.log(err)
      })
  }
};

export default {
  state: {
    ...initialState
  },
  mutations,
  actions,
}