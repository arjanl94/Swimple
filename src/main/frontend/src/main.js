import Vue from "vue";
import App from "./App.vue";
import store from "./store";
import { router } from "./router";
import * as firebase from "firebase/app";

import "@/assets/css/tailwind.css";

Vue.config.productionTip = false;

var config = {
  apiKey: "AIzaSyAoVREzYW3GRdAy1Ko4_HA9JwDrw7oAoxg",
  authDomain: "swimple.firebaseapp.com",
  databaseURL: "https://swimple.firebaseio.com",
  projectId: "swimple",
  storageBucket: "swimple.appspot.com",
  messagingSenderId: "663309924776"
};

firebase.initializeApp(config);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
