import Vue from 'vue'
import Router from 'vue-router'
import home from '@/views/home'
import manage from '@/views/manage'
import monitor from '@/views/monitor'
import register from '@/views/register'
import mobile from '@/views/mobile'
import mlogin from '@/views/mlogin'
import usedata from '@/views/usedata'
import useqrcode from '@/views/useqrcode'
import message from '@/views/message'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    
    {
      path: '/home',
      name: 'home',
      component: home
    },
    {
      path: '/manage',
      name: 'manage',
      component: manage
    },
    {
      path: '/monitor',
      name: 'monitor',
      component: monitor
    },
    {
      path: '/register',
      name: 'register',
      component: register
    },
    {
      path: '/',
      name: 'mobile',
      component: mobile,
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/mlogin',
      name: 'mlogin',
      component: mlogin
    },
    {
      path: '/usedata',
      name: 'usedata',
      component: usedata
    },
    {
      path: '/useqrcode',
      name: 'useqrcode',
      component: useqrcode
    },
    {
      path: '/message',
      name: 'message',
      component: message
    },
    
  ]
})
