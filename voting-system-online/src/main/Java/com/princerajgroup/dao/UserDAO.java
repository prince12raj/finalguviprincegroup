package com.princerajgroup.dao;

import com.princerajgroup.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/voting_system";
    private static final String USER = "voting_user";
    private static final String PASSWORD = "yourpassword";

    // Method to get a connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Create a new user
    public void createUser(User user) throws SQLException {
        String query = "INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
        }
    }

    // Get a user by ID
    public User getUserById(int id) throws SQLException {
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        }
        return null;
    }

    // Get all users
    public List<User> getAllUsers() throws SQLException {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }
        }
        return users;
    }

    // Update a user's information
    public void updateUser(User user) throws SQLException {
        String query = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getId());
            stmt.executeUpdate();
        }
    }

    // Delete a user by ID
    public void deleteUser(int id) throws SQLException {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
