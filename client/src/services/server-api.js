import { router } from '../main';
import { store } from '../main';
import axios from 'axios';

export default {
    async login(username, password, rememberMe) {
        try {
            const authResponse = await axios.post('/auth/login', { username, password });
            const token = authResponse.data.accessToken.token;

            if (rememberMe) {
                localStorage.setItem('token', token);
            }

            const userResponse = await axios.get('/api/users/' + username, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            const rolesResponse = await axios.get('/api/users/' + username + '/roles', {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            store.commit(
                'SET_LOGIN_INFO', 
                { 
                    token, 
                    user: userResponse.data,
                    roles: rolesResponse.data 
                }
            );
            return true;
        } catch (error) {
            return false;
        }
    },

    async restoreLogin() {
        try {
            const token = localStorage.getItem('token');
            if (token) {
                console.log(token);
                const userResponse = await axios.get('/api/profile', {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });

                const rolesResponse = await axios.get('/api/profile/roles', {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });

                store.commit(
                    'SET_LOGIN_INFO', 
                    { 
                        token: token, 
                        user: userResponse.data,
                        roles: rolesResponse.data 
                    }
                );
                return true;
            } else {
                return false;
            }
        } catch (error) {
        }
        return false;
    },
    
    async logout() {
        store.commit('CLEAR_LOGIN_INFO', null);
        localStorage.removeItem('token');
        router.push('/login');
    },

    async getTodos() {
        try {
            const response = await axios.get('/api/todos', {
                headers: {
                    Authorization: `Bearer ${store.state.token}`
                }
            });
            return response.data;
        } catch (error) {
            this.logout();
        }
    }
}