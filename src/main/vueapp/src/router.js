import Vue from 'vue';
import Router from 'vue-router';
import Dashboard from "./pages/Dashboard";
import Calendar from "./pages/Calendar";
import Users from "./pages/Users";
import TrainingsShow from "./pages/TrainingsShow";
import TrainingsEdit from "./pages/TrainingsEdit";
import TrainingsNew from "./pages/TrainingsNew";
import Login from "./pages/Login";

Vue.use(Router);

export const router = new Router({
    routes: [
        {
            path: '/',
            name: 'home',
            component: Dashboard
        },
        {
            path: '/login',
            name: 'login',
            component: Login
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
            path: '/trainings/new',
            name: 'trainings#new',
            component: TrainingsNew
        },
        {
            path: '/trainings/:id',
            name: 'trainings#show',
            component: TrainingsShow
        },
        {
            path: '/trainings/:id/edit',
            name: 'trainings#edit',
            component: TrainingsEdit
        }
    ]
});

router.beforeEach((to, from, next) => {
    const publicPages = ['/login'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    if(authRequired && !loggedIn) {
        return next('/login');
    }

    next();
});