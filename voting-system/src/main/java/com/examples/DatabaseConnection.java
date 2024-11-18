package com.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/voting_system";
    private static final String USER = "voting_user";
    private static final String PASSWORD = "yourpassword";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Establishing a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection to the database was successful!");

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.err.println("Connection failed: " + e.getMessage());
        } finally {
            // Ensure the connection is closed
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Connection closed.");
                } catch (SQLException e) {
                    System.err.println("Error closing the connection: " + e.getMessage());
                }
            }
        }
    }
}
