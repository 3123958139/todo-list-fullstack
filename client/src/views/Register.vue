<script>
  import serverApi from '../services/server-api';

  export default {
    data() {
      return {
        todos: []
      };
    },
    mounted() {
      serverApi.getTodos().then(todos => this.todos = todos);
    }
  }
</script>

<template>
  <h1>Register</h1>
  <ul>
    <li v-for="todo in todos">
      {{ todo.title }} - {{ todo.completed }}
    </li>
  </ul>
</template>
