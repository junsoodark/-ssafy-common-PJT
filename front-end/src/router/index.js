import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
//accounts
import Login from '../views/accounts/Login.vue'
import Signup from '../views/accounts/Signup.vue'
import Logout from '../views/accounts/Logout.vue'
import Mypage from '../views/accounts/Mypage.vue'
//team
import TeamList from '../views/team/TeamList.vue'
import TeamDetail from '../views/team/TeamDetail.vue'
import CreateTeam from "../views/team/CreateTeam.vue"

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/signup",
    name: "Signup",
    component: Signup,
  },
  {
    path: "/logout",
    name: "Logout",
    component: Logout,
  },
  {
    path: '/mypage',
    name: 'Mypage',
    component: Mypage,
  },
  {
    path: '/study/list',
    name: 'StudyList',
    component: TeamList
  },
  {
    path: '/study/detail',
    name: 'StudyDetail',
    component: TeamDetail
  },
  
  {
    path: "/study/create",
    name: "CreateTeam",
    component: CreateTeam,
  }
]

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

<<<<<<< front-end/src/router/index.js
// router.beforeEach((to, from, next) => {
//   const publicPages = ["Login", "Signup", "Home"]; // Login 안해도 됨
//   const authPages = ["Login", "Signup"]; // Login 되어있으면 안됨
=======
router.beforeEach((to, from, next) => {
  const publicPages = ['Login', 'Signup', 'Home', 'About', 'StudyList', 'StudyDetail', 'Mypage']  // Login 안해도 됨
  const authPages = ['Login', 'Signup']  // Login 되어있으면 안됨
  
  const authRequired = !publicPages.includes(to.name)  // 로그인 해야 함.
  const unauthRequired = authPages.includes(to.name)  // 로그인 해서는 안됨
  const isLoggedIn = !!Vue.$cookies.isKey('auth-token')
>>>>>>> front-end/src/router/index.js

//   const authRequired = !publicPages.includes(to.name); // 로그인 해야 함.
//   const unauthRequired = authPages.includes(to.name); // 로그인 해서는 안됨
//   const isLoggedIn = !!Vue.$cookies.isKey("auth-token");

//   if (unauthRequired && isLoggedIn) {
//     next("/");
//   }
//   authRequired && !isLoggedIn ? next({ name: "Login" }) : next();
// });

export default router;
