<script>
  import serverApi from '../services/server-api';

  export default {
    data() {
      return {
        todos: []
      };
    },
    async mounted() {
      this.todos = await serverApi.getTodos();
    }
  }
</script>

<template>
  <h1>Todo List</h1>
  <ul>
    <li v-for="todo in todos">
      {{ todo.title }} - {{ todo.completed }}
    </li>
  </ul>
</template>
