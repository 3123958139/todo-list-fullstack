package server.Daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import server.Models.Todo;

@Component
public class TodoDao {
    private final JdbcTemplate template;

    public TodoDao(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public List<Todo> getAllTodos() {
        return template.query("select * from todos", this::mapRowToTodo);
    }

    public Todo getTodoById(int id) {
        try {
            return template.queryForObject("select * from todos where id = ?", this::mapRowToTodo, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void createTodo(Todo todo) {
        template.update("insert into todos (title, completed) values (?, ?)", todo.getTitle(), todo.isCompleted());
    }

    public void updateTodo(Todo todo) {
        template.update("update todos set title = ?, completed = ? where id = ?", todo.getTitle(), todo.isCompleted(), todo.getId());
    }

    public void deleteTodoById(int id) {
        template.update("delete from todos where id = ?", id);
    }

    private Todo mapRowToTodo(ResultSet row, int rowNumber) throws SQLException {
        return new Todo(
            row.getInt("id"),
            row.getString("title"),
            row.getBoolean("completed")
        );
    }
}
