package server.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import server.Daos.UserDao;
import server.Models.User;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public List<User> listUsers() {
        return userDao.getAllUsers();
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userDao.getUserByUsername(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        userDao.createUser(user);
    }

    @PutMapping("/{username}")
    public void updateUserWithoutPassword(@RequestBody User User) {
        userDao.updateUserWithoutPassword(User);
    }

    @PutMapping("/password/{username}")
    public void updateUser(@PathVariable String username, @RequestBody User User) {
        User.setUsername(username);
        userDao.updatePassword(User);
    }
    
    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserByUsername(@PathVariable String username) {
        userDao.deleteUser(username);
    }

    @GetMapping("/{username}/roles")
    public List<String> getUserRoles(@PathVariable String username) {
        return userDao.getUserRoles(username);
    }

    @PostMapping("/{username}/roles/{role}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoleToUser(@PathVariable String username, @PathVariable String role) {
        userDao.addRoleToUser(username, role);
    }

    @DeleteMapping("/{username}/roles/{role}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRoleFromUser(@PathVariable String username, @PathVariable String role) {
        userDao.removeRoleFromUser(username, role);
    }
}
