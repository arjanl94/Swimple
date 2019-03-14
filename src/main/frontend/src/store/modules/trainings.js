import { trainingService } from "../../services/training.services";
import { router } from '../../router';

//Initial state
const state = {
    status: {},
    all: [],
    entity: null
}

// actions
const actions = {
    getAllTrainings({ commit }) {
        commit('REQUEST_TRAININGS');

        trainingService.getAll().then(trainings => {
            commit("SET_TRAININGS", trainings);
        });
    },

    getTraining({commit}, id) {
        commit("CLEAR_TRAINING");

        trainingService.get(id).then(training => {
            commit("SET_TRAINING", training);
        });
    },

    updateTraining({ commit, dispatch }, data) {
        trainingService.update(data.id, data)
            .then(training => {
                commit('SET_TRAINING', training);
                dispatch('notices/addNotice', 'Succesfully saved training!', { root: true });
                router.push({ name: "trainings#show", params: { id: training.id }});
            }).catch(error => {
                // return Promise.reject(error);
            });
    },

    createTraining({ commit, dispatch }, data) {
        trainingService.create(data).then(training => {
            commit('SET_TRAINING', training);
            dispatch('notices/addNotice', 'Succesfully saved training!', { root: true });
            router.push({ name: "trainings#show", params: { id: training.id }});
        }).catch(error => {
            // return Promise.reject(error);
        });
    }
}

// mutations
const mutations = {
    REQUEST_TRAININGS: (state) => {
      state.status = { isLoading: true }
    },

    SET_TRAININGS: (state, data) => {
        state.all = data
    },

    SET_TRAINING: (state, data) => {
        state.entity = data
    },

    CLEAR_TRAINING: (state) => {
        state.entity = null
    }
}

export default {
    namespaced: true,
    state,
    actions,
    mutations
}