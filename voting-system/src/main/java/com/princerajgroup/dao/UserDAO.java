package com.princerajgroup.dao;

import com.princerajgroup.model.User;
import com.princerajgroup.jdbc.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String INSERT_USER = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String UPDATE_USER = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";

    public void createUser(User user) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            }
        }
    }

    public User getUserById(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_USER_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("username"), 
                                  rs.getString("email"), rs.getString("password"));
                }
            }
        }
        return null;
    }

    public User getUserByEmail(String email) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_USER_BY_EMAIL)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("username"), 
                                  rs.getString("email"), rs.getString("password"));
                }
            }
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_USERS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("username"), 
                                 rs.getString("email"), rs.getString("password")));
            }
        }
        return users;
    }

    public void updateUser(User user) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_USER)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_USER)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}