import Vue from 'vue'
import Electron from 'vue-electron'
import 'normalize.css/normalize.css'// A modern alternative to CSS resets

import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import '@/styles/index.scss' // global.sass css

import App from './App'
import router from './router'
import store from './store'
import nprogress from 'nprogress'

import 'font-awesome/css/font-awesome.css';
import i18n from './assets/lang'
import './assets/icons'
import './errorLog'
import eventBus from './utils/eventBus'
import './permission'

Vue.use(Electron)
Vue.use(Element, {
  i18n: (key, value) => i18n.t(key, value)
})

Vue.config.productionTip = false
Vue.nprogress = Vue.prototype.$nprogress = nprogress
Vue.eventBus = Vue.prototype.$eventBus = eventBus

new Vue({
  el: '#app',
  router,
  store,
  i18n,
  template: '<App/>',
  components: { App }
})