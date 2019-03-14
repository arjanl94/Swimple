import {commentService} from "../../services";

const state = {
    all: [],
    status: {}
}

const actions = {
    getAllComments({ commit }, training) {
        commit('COMMENTS_CLEAR');
        commit('COMMENTS_REQUEST');

        commentService.getAll(training.id)
            .then(comments => {
                commit('COMMENTS_SUCCESS', comments);
            }).catch(error => {
                commit('COMMENTS_FAILURE');
        })
    },

    createComment({ commit, dispatch }, { training, body }) {
        console.log('--VUEX', body);
        commentService.create(training, body)
            .then(() => {
                dispatch('getAllComments', training);
            })
    }
}

const mutations = {
    COMMENTS_CLEAR: (state) => {
        state.status = {};
        state.all = null;
    },

    COMMENTS_REQUEST: (state) => {
        state.status = { loading: true };
    },

    COMMENTS_SUCCESS: (state, comments) => {
        state.status = { loaded: true };
        state.all = comments;
    },

    COMMENTS_FAILURE: (state) => {
        state.status = {};
        state.all = null;
    }
}

export default {
    namespaced: true,
    state,
    actions,
    mutations
}