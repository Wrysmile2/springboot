import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import axios from "axios";
import 'element-ui/lib/theme-chalk/index.css';
import './assets/gloable.css'

Vue.config.productionTip = false

Vue.use(ElementUI,{size:"mini"});
Vue.prototype.axios = axios;

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
