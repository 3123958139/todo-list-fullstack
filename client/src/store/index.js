import { createStore } from 'vuex';

export function createAppStore() {
    return createStore({
        state: {
            token: null,
            user: null,
            count: 0
        },
        mutations: {
            increment(state) {
                state.count++;
            },
            setToken(state, token) {
                state.token = token;
            },
            setUser(state, user) {
                state.user = user;
            }
        }
    });
}