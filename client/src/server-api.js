import { router } from './main';
import { store } from './main';

export default {
    async login(username, password, rememberMe) {
        const authResponse = await fetch('/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        });

        if (authResponse.ok) {
            const body = await authResponse.json();
            store.commit('setToken', body.accessToken.token);
            if (rememberMe) {
                localStorage.setItem('token', body.accessToken.token);
            }

            const userResponse = await fetch('/api/users/' + username, {
                headers: {
                    authorization: `Bearer ${body.accessToken.token}`
                }
            });
            if (userResponse.ok) {
                const user = await userResponse.json();
                store.commit('setUser', user);
            } 
        }
        return authResponse.ok;
    },
    
    async logout() {
        store.commit('setToken', null);
        localStorage.removeItem('token');
        router.push('/login');
    },

    async getTodos() {
        const response = await fetch(
            '/api/todos',
            {
                headers: {
                    authorization: `Bearer ${store.state.token}`
                }
            }
        );
        if (response.ok) {
            return await response.json();
        } else {
            this.logout();
        }
    }
}