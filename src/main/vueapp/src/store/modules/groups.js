import {groupService} from "../../services/group.services";

const state = {
    all: [],
    selected: null,
    status: {}
}

const actions = {
    getAllGroups({ commit }) {
        commit('GROUPS_REQUEST');

        groupService.getAll()
            .then(groups => {
                commit('GROUPS_SUCCESS', groups);
            }).catch(error => {
                commit('GROUPS_FAILURE');
                console.log(error);
            });
    },

    select({ commit }, group) {
        commit('SELECT_GROUP', group);
    }
}

const mutations = {
    GROUPS_REQUEST: (state) => {
        state.status = { loading: true };
    },

    GROUPS_SUCCESS: (state, groups) => {
        state.status = { loaded: true };
        state.all = groups;
    },

    GROUPS_FAILURE: (state) => {
        state.status = {};
        state.all = null;
    },

    SELECT_GROUP: (state, group) => {
        state.selected = group;
    }
}

export default {
    namespaced: true,
    state,
    actions,
    mutations
}