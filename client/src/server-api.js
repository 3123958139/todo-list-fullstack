import { router } from './main';
import { store } from './main';
import axios from 'axios';

export default {
    async login(username, password, rememberMe) {
        try {
            const authResponse = await axios.post('/auth/login', { username, password });
            store.commit('setToken', authResponse.data.accessToken.token);
            if (rememberMe) {
                localStorage.setItem('token', authResponse.data.accessToken.token);
            }

            const userResponse = await axios.get('/api/users/' + username, {
                headers: {
                    authorization: `Bearer ${authResponse.data.accessToken.token}`
                }
            });
            store.commit('setUser', userResponse.data);
            return true;
        } catch (error) {
            return false;
        }
    },
    
    async logout() {
        store.commit('setToken', null);
        localStorage.removeItem('token');
        router.push('/login');
    },

    async getTodos() {
        try {
            const response = await axios.get('/api/todos', {
                headers: {
                    authorization: `Bearer ${store.state.token}`
                }
            });
            return response.data;
        } catch (error) {
            this.logout();
        }
    }
}