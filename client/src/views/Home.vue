<script>
  export default {
    data() {
      return {
        todos: []
      };
    },
    async mounted() {
      const response = await fetch("/api/todos", { cache: "no-store", mode: "no-cors" });
      this.todos = await response.json();
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
