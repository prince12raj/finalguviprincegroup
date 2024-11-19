package com.votingsystem.dao;

import java.util.List;

import com.votingsystem.model.User;

public interface UserDAO {
    void addUser(User user);          // Add a new user to the database
    void removeUser(int id);          // Remove a user by ID
    User getUserById(int id);         // Retrieve a user by ID
    void updateUser(User user);       // Update user information
	List<User> getAllUsers();
}
