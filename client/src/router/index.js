import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import About from '../views/About.vue';
import Login from '@/views/Login.vue';
import { store } from '@/main';

export function createAppRouter() {
    const router = createRouter({
        history: createWebHistory(),
        routes: [
            {
                path: '/',
                name: 'Home',
                requireAuth: true,
                component: Home
            },
            {
                path: '/about',
                name: 'About',
                requireAuth: true,
                component: About
            },
            {
                path: '/login',
                name: 'Login',
                requireAuth: false,
                component: Login
            }
        ]
    });
    router.beforeEach((to, from, next) => {
        if (to.path !== '/login' && store.state.token === null) {
            next('/login');
        } else {
            next();
        }
    });
    return router;
}