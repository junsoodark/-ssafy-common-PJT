import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
//accounts
import Login from '../views/accounts/Login.vue'
import Signup from '../views/accounts/Signup.vue'
import Logout from '../views/accounts/Logout.vue'
import Mypage from '../views/accounts/Mypage.vue'
import UpdateUserInfo from '../views/accounts/UpdateUserInfo.vue'
import EditPassword from '../views/accounts/EditPassword.vue'
//team
import TeamList from '../views/team/TeamList.vue'
import TeamDetail from '../views/team/TeamDetail.vue'
import CreateTeam from "../views/team/CreateTeam.vue"
import UpdateTeam from "../views/team/UpdateTeam.vue"
import StudyArticle from '../views/team/TeamArticleList.vue'
//error
import NotFound from '../views/error/NotFound.vue'
//추가기능
import Search from '../views/Search.vue'

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
    path: '/updateuserinfo',
    name: 'UpdateUserInfo',
    component: UpdateUserInfo,
  },
  {
    path: '/EditPassword',
    name: 'EditPassword',
    component: EditPassword,
  },
  {
    path: '/study/list',
    name: 'StudyList',
    component: TeamList
  },
  {
    path: '/study/detail/:id',
    name: 'StudyDetail',
    component: TeamDetail,
  },
  {
    path: '/study/:id/article',
    name: 'StudyArticle',
    component: StudyArticle
  },
  {
    path: "/study/create",
    name: "CreateTeam",
    component: CreateTeam,
  },
  {
    path: "/study/update/:id",
    name: "UpdateTeam",
    component: UpdateTeam
  },
  {
    path: "/look/:search",
    name: "Search",
    component: Search
  },
  {
    path: "*",
    name: "NotFound",
    component: NotFound,
  }
]

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  const publicPages = ['Login', 'Signup', 'Home', 'About', 'StudyList', 'Mypage',  'UpdateUserInfo','NotFound']  // Login 안해도 됨
  const authPages = ['Login', 'Signup']  // Login 되어있으면 안됨
  const loginRequiredPages = ['CreateTeam']
  
  const authRequired = !publicPages.includes(to.name)  // 로그인 해야 함.
  const unauthRequired = authPages.includes(to.name)  // 로그인 해서는 안됨
  const loginRequired = loginRequiredPages.includes(to.name)
  const isLoggedIn = !!Vue.$cookies.isKey('auth-token')

  if (unauthRequired && isLoggedIn) {
    next("/");
  }
  if (loginRequired && !isLoggedIn) {
    alert('로그인이 필요합니다.')
    next({ name: "Login"})
  }
  authRequired && !isLoggedIn ? next({ name: "Login" }) : next();
});

export default router;
