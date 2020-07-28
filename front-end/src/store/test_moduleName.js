import Axios from 'axios'

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
  deleteUserInfo(state) {
    state.email = ''
    state.age = ''
    state.name = ''
    state.sex = ''
  }
};

const actions = {
  update_email({commit}, $data) {
    const email = $data
    Axios.get(`http://localhost:3000/user/${email}`)
    .then(res => {
      console.log('rrrr', res)
      commit('UPDATE_EMAIL', res)
    })
    .catch( err => {console.log(err)} )
  },
};

export default {
  state: {
    ...initialState
  },
  mutations,
  actions,
}