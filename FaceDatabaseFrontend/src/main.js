import Vue from 'vue'
import App from './App.vue'
import './plugins/element.js'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueWechatTitle from 'vue-wechat-title'
 
Vue.use(VueWechatTitle)

Vue.use(VueAxios, axios)
Vue.config.productionTip = false
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()
})
