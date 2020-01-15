import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import mainPages from '@/components/mainPages'
import _error from '@/components/error'

Vue.use(Router)

export const constantRouterMap = [
  {path: '/', name: 'Login', component: Login},
  {path:'/login', redirect:'/login'},
  {path: '/mainPages', name: 'mainPages', component: mainPages},
  {path: '/error', name: 'error', component: _error},
]

export default new Router({
  routes: constantRouterMap //指定路由列表
})
