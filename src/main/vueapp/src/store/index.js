import Vue from 'vue';
import Vuex from 'vuex';
import trainings from './modules/trainings';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        trainings
    },
    strict: true
});