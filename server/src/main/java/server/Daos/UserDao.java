package server.Daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
        return template.query("select * from users", this::mapRowToUser);
    }

    public User getUserByUsername(String username) {
        try {
            return template.queryForObject("select * from users where username = ?", this::mapRowToUser, username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    public void createUser(User user) {
        template.update("insert into users (username, password) values (?, ?)", user.getUsername(),  passwordEncoder.encode(user.getPassword()));
    }

    public void updatePassword(User user) {
        template.update("update users set password = ? where username = ?", passwordEncoder.encode(user.getPassword()), user.getUsername());
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

    private User mapRowToUser(ResultSet row, int rowNumber) throws SQLException {
        return new User(
            row.getString("username"),
            row.getString("password")
        );
    }
}
