package server.Daos;

import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import server.Models.Todo;

@Component
public class TodoDao {
    private final JdbcTemplate template;

    public TodoDao(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<>();
        SqlRowSet rowSet = template.queryForRowSet("select * from todos");

        while (rowSet.next()) {
            todos.add(mapRowToTodo(rowSet));
        }
        return todos;
    }

    public Todo getTodoById(int id) {
        SqlRowSet row = template.queryForRowSet("select * from todos where id = ?", id);

        if (row.next()) {
            return mapRowToTodo(row);
        } else {
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

    private Todo mapRowToTodo(SqlRowSet row) {
        return new Todo(
            row.getInt("id"),
            row.getString("title"),
            row.getBoolean("completed")
        );
    }
}
