package com.princerajgroup.dao;

import com.princerajgroup.model.User;
import com.princerajgroup.jdbc.DatabaseConnection; // Importing the DatabaseConnection class
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    // SQL Queries
    private static final String INSERT_USER = "INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String UPDATE_USER = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static final String AUTHENTICATE_USER = "SELECT * FROM users WHERE username = ? AND password = ?";

    // Method to get a connection from DatabaseConnection class
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection(); // Using the custom connection method from DatabaseConnection class
    }

    // Create a new user
    public void createUser(User user) throws SQLException {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_USER)) {
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword()); // Consider hashing the password
            stmt.executeUpdate();
        }
    }

    // Get a user by ID
    public User getUserById(int id) throws SQLException {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_USER_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
                            rs.getString("password"));
                }
            }
        }
        return null;
    }

    // Get all users
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_USERS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
                        rs.getString("password")));
            }
        }
        return users;
    }

    // Update a user's information
    public void updateUser(User user) throws SQLException {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_USER)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword()); // Consider hashing the password
            stmt.setInt(4, user.getId());
            stmt.executeUpdate();
        }
    }

    // Delete a user by ID
    public void deleteUser(int id) throws SQLException {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_USER)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Authenticate a user
    public User authenticateUser(String username, String password) throws SQLException {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(AUTHENTICATE_USER)) {
            stmt.setString(1, username);
            stmt.setString(2, password); // Consider hashing the password for security
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
                            rs.getString("password"));
                }
            }
        }
        return null;
    }
}
