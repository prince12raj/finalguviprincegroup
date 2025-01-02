package com.princerajgroup.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Declare the connection details as static variables
    private static final String URL = "jdbc:mysql://localhost:3306/voting_system?useSSL=false&serverTimezone=Asia/Kolkata";
    private static final String USER = "root";  // replace with your database username
    private static final String PASSWORD = "12345";  // replace with your database password

    public static void main(String[] args) {
        try {
            // Register the JDBC driver (optional for newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the connection
            Connection connection = getConnection(); // Use getConnection() to get the connection
            System.out.println("Connection established successfully!");
            
            // Close the connection
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }

    // The getConnection() method is now using the static connection details
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
