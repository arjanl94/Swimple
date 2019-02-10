import Vue from 'vue';
import Router from 'vue-router';
import Dashboard from "./pages/Dashboard";
import Calendar from "./pages/Calendar";
import Users from "./pages/Users";
import TrainingsShow from "./pages/TrainingsShow";

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'home',
            component: Dashboard
        },
        {
            path: '/calendar',
            name: 'calendar#index',
            component: Calendar
        },
        {
            path: '/users',
            name: 'users#index',
            component: Users
        },
        {
            path: '/trainings/:id',
            name: 'trainings#show',
            component: TrainingsShow
        },
        {
            path: '/trainings/:id/edit',
            name: 'trainings#edit',
            component: TrainingsShow
        }
    ]
})