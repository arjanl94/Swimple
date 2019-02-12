import Vue from 'vue'
import VueTextareaAutosize from 'vue-textarea-autosize';
import App from './App.vue'
import store from './store';
import router from './router';

import '@/assets/css/tailwind.css'

Vue.use(VueTextareaAutosize)
Vue.config.productionTip = false

new Vue({
    router,
    store,
  render: h => h(App),
}).$mount('#app')
