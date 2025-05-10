// 声明包名，该类属于 server.Services 包
package server.Services;

// 导入 JwtUser 类，用于处理 JWT 用户信息
import eu.fraho.spring.securityJwt.base.dto.JwtUser;
// 导入密码编码器类，此处未使用，但可能在其他部分需要
import org.springframework.security.crypto.password.PasswordEncoder;
// 导入用户数据访问对象类，用于与数据库交互获取用户信息
import server.Daos.UserDao;
// 导入用户模型类，用于表示数据库中的用户实体
import server.Models.User;

// 导入简单授权信息类，用于构建用户的权限信息
import org.springframework.security.core.authority.SimpleGrantedAuthority;
// 导入用户详情接口，定义了用户的基本信息和权限信息
import org.springframework.security.core.userdetails.UserDetails;
// 导入用户详情服务接口，用于加载用户信息
import org.springframework.security.core.userdetails.UserDetailsService;
// 导入用户名未找到异常类，当用户不存在时抛出该异常
import org.springframework.security.core.userdetails.UsernameNotFoundException;
// 导入组件注解，将该类标记为 Spring 组件，以便 Spring 自动扫描和管理
import org.springframework.stereotype.Component;
// 导入列表接口，用于存储用户的角色信息
import java.util.List;

/**
 * 自定义用户详情服务类，实现了 Spring Security 的 UserDetailsService 接口，
 * 用于根据用户名加载用户的详细信息。
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    // 用户数据访问对象，用于从数据库中获取用户信息
    private UserDao userDao;

    /**
     * 构造函数，通过依赖注入的方式初始化用户数据访问对象。
     * @param userDao 用户数据访问对象
     */
    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 根据用户名加载用户的详细信息。
     * @param username 用户名
     * @return 用户详情对象
     * @throws UsernameNotFoundException 当用户不存在时抛出该异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中根据用户名获取用户信息
        User dbUser = userDao.getUserByUsername(username);
        // 从数据库中根据用户名获取用户的角色信息
        List<String> roles = userDao.getUserRoles(username);
        
        // 创建一个 JwtUser 对象，用于封装用户信息和权限信息
        JwtUser jwtUser = new JwtUser();
        // 设置 JwtUser 对象的用户名
        jwtUser.setUsername(username);
        // 设置 JwtUser 对象的密码
        jwtUser.setPassword(dbUser.getPassword());

        // 遍历用户的角色列表，将每个角色添加到 JwtUser 对象的权限列表中
        for (String role : roles) {
            jwtUser.getAuthorities().add(new SimpleGrantedAuthority(role));
        }

        // 设置用户账户未过期
        jwtUser.setAccountNonExpired(true);
        // 设置用户账户未锁定
        jwtUser.setAccountNonLocked(true);
        // 设置用户允许进行 API 访问
        jwtUser.setApiAccessAllowed(true);
        // 设置用户凭证未过期
        jwtUser.setCredentialsNonExpired(true);
        // 设置用户已启用
        jwtUser.setEnabled(true);
        // 返回 JwtUser 对象，该对象实现了 UserDetails 接口
        return jwtUser;
    }
}