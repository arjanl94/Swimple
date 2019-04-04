import {userService} from "../../services";

const state = {
    all: [],
    status: {}
}

const actions = {
    getAllUsers({ commit }) {
        commit('USERS_REQUEST');

        userService.getAll()
            .then(users => {
                commit('USERS_SUCCESS', users);
            }).catch(() => {
               commit('USERS_FAILURE');
            });
    }
}

const getters = {
    getUsers(state, _getters, rootState) {
        if(!rootState.groups.selected) return state.all;

        return state.all.filter(user => {
            return user.groups.map(group => {
              return group.id;
            }).includes(rootState.groups.selected.id);
        });
    }
}

const mutations = {
    USERS_REQUEST: (state) => {
        state.status = { loading: true };
    },

    USERS_SUCCESS: (state, users) => {
        state.status = { loaded: true };
        state.all = users;
    },

    USERS_FAILURE: (state) => {
        state.status = {};
        state.all = null;
    }
}

export default {
    namespaced: true,
    state,
    actions,
    mutations,
    getters
}
