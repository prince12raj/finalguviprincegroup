package com.princerajgroup.votingsystem.controller;

import com.votingsystem.dao.UserDAO;
import com.votingsystem.dao.UserDAOImpl;
import com.votingsystem.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserDAO userDAO;

    // Constructor to inject UserDAO implementation
    public UserController() {
        this.userDAO = new UserDAOImpl();  // This should ideally be injected via a service layer
    }

    // Endpoint to add a new user
    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        try {
            userDAO.addUser(user);
            return "User added successfully!";
        } catch (Exception e) {
            return "Error adding user: " + e.getMessage();
        }
    }

    // Endpoint to update an existing user
    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user) {
        try {
            user.setId(id);  // Ensure the ID is set correctly
            userDAO.updateUser(user);
            return "User updated successfully!";
        } catch (Exception e) {
            return "Error updating user: " + e.getMessage();
        }
    }

    // Endpoint to delete a user by ID
    @DeleteMapping("/delete/{id}")
    public String removeUser(@PathVariable int id) {
        try {
            userDAO.removeUser(id);
            return "User removed successfully!";
        } catch (Exception e) {
            return "Error removing user: " + e.getMessage();
        }
    }

    // Endpoint to get all users
    @GetMapping("/")
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    // Endpoint to get a user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userDAO.getUserById(id);
    }
}
