// 定义包名为 server.Controllers
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

// 导入自定义的 UserDao 类，用于与数据库交互用户数据
import server.Daos.UserDao;
// 导入自定义的 User 类，用于表示用户实体
import server.Models.User;
// 导入 Spring 框架的 RequestParam 注解，用于获取请求参数
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户控制器类，处理与用户相关的 HTTP 请求
 */
@RestController
// 映射所有以 /api/users 开头的请求到该控制器
@RequestMapping("/api/users")
// 允许跨域请求
@CrossOrigin
public class UserController {
    // 定义 UserDao 实例，用于与数据库交互
    private UserDao userDao;

    /**
     * 构造函数，通过依赖注入初始化 UserDao 实例
     * @param userDao 用户数据访问对象
     */
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 处理根路径的 GET 请求，根据请求参数返回一个字符串
     * @param param 请求参数
     * @return 一个空字符串
     */
    @GetMapping("")
    public String requestMethodName(@RequestParam String param) {
        return new String();
    }

    /**
     * 获取所有用户列表
     * @return 用户列表
     */
    public List<User> listUsers() {
        return userDao.getAllUsers();
    }

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户实体
     */
    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userDao.getUserByUsername(username);
    }

    /**
     * 创建新用户
     * @param user 用户实体
     */
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        userDao.createUser(user);
    }

    /**
     * 更新用户信息（不包含密码）
     * @param User 用户实体
     */
    @PutMapping("/{username}")
    public void updateUserWithoutPassword(@RequestBody User User) {
        userDao.updateUserWithoutPassword(User);
    }

    /**
     * 更新用户密码
     * @param username 用户名
     * @param User 用户实体
     */
    @PutMapping("/password/{username}")
    public void updateUser(@PathVariable String username, @RequestBody User User) {
        User.setUsername(username);
        userDao.updatePassword(User);
    }

    /**
     * 根据用户名删除用户
     * @param username 用户名
     */
    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserByUsername(@PathVariable String username) {
        userDao.deleteUser(username);
    }

    /**
     * 根据用户名获取用户角色列表
     * @param username 用户名
     * @return 用户角色列表
     */
    @GetMapping("/{username}/roles")
    public List<String> getUserRoles(@PathVariable String username) {
        return userDao.getUserRoles(username);
    }

    /**
     * 为用户添加角色
     * @param username 用户名
     * @param role 角色名称
     */
    @PostMapping("/{username}/roles/{role}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoleToUser(@PathVariable String username, @PathVariable String role) {
        userDao.addRoleToUser(username, role);
    }

    /**
     * 从用户移除角色
     * @param username 用户名
     * @param role 角色名称
     */
    @DeleteMapping("/{username}/roles/{role}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRoleFromUser(@PathVariable String username, @PathVariable String role) {
        userDao.removeRoleFromUser(username, role);
    }
}
