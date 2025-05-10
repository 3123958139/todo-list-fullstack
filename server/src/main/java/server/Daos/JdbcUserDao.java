package server.Daos;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import server.Daos.UserDao;
import eu.fraho.spring.securityJwt.base.dto.JwtUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import server.Models.User;

/**
 * 使用JDBC实现的用户数据访问对象
 */
@Component
public class JdbcUserDao implements UserDao {
    private final JdbcTemplate template; 
    private PasswordEncoder passwordEncoder;

    /**
     * 构造函数
     * @param dataSource 数据源
     * @param passwordEncoder 密码加密器
     */
    public JdbcUserDao(DataSource dataSource, PasswordEncoder passwordEncoder) {
        template = new JdbcTemplate(dataSource);
        this.passwordEncoder = passwordEncoder;
        // admin 1234567890 
        var x = passwordEncoder.encode("1234567890");
    }

    @Override
    /**
     * 获取所有用户
     * @return 包含所有用户的列表
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        SqlRowSet rowSet = template.queryForRowSet("select * from users order by username");
        while (rowSet.next()) {
            users.add(mapRowToUser(rowSet));
        }
        return users;
    }

    @Override
    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 找到的用户对象，未找到则返回null
     */
    public User getUserByUsername(String username) {
        SqlRowSet row = template.queryForRowSet("select * from users where username = ?", username);
        if (row.next()) {
            return mapRowToUser(row);
        } else {
            return null;
        }
    }
    
    @Override
    /**
     * 创建新用户
     * @param user 要创建的用户对象
     */
    public void createUser(User user) {
        template.update(
            "insert into users (username, password, email) values (?, ?, ?)", 
            user.getUsername(),  
            passwordEncoder.encode(user.getPassword()), 
            user.getEmail()
        );
    }

    @Override
    /**
     * 更新用户密码
     * @param user 包含新密码的用户对象
     */
    public void updatePassword(User user) {
        template.update("update users set password = ? where username = ?", passwordEncoder.encode(user.getPassword()), user.getUsername());
    }

    @Override
    /**
     * 更新用户信息(不更新密码)
     * @param user 包含更新数据的用户对象
     */
    public void updateUserWithoutPassword(User user) {
        template.update("update users set email = ? where username = ?", user.getEmail(), user.getUsername());
    }

    @Override
    /**
     * 删除用户
     * @param username 要删除的用户名
     */
    public void deleteUser(String username) {
        template.update("delete from users where username = ?", username);
    }

    @Override
    /**
     * 获取用户角色列表
     * @param username 用户名
     * @return 用户的角色列表
     */
    public List<String> getUserRoles(String username) {
        return template.queryForList("select role from user_roles where username = ?", String.class, username);
    }

    @Override
    /**
     * 为用户添加角色
     * @param username 用户名
     * @param role 要添加的角色
     */
    public void addRoleToUser(String username, String role) {
        template.update("insert into user_roles (username, role) values (?, ?)", username, role);
    }

    @Override
    /**
     * 移除用户的角色
     * @param username 用户名
     * @param role 要移除的角色
     */
    public void removeRoleFromUser(String username, String role) {
        template.update("delete from user_roles where username = ? and role = ?", username, role);
    }

    /**
     * 将数据库行映射为用户对象
     * @param row 数据库行数据
     * @return 映射后的用户对象
     */
    private User mapRowToUser(SqlRowSet row) {
        return new User(
            row.getString("username"),
            row.getString("password"),
            row.getString("email")
        );
    }
}
