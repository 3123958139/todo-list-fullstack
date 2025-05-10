package server.Daos;

import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import server.Models.Todo;

/**
 * 使用JDBC实现的Todo数据访问对象
 */
@Component
public class JdbcTodoDao implements TodoDao {
    private final JdbcTemplate template;

    /**
     * 构造函数
     * @param dataSource 数据源
     */
    public JdbcTodoDao(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    /**
     * 获取所有Todo项
     * @return 包含所有Todo项的列表
     */
    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<>();
        SqlRowSet rowSet = template.queryForRowSet("select * from todos order by id");

        while (rowSet.next()) {
            todos.add(mapRowToTodo(rowSet));
        }
        return todos;
    }

    @Override
    /**
     * 根据ID获取单个Todo项
     * @param id Todo项的ID
     * @return 找到的Todo对象，未找到则返回null
     */
    public Todo getTodoById(int id) {
        SqlRowSet row = template.queryForRowSet("select * from todos where id = ?", id);

        if (row.next()) {
            return mapRowToTodo(row);
        } else {
            return null;
        }
    }

    @Override
    /**
     * 创建新的Todo项
     * @param todo 要创建的Todo对象
     */
    public void createTodo(Todo todo) {
        template.update("insert into todos (title, completed) values (?, ?)", todo.getTitle(), todo.isCompleted());
    }

    @Override
    /**
     * 更新Todo项
     * @param todo 包含更新数据的Todo对象
     */
    public void updateTodo(Todo todo) {
        template.update("update todos set title = ?, completed = ? where id = ?", todo.getTitle(), todo.isCompleted(), todo.getId());
    }

    @Override
    /**
     * 根据ID删除Todo项
     * @param id 要删除的Todo项ID
     */
    public void deleteTodoById(int id) {
        template.update("delete from todos where id = ?", id);
    }

    /**
     * 将数据库行映射为Todo对象
     * @param row 数据库行数据
     * @return 映射后的Todo对象
     */
    private Todo mapRowToTodo(SqlRowSet row) {
        return new Todo(
            row.getInt("id"),
            row.getString("title"),
            row.getBoolean("completed")
        );
    }
}
