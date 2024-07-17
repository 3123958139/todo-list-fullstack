import { createStore } from 'vuex';

export function createAppStore() {
    return createStore({
        state: {
            count: 0
        },
        mutations: {
            increment(state) {
                state.count++;
            }
        }
    });
}