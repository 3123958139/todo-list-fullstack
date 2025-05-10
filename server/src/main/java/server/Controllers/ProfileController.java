// 声明该类所在的包
package server.Controllers;

// 导入 List 类，用于处理列表数据
import java.util.List;
// 导入 Principal 类，用于获取当前用户的信息
import java.security.Principal;
// 导入 CrossOrigin 注解，用于处理跨域请求
import org.springframework.web.bind.annotation.CrossOrigin;
// 导入 GetMapping 注解，用于处理 HTTP GET 请求
import org.springframework.web.bind.annotation.GetMapping;
// 导入 RequestMapping 注解，用于映射请求路径
import org.springframework.web.bind.annotation.RequestMapping;
// 导入 RestController 注解，将该类标记为 RESTful 风格的控制器
import org.springframework.web.bind.annotation.RestController;

// 导入 UserDao 类，用于与用户数据访问对象交互
import server.Daos.UserDao;
// 导入 User 类，用于表示用户模型
import server.Models.User;

/**
 * 该类是一个 RESTful 控制器，用于处理与用户个人资料相关的请求。
 */
@RestController
// 定义该控制器处理的基础请求路径
@RequestMapping("/api/profile")
// 允许跨域请求
@CrossOrigin
public class ProfileController {
    // 定义用户数据访问对象
    private UserDao userDao;

    /**
     * 构造函数，用于注入 UserDao 实例。
     * @param userDao 用户数据访问对象
     */
    public ProfileController(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 处理获取当前用户信息的请求。
     * @param principal 包含当前用户信息的对象
     * @return 当前用户的信息
     */
    @GetMapping("")
    public User getCurrentUser(Principal principal) {
        return userDao.getUserByUsername(principal.getName());
    }

    /**
     * 处理获取当前用户角色的请求。
     * @param principal 包含当前用户信息的对象
     * @return 当前用户的角色列表
     */
    @GetMapping("/roles")
    public List<String> getCurrentUserRoles(Principal principal) {
        return userDao.getUserRoles(principal.getName());
    }
}
