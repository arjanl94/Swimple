const state = {
    all: []
}

const actions = {
    addNotice({commit}, notice) {
      commit('ADD_NOTICE', notice);

      setTimeout(function() {
          commit('REMOVE_NOTICES');
      }, 3000)
    },
}

const mutations = {
    ADD_NOTICE: (state, notice) => {
        state.all.push(notice);
    },

    REMOVE_NOTICES: (state) => {
        state.all = []
    }
}

export default {
    namespaced: true,
    state,
    actions,
    mutations
}