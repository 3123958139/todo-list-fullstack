<script>
  import { RouterView, RouterLink } from 'vue-router';
  import serverApi from './services/server-api';
  export default {
    components: {
      RouterView,
      RouterLink
    },
    methods: {
      async logout() {
        await serverApi.logout();
      }
    }
  }
</script>

<template>
  <header>
    <nav v-if="$store.state.token != null">
      <menu>
        <li>
          <router-link to="/">Home</router-link>
        </li>
        <li>
          <router-link to="/about">About</router-link>
        </li>
      </menu>
      <menu>
        <li v-if="$store.state.user">
          Welcome, {{ $store.state.user?.username }}
        </li>
        <li>
          <a href="#" @click="logout">Logout</a>
        </li>
      </menu>
    </nav>
  </header>
  <main>
    <RouterView />
  </main>
  <footer>
    <p>&copy; 2024</p>
  </footer>
</template>

<style scoped>
  /* Styles for content in this component are in main.css, as they are top-leve (directly below #app) */
</style>