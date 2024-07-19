import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import { createAppRouter } from './router'
import { createAppStore } from './store'
import { BootstrapIconsPlugin } from "bootstrap-icons-vue";
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import serverApi from './services/server-api'

const app = createApp(App);
export const router = createAppRouter();
export const store = createAppStore();
const vuetify = createVuetify({ components, directives });

await serverApi.restoreLogin();

app.use(router);
app.use(store);
app.use(BootstrapIconsPlugin);
app.use(vuetify);

app.mount('#app')
