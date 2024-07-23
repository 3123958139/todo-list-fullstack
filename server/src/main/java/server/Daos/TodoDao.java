package server.Daos;

import server.Models.Todo;

import java.util.List;

public interface TodoDao {
    List<Todo> getAllTodos();

    Todo getTodoById(int id);

    void createTodo(Todo todo);

    void updateTodo(Todo todo);

    void deleteTodoById(int id);
}
