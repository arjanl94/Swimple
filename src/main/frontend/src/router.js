import Vue from 'vue';
import Router from 'vue-router';
import Dashboard from "./pages/Dashboard";
import Calendar from "./pages/Calendar";
import Users from "./pages/Users";
import TrainingsShow from "./pages/TrainingsShow";
import TrainingsEdit from "./pages/TrainingsEdit";
import TrainingsNew from "./pages/TrainingsNew";
import Login from "./pages/Login";
import Register from "./pages/Register";
import GroupsNew from "./pages/GroupsNew";
import {userHasRole, userIsAdmin} from "./helpers";

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
            path: '/register',
            name: 'register',
            component: Register
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
            component: TrainingsNew,
            meta: {
                admin: true
            }
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
        },
        {
            path: '/groups/new',
            name: 'groups#new',
            component: GroupsNew
        },
    ]
});

router.beforeEach((to, from, next) => {
    const publicPages = ['/login', '/register'];
    const authRequired = !publicPages.includes(to.path);
    const loggedInUser = localStorage.getItem('user');

    if(authRequired && !loggedInUser) {
        return next('/login');
    }

    if(to.matched.some(record => record.meta.admin)) {
        if(!userIsAdmin(JSON.parse(loggedInUser))) {
            return next('/');
        }
    }

    next();
});