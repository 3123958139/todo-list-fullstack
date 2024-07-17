package server.Controllers;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import server.Models.Todo;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin
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
