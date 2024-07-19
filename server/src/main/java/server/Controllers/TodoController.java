package server.Controllers;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController; 

import server.Daos.TodoDao;
import server.Models.Todo;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin
public class TodoController {
    private TodoDao todoDao;

    public TodoController(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Todo> listTodos() {
        return todoDao.getAllTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTodo(@RequestBody Todo todo) {
        todoDao.createTodo(todo);
    }

    @PutMapping("/{id}")
    public void updateTodo(@PathVariable int id, @RequestBody Todo todo) {
        todo.setId(id);
        todoDao.updateTodo(todo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodoById(@PathVariable int id) {
        todoDao.deleteTodoById(id);
    }
}
