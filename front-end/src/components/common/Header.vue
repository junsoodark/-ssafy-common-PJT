<template>
  <div>
    <b-navbar toggleable="lg" style="background-color: rgb(24,44,141)" type="dark">
      <router-link to="/" class="ml-2 mr-5 text-decoration-none text-light"><img src="../../assets/logo.png" style="height:30px">Home</router-link>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <b-collapse id="nav-collapse" is-nav>
        <router-link class="text-decoration-none text-light mx-1" :to="{ name: 'StudyList' }">스터디 팀들</router-link>
        <router-link class="text-decoration-none text-light mx-1" :to="{ name: 'CreateTeam'}">스터디만들기</router-link>
        <router-link class="text-decoration-none text-light mx-1" :to="{ name: 'Chat'}">모두와 대화</router-link>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-form @submit.prevent="search">
            <b-form-input size="sm" class="mr-sm-2" placeholder="Search" v-model="searchData"></b-form-input>
            <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
          </b-nav-form>

            <!-- Using 'button-content' slot -->
          <div v-if="isLoggedIn" class="ml-3">
            <router-link class="text-light mx-1" :to="{ name: 'Logout' }">Logout</router-link>
            <router-link class="text-light ml-2" :to="{ name: 'Mypage' }">마이페이지</router-link>
          </div>
          <div v-else class="ml-3">
            <router-link class="text-light mx-1" to="/login">Login</router-link>
            <router-link class="text-light ml-2" to="/signup">Signup</router-link>
          </div>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  data () {
    return {
      searchData: null
    }
  },
  computed: {
    ...mapGetters(['isLoggedIn'])
  },
  methods: {
    ...mapMutations(['deleteUserInfo']),
    ...mapActions(['delete_email']),
    search() {
      this.$router.push({ name: 'Search', params: {search:this.searchData} })
    }
  }
}
</script>

<style>

</style>