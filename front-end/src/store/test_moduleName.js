import Axios from "axios";
import router from "@/router";
const API_URL = process.env.VUE_APP_LOCAL_URL

const initialState = {
  email: '',
  imageName: null,
};

const mutations = {
  UPDATE_EMAIL(state, value) {
    state.email = value
  },
  deleteUserInfo(state , value) {
    state.email = value
  },
  EDIT_USER_INFO(state, value) {
    state.email = value.email
    router.push({ name: 'Mypage' })
  },
};

const actions = {
  update_user_info(params) {
    console.log(params)
      
      Axios.put(`${API_URL}user`, params, {
        headers: {
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
          'user-email': sessionStorage.getItem('user-email')
        }
      })
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