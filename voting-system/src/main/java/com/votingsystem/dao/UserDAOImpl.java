package com.votingsystem.dao;

import com.votingsystem.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/voting_system";
    private static final String USER = "voting_user";
    private static final String PASSWORD = "yourpassword";

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pre = con.prepareStatement(query)) {

            pre.setString(1, user.getUsername());
            pre.setString(2, user.getEmail());
            pre.setString(3, user.getPassword());
            pre.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    @Override
    public void removeUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pre = con.prepareStatement(query)) {

            pre.setInt(1, id);
            pre.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error removing user: " + e.getMessage());
        }
    }

    @Override
    public User getUserById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pre = con.prepareStatement(query)) {

            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error fetching user by ID: " + e.getMessage());
        }
        return null; // Return null if no user is found
    }

    @Override
    public void updateUser(User user) {
        String query = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pre = con.prepareStatement(query)) {

            pre.setString(1, user.getUsername());
            pre.setString(2, user.getEmail());
            pre.setString(3, user.getPassword());
            pre.setInt(4, user.getId());
            pre.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching all users: " + e.getMessage());
        }
        return users;
    }
}
