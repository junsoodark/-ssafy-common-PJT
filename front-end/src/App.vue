<template>
  <div id="app" @click="timeCheck" @keyup="timeCheck">
    <Header />
    <router-view :key="$route.fullPath"/>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import Header from "./components/common/Header.vue";

export default {
  name: "App",
  computed: {
    ...mapGetters(["isLoggedIn"]),
    ...mapState({
      loginTime: state => state.moduleName.loginTime,
    }),
  },

  components: {
    Header,
  },
  methods: {
    timeCheck() {
      if (this.isLoggedIn) {
        var date = new Date()
        var H = date.getHours() * 1
        var M = date.getMinutes() * 1
        var S = date.getSeconds() * 1
        console.log('로그인',this.isLoggedIn)
        console.log('현재시간', H,M,S)

        if (H === 0 && M < 16) {
          H = 24
        }

        if (H < 10) {
            H = '0' + H
            H *= 1
        }
        if (M < 10) {
          M = '0' + M
          M *= 1
        }
        if (S < 10) {
          S = '0' + S
          S *= 1
        }
        console.log('현재시간 보정', H,M,S)
        console.log('로그인 시간', this.loginTime)
        var limitHour = (this.loginTime[0] + this.loginTime[1]) * 1
        var limitMinute = (this.loginTime[3] + this.loginTime[4]) * 1
        var limitSecond = (this.loginTime[6] + this.loginTime[7]) * 1

        // 토큰 유지 시간 조정
        limitMinute += 15

        if (limitMinute >= 60 ) {
          limitMinute -= 60
          limitHour += 1
        }

        console.log('리미트 시간', limitHour, limitMinute, limitSecond)
        const limit = limitHour * 3600 + limitMinute * 60 + limitSecond
        const now = H * 3600 + M * 60 + S
        // console.log(now, limit)
        if (now >= limit) {
          alert('로그인 기간이 만료되었습니다.')
          this.$router.push({ name: "Logout" })
        }

        // console.log(this.loginTime)
      }
    }
  },
};
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
