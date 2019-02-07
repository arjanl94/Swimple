import Vue from 'vue';
import Vuex from 'vuex';


Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        title: 'VueX is working!',
        trainings: []
    },

    mutations: {
        SET_TRAININGS: (state, data) => {
            state.trainings = data
        }
    },

    actions: {
        getAllTrainings: (context) =>  {
            fetch('/swimple/api/trainings')
                .then(resp => resp.json())
                .then(data => {
                context.commit("SET_TRAININGS", data);
            });
        }
    },
});