package server.Daos;

// 导入用户模型类
import server.Models.User;

// 导入列表接口
import java.util.List;

/**
 * 用户数据访问对象接口，定义了与用户数据操作相关的方法。
 */
public interface UserDao {
    /**
     * 获取所有用户信息。
     * @return 包含所有用户信息的列表
     */
    List<User> getAllUsers();

    /**
     * 根据用户名获取用户信息。
     * @param username 用户名
     * @return 对应的用户对象，如果未找到则返回 null
     */
    User getUserByUsername(String username);

    /**
     * 创建一个新用户。
     * @param user 用户对象，包含用户的各项信息
     */
    void createUser(User user);

    /**
     * 更新用户的密码。
     * @param user 用户对象，包含需要更新密码的用户信息
     */
    void updatePassword(User user);

    /**
     * 更新用户信息，但不更新密码。
     * @param user 用户对象，包含需要更新的用户信息（除密码外）
     */
    void updateUserWithoutPassword(User user);

    /**
     * 根据用户名删除用户。
     * @param username 要删除的用户的用户名
     */
    void deleteUser(String username);

    /**
     * 获取指定用户的角色列表。
     * @param username 用户名
     * @return 包含该用户所有角色的列表
     */
    List<String> getUserRoles(String username);

    /**
     * 为指定用户添加角色。
     * @param username 用户名
     * @param role 要添加的角色名称
     */
    void addRoleToUser(String username, String role);

    /**
     * 从指定用户移除角色。
     * @param username 用户名
     * @param role 要移除的角色名称
     */
    void removeRoleFromUser(String username, String role);
}
