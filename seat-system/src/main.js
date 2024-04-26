// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from './store'

// 设置反向代理，前端请求默认发送到 http://localhost:8440/api
var axios = require('axios')
//axios.defaults.baseURL = 'http://localhost:8660/api'
axios.defaults.baseURL = 'http://43.138.148.179:8990/api'
//axios.defaults.baseURL = 'https://192.168.1.14:8440/api'
//axios.defaults.baseURL = 'https://192.168.102.110:8440/api'
// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
Vue.prototype.$axios = axios


Vue.config.productionTip = false
Vue.use(ElementUI)

import Mui from 'vue-awesome-mui'
Vue.use(Mui)

//vue.component("menu-view",menu);
/* eslint-disable no-new */
router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    if (store.state.user.username) {
      next()
    } else {
      next({
        path: 'mlogin',
        query: {redirect: to.fullPath}
      })
    }
  } else {
    next()
  }
}
)

new Vue({
  el: '#app',
  render: h => h(App),
  router,
  // 注意这里
  store,
  components: { App },
  template: '<App/>'
})
