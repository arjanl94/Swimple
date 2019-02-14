import Vue from 'vue';
import Vuex from 'vuex';
import trainings from './modules/trainings';
import notices  from './modules/notices';
import auth from "./modules/auth";

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        trainings,
        notices,
        auth
    },
    strict: true
});