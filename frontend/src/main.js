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

// bootstrap
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
Vue.use(BootstrapVue)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router, 
  components: { App }, 
  template: '<App/>'
})
 