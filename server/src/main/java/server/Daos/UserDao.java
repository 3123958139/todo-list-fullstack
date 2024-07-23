package server.Daos;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import server.Models.User;

@Component
public class UserDao {
    private final JdbcTemplate template; 
    private PasswordEncoder passwordEncoder;

    public UserDao(DataSource dataSource, PasswordEncoder passwordEncoder) {
        template = new JdbcTemplate(dataSource);
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        SqlRowSet rowSet = template.queryForRowSet("select * from users");
        while (rowSet.next()) {
            users.add(mapRowToUser(rowSet));
        }
        return users;
    }

    public User getUserByUsername(String username) {
        SqlRowSet row = template.queryForRowSet("select * from users where username = ?", username);
        if (row.next()) {
            return mapRowToUser(row);
        } else {
            return null;
        }
    }
    
    public void createUser(User user) {
        template.update(
            "insert into users (username, password, email) values (?, ?, ?)", 
            user.getUsername(),  
            passwordEncoder.encode(user.getPassword()), 
            user.getEmail()
        );
    }

    public void updatePassword(User user) {
        template.update("update users set password = ? where username = ?", passwordEncoder.encode(user.getPassword()), user.getUsername());
    }

    public void updateUserWithoutPassword(User user) {
        template.update("update users set email = ? where username = ?", user.getEmail(), user.getUsername());
    }

    public void deleteUser(String username) {
        template.update("delete from users where username = ?", username);
    }

    public List<String> getUserRoles(String username) {
        return template.queryForList("select role from user_roles where username = ?", String.class, username);
    }

    public void addRoleToUser(String username, String role) {
        template.update("insert into user_roles (username, role) values (?, ?)", username, role);
    }

    public void removeRoleFromUser(String username, String role) {
        template.update("delete from user_roles where username = ? and role = ?", username, role);
    }

    private User mapRowToUser(SqlRowSet row) {
        return new User(
            row.getString("username"),
            row.getString("password"),
            row.getString("email")
        );
    }
}
