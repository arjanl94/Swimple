
// initial state
const state = {
    all: [],
    entity: null
}

// actions
const actions = {
    getAllTrainings({ commit }) {
        fetch('/swimple/api/trainings')
            .then(resp => resp.json())
            .then(data => {
                commit("SET_TRAININGS", data);
            });
    },

    getTraining({commit}, id) {
        commit("SET_TRAINING", null);

        fetch(`/swimple/api/trainings/${id}`)
            .then(resp => resp.json())
            .then(data => {
                commit("SET_TRAINING", data);
            })
    }
}

// mutations
const mutations = {
    SET_TRAININGS: (state, data) => {
        state.all = data
    },

    SET_TRAINING: (state, data) => {
        state.entity = data
    }
}

export default {
    namespaced: true,
    state,
    actions,
    mutations
}