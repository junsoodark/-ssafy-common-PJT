import Axios from "axios";
import router from "@/router";


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
  // deleteUserInfo(state) {
  //   console.log('로그아웃')
  //   state.email = ''
  //   state.age = ''
  //   state.name = ''
  //   state.sex = ''
  // }
  EDIT_USER_INFO(state, value) {
    state.email = value.email
    state.age = value.age
    state.name = value.name
    state.sex = value.sex
    console.log('vvvvalue', value)
    router.push({ name: 'Mypage' })
  }
};

const actions = {
  update_email({commit}, $data) {
    const email = $data
    Axios.get(`http://localhost:3000/user/${email}`)
    .then(res => {
      commit('UPDATE_EMAIL', res)
    })
    .catch( err => {console.log(err)} )
  },
  // delete_email({commit}) {
  //   alert('delete email')
  //   commit('deleteUserInfo')
  // },

  update_user_info(params) {
    console.log(params)
      
      Axios.put("http://localhost:3000/user", params)
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