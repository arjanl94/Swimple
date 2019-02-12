import Vue from 'vue';
import Vuex from 'vuex';
import trainings from './modules/trainings';
import notices  from './modules/notices';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        trainings,
        notices
    },
    strict: true
});