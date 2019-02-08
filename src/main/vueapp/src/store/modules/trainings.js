
// initial state
const state = {
    all: []
}

// actions
const actions = {
    getAllTrainings({ commit }) {
        fetch('/swimple/api/trainings')
            .then(resp => resp.json())
            .then(data => {
                commit("SET_TRAININGS", data);
            });
    }
}

// mutations
const mutations = {
    SET_TRAININGS: (state, data) => {
        state.all = data
    }
}

export default {
    namespaced: true,
    state,
    actions,
    mutations
}