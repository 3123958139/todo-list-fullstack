package server.Models;

/**
 * User实体类，表示用户信息
 */
public class User {
    /** 用户名 */
    private String username;
    
    /** 用户密码 */
    private String password;
    
    /** 用户邮箱 */
    private String email;

    /**
     * 获取用户名
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 获取用户密码
     * @return 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 获取用户邮箱
     * @return 用户邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置用户名
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 设置用户密码
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 设置用户邮箱
     * @param email 用户邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 无参构造函数
     */
    public User() {
    }

    /**
     * 带参构造函数
     * @param username 用户名
     * @param password 用户密码
     * @param email 用户邮箱
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
