package server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.Daos.JdbcTodoDao;
import server.Models.Todo;

public class JdbcTodoDaoTests extends BaseDaoTests {
    private static final Todo TODO_1 = new Todo(1, "Wash dishes", false);
    private static final Todo TODO_2 = new Todo(2, "Feed Loki", false);
    private static final Todo TODO_3 = new Todo(3, "Feed Cat", false);

    private JdbcTodoDao dao;

    @Before
    public void setup() {
        dao = new JdbcTodoDao(dataSource);
    }

    @Test
    public void getTodoById_with_valid_id_returns_correct_todo() {
        Todo expected = TODO_1;
        Todo actual = dao.getTodoById(1);
        assertTodosMatch(expected, actual);
    }

    @Test
    public void getTodoById_with_invalid_id_returns_null_todo() {
        Todo actual = dao.getTodoById(100);
        Assert.assertNull(actual);
    }

    @Test
    public void getTodos_returns_list_of_all_todos() {
        Todo[] expected = new Todo[] {TODO_1, TODO_2, TODO_3};
        Todo[] actual = dao.getAllTodos().toArray(new Todo[0]);
        for (int i = 0; i < expected.length; i++) {
            assertTodosMatch(expected[i], actual[i]);
        }
    }

    @Test
    public void createTodo_creates_todo() {
        Todo expected = new Todo(4, "Do Something Else", false);
        dao.createTodo(expected);
        Todo actual = dao.getTodoById(4);
        assertTodosMatch(expected, actual);
    }

    @Test
    public void updateTodo_updates_todo() {
        Todo expected = new Todo(1, "Wash dishes", true);
        dao.updateTodo(expected);
        Todo actual = dao.getTodoById(1);
        assertTodosMatch(expected, actual);
    }

    @Test
    public void deleteTodoById_deletes_todo() {
        dao.deleteTodoById(1);
        Todo actual = dao.getTodoById(1);
        Assert.assertNull(actual);
    }

    private void assertTodosMatch(Todo expected, Todo actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getTitle(), actual.getTitle());
        Assert.assertEquals(expected.isCompleted(), actual.isCompleted());
    }
}
