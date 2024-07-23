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
public class JdbcUserDao implements UserDao {
    private final JdbcTemplate template; 
    private PasswordEncoder passwordEncoder;

    public JdbcUserDao(DataSource dataSource, PasswordEncoder passwordEncoder) {
        template = new JdbcTemplate(dataSource);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        SqlRowSet rowSet = template.queryForRowSet("select * from users order by username");
        while (rowSet.next()) {
            users.add(mapRowToUser(rowSet));
        }
        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        SqlRowSet row = template.queryForRowSet("select * from users where username = ?", username);
        if (row.next()) {
            return mapRowToUser(row);
        } else {
            return null;
        }
    }
    
    @Override
    public void createUser(User user) {
        template.update(
            "insert into users (username, password, email) values (?, ?, ?)", 
            user.getUsername(),  
            passwordEncoder.encode(user.getPassword()), 
            user.getEmail()
        );
    }

    @Override
    public void updatePassword(User user) {
        template.update("update users set password = ? where username = ?", passwordEncoder.encode(user.getPassword()), user.getUsername());
    }

    @Override
    public void updateUserWithoutPassword(User user) {
        template.update("update users set email = ? where username = ?", user.getEmail(), user.getUsername());
    }

    @Override
    public void deleteUser(String username) {
        template.update("delete from users where username = ?", username);
    }

    @Override
    public List<String> getUserRoles(String username) {
        return template.queryForList("select role from user_roles where username = ?", String.class, username);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        template.update("insert into user_roles (username, role) values (?, ?)", username, role);
    }

    @Override
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
