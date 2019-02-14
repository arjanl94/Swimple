import { userService } from "../../services";
import { router } from "../../router";

const user = JSON.parse(localStorage.getItem("user"));
const initialState = user ? { status: { loggedIn: true}, user } : { status: {}, user: null}

const actions = {
    login({ dispatch, commit }, { email, password}) {
        commit('LOGIN_REQUEST', { email });

        userService.login(email, password)
            .then(user => {
                commit('LOGIN_SUCCESS', user);
                router.push('/');
            }).catch(error => {
              commit('LOGIN_FAILURE', error);
              dispatch('notices/addNotice', "Login failed", { root: true });
            });
    },

    logout({ commit }) {
        userService.logout();
        commit('LOGOUT');
    }
};

const mutations = {
    LOGIN_REQUEST: (state, user) => {
      state.status = { loggingIn: true };
      state.user = user;
    },

    LOGIN_SUCCESS: (state, user) => {
        state.status = { loggedIn: true };
        state.user = user;
    },

    LOGIN_FAILURE: (state) => {
        state.status = {};
        state.user = null;
    },

    LOGOUT: (state) => {
        state.status = {};
        state.user = null;
    }
};

export default {
    namespaced: true,
    state: initialState,
    actions,
    mutations
}