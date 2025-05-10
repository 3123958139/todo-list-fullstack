// 声明该类所在的包，此包名为 server.Daos
package server.Daos;

// 导入 server.Models 包下的 Todo 类
import server.Models.Todo;

// 导入 java.util 包下的 List 接口
import java.util.List;

/**
 * 该接口定义了与 Todo 数据操作相关的方法，用于对 Todo 数据进行增删改查操作。
 */
public interface TodoDao {
    /**
     * 获取所有的 Todo 数据。
     * @return 包含所有 Todo 数据的列表
     */
    List<Todo> getAllTodos();

    /**
     * 根据指定的 ID 获取对应的 Todo 数据。
     * @param id 要查询的 Todo 数据的 ID
     * @return 对应 ID 的 Todo 数据，如果未找到则返回 null
     */
    Todo getTodoById(int id);

    /**
     * 创建一条新的 Todo 数据。
     * @param todo 要创建的 Todo 数据对象
     */
    void createTodo(Todo todo);

    /**
     * 更新一条已有的 Todo 数据。
     * @param todo 包含更新信息的 Todo 数据对象
     */
    void updateTodo(Todo todo);

    /**
     * 根据指定的 ID 删除对应的 Todo 数据。
     * @param id 要删除的 Todo 数据的 ID
     */
    void deleteTodoById(int id);
}
