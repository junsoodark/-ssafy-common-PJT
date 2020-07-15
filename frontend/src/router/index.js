import Vue from 'vue'
import Router from 'vue-router'

import constants from '../lib/constants'

// 유저
import Login from '../page/user/Login.vue'
import Join from '../page/user/Join.vue'

// 포스트
import List from '../page/post/List.vue'

// error페이지들
import NotFound from '../page/error/NotFound.vue'
import ErrorPage from '../page/error/Error.vue'
Vue.use(Router) 
 
export default new Router({
  routes: [   
    // 로그인/가입
    { 
      path: '/user/login',
      name: constants.URL_TYPE.USER.LOGIN,
      component: Login
    },
    {
      path: '/user/join',
      name: constants.URL_TYPE.USER.JOIN,
      component: Join
    },
    // 포스트
    { 
      path: '/',
      name: constants.URL_TYPE.POST.MAIN,
      component: List,
    },
    // 에러
    {
      path: '/err',
      name: constants.ERROR.FRONT_ERROR,
      component: ErrorPage
    },
    {
      path: '*',
      name: constants.ERROR.NOT_FOUND,
      component: NotFound,
    }
  ]
})
