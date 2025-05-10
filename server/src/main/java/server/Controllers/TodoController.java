// 声明该类所在的包
package server.Controllers;

// 导入 List 接口，用于处理列表数据
import java.util.List;

// 导入 Spring 框架的 HttpStatus 类，用于表示 HTTP 状态码
import org.springframework.http.HttpStatus;
// 导入 Spring 框架的 CrossOrigin 注解，用于处理跨域请求
import org.springframework.web.bind.annotation.CrossOrigin;
// 导入 Spring 框架的 DeleteMapping 注解，用于处理 HTTP DELETE 请求
import org.springframework.web.bind.annotation.DeleteMapping;
// 导入 Spring 框架的 GetMapping 注解，用于处理 HTTP GET 请求
import org.springframework.web.bind.annotation.GetMapping;
// 导入 Spring 框架的 PathVariable 注解，用于获取 URL 路径中的变量
import org.springframework.web.bind.annotation.PathVariable;
// 导入 Spring 框架的 PostMapping 注解，用于处理 HTTP POST 请求
import org.springframework.web.bind.annotation.PostMapping;
// 导入 Spring 框架的 PutMapping 注解，用于处理 HTTP PUT 请求
import org.springframework.web.bind.annotation.PutMapping;
// 导入 Spring 框架的 RequestBody 注解，用于获取请求体中的数据
import org.springframework.web.bind.annotation.RequestBody;
// 导入 Spring 框架的 RequestMapping 注解，用于映射请求路径
import org.springframework.web.bind.annotation.RequestMapping;
// 导入 Spring 框架的 ResponseStatus 注解，用于设置响应的 HTTP 状态码
import org.springframework.web.bind.annotation.ResponseStatus;
// 导入 Spring 框架的 RestController 注解，表明该类是一个 RESTful 风格的控制器
import org.springframework.web.bind.annotation.RestController; 

// 导入自定义的数据访问对象类
import server.Daos.TodoDao;
// 导入自定义的模型类
import server.Models.Todo;

/**
 * 该类是一个 RESTful 风格的控制器，用于处理与待办事项（Todo）相关的 HTTP 请求。
 */
@RestController
// 映射该控制器处理的基础请求路径
@RequestMapping("/api/todos")
// 允许跨域请求
@CrossOrigin
public class TodoController {
    // 定义一个 TodoDao 类型的成员变量，用于与数据库进行交互
    private TodoDao todoDao;

    /**
     * 构造函数，用于初始化 TodoDao 对象。
     * @param todoDao 用于与数据库进行交互的 TodoDao 对象
     */
    public TodoController(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    /**
     * 处理获取所有待办事项的 HTTP GET 请求。
     * @return 返回一个包含所有待办事项的列表
     */
    @GetMapping("")
    public List<Todo> listTodos() {
        return todoDao.getAllTodos();
    }

    /**
     * 处理创建新待办事项的 HTTP POST 请求。
     * @param todo 从请求体中获取的待办事项对象
     */
    @PostMapping("")
    // 设置响应的 HTTP 状态码为 201 Created
    @ResponseStatus(HttpStatus.CREATED)
    public void createTodo(@RequestBody Todo todo) {
        todoDao.createTodo(todo);
    }

    /**
     * 处理更新指定 ID 待办事项的 HTTP PUT 请求。
     * @param id 要更新的待办事项的 ID，从 URL 路径中获取
     * @param todo 从请求体中获取的更新后的待办事项对象
     */
    @PutMapping("/{id}")
    public void updateTodo(@PathVariable int id, @RequestBody Todo todo) {
        // 设置待办事项的 ID 为 URL 路径中的 ID
        todo.setId(id);
        todoDao.updateTodo(todo);
    }

    /**
     * 处理删除指定 ID 待办事项的 HTTP DELETE 请求。
     * @param id 要删除的待办事项的 ID，从 URL 路径中获取
     */
    @DeleteMapping("/{id}")
    // 设置响应的 HTTP 状态码为 204 No Content
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodoById(@PathVariable int id) {
        todoDao.deleteTodoById(id);
    }
}
