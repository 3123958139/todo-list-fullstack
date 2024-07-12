package com.mlambert.todo.Controllers;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mlambert.todo.Models.Todo;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "upgraded-goggles-7x446wgg7pwfr54p-5173.app.github.dev")
public class TodoController {
    private JdbcTemplate template;

    public TodoController(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @GetMapping
    public List<Todo> listTodos() {
        return template.query("select * from todos", (row, index) -> new Todo(
            row.getInt("id"),
            row.getString("title").toUpperCase(),
            row.getBoolean("completed")
        ));
    }
}
