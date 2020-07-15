import Vue from 'vue'
import App from './App.vue'
import router from './router'

// Vue cookies
import VueCookies from 'vue-cookies'
Vue.use(VueCookies)
Vue.config.productionTip = false
// axios
import axios from 'axios'
Vue.prototype.$axios = axios

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router, 
  components: { App }, 
  template: '<App/>'
})
 