import { userService } from "../../services";
import { router } from "../../router";

const user = JSON.parse(localStorage.getItem("user"));
const initialState = user ? { status: { loggedIn: true}, user } : { status: {}, user: null}

const actions = {
    login({ dispatch, commit }, { email, password }) {
        commit('LOGIN_REQUEST', { email });

        userService.login(email, password)
            .then(user => {
                commit('LOGIN_SUCCESS', user);
                router.push('/');
            }).catch(error => {
              commit('LOGIN_FAILURE', error);
              dispatch('notices/addNotice', error, { root: true });
            });
    },

    register({ dispatch, commit }, { name, email, password }) {
        commit('SIGNUP_REQUEST', { email });

        userService.register(name, email, password)
            .then(user => {
                commit('SIGNUP_SUCCESS', user);
                router.push('/');
            }).catch(error => {
                commit('SIGNUP_FAILURE', error);
                console.log(error);
                dispatch('notices/addNotice', "Signup failed", {root: true});
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

    SIGNUP_REQUEST: (state, user) => {
        state.status = { signingUp: true };
        state.user = user;
    },

    SIGNUP_SUCCESS: (state, user) => {
        state.status = { loggedIn: true };
        state.user = user;
    },

    SIGNUP_FAILURE: (state) => {
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