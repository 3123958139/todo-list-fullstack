import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import { createAppRouter } from './router'
import { createAppStore } from './store'
import { BootstrapIconsPlugin } from "bootstrap-icons-vue";
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

const app = createApp(App);
export const router = createAppRouter();
export const store = createAppStore();
const vuetify = createVuetify({ components, directives });

const localStorageToken = localStorage.getItem('token');
if (localStorageToken) {
    store.commit('setToken', localStorageToken);
}

app.use(router);
app.use(store);
app.use(BootstrapIconsPlugin);
app.use(vuetify);

app.mount('#app')
