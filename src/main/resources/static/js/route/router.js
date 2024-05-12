import {createRouter, createWebHistory} from 'vue-router'

import MessagesList from "../pages/MessagesList.vue";
import Auth from "../pages/Auth.vue";
import Profile from "../pages/Profile.vue";

const routes = [
    { path: '/', component: MessagesList },
    { path: '/auth', component: Auth },
    { path: '/profile', component: Profile},
    // { path: '*', component: MessagesList },

]

export default createRouter({
    history: createWebHistory(),
    routes,
})