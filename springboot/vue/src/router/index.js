import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Main',
    component:() => import('../views/Main'),
    redirect:"/home",
    children:[
      {path:'home', name:'Home', component:() => import('../views/Home.vue')},
      {path:'userInfo', name:'UserInfo', component:() => import('../views/UserInfo.vue')},
      {path:'user', name:'User', component:() => import('../views/User.vue')},
      {path:'add', name:'Add', component:() => import('../views/Add.vue')},
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
