import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

// vue cookies
import VueCookies from "vue-cookies";
Vue.config.productionTip = false;
Vue.use(VueCookies);
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

// bootstrap
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
Vue.use(BootstrapVue);
Vue.config.productionTip = false;

//daterangepicker
import VMdDateRangePicker from "v-md-date-range-picker";
Vue.use(VMdDateRangePicker);

//vue-slider
import VueSlider from "vue-slider-component";
import "vue-slider-component/theme/default.css";

Vue.component("VueSlider", VueSlider);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
