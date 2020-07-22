import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueCookies from 'vue-cookies'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

Vue.config.productionTip = false
Vue.use(VueCookies)
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
