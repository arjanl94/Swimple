import Vue from 'vue';
import Vuex from 'vuex';
import trainings from './modules/trainings';
import notices  from './modules/notices';
import auth from "./modules/auth";
import users from "./modules/users";
import groups from "./modules/groups";

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        trainings,
        notices,
        auth,
        users,
        groups
    },
    strict: true
});