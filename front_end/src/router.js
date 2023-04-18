import { createRouter, createWebHistory } from "vue-router";

import HomePage from './pages/HomePage.vue';
import SinglePhoto from './pages/SinglePhoto.vue';
import Contacts from './pages/Contacts.vue';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomePage
        },
        {
            path: '/photos/:id',
            name: 'single-photo',
            component: SinglePhoto
        },
        {
            path: '/contacts',
            name: 'contacts',
            component: Contacts
        },
    ]
});
export {router}