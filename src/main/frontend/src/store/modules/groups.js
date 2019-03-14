import {groupService} from "../../services/group.services";
import { router } from '../../router';

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

    createGroup({ commit, dispatch }, group) {
      groupService.create(group).then(group => {
          commit('SELECT_GROUP', group);
          dispatch('notices/addNotice', 'Succesfully created group!', { root: true});
          router.push({ name: 'users#index' });
      }).catch(error => {

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