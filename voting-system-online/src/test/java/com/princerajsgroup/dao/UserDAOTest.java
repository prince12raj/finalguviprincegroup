package com.princerajsgroup.dao;

import com.princerajgroup.dao.UserDAO;
import com.princerajgroup.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {

    private UserDAO userDAO;
    private User testUser;

    @BeforeEach
    public void setUp() {
        userDAO = new UserDAO();
        testUser = new User(1, "testuser", "test@example.com", "password");
    }

    @Test
    public void testCreateUser() throws SQLException {
        userDAO.createUser(testUser);
        User fetchedUser = userDAO.getUserById(1);
        assertNotNull(fetchedUser);
        assertEquals(testUser.getUsername(), fetchedUser.getUsername());
    }

    @Test
    public void testGetUserById() throws SQLException {
        userDAO.createUser(testUser);
        User fetchedUser = userDAO.getUserById(1);
        assertNotNull(fetchedUser);
        assertEquals(testUser.getUsername(), fetchedUser.getUsername());
    }

    @Test
    public void testGetAllUsers() throws SQLException {
        userDAO.createUser(testUser);
        List<User> users = userDAO.getAllUsers();
        assertTrue(users.size() > 0);
    }

    @Test
    public void testUpdateUser() throws SQLException {
        userDAO.createUser(testUser);
        testUser.setUsername("updatedUser");
        userDAO.updateUser(testUser);

        User updatedUser = userDAO.getUserById(1);
        assertEquals("updatedUser", updatedUser.getUsername());
    }

    @Test
    public void testDeleteUser() throws SQLException {
        userDAO.createUser(testUser);
        userDAO.deleteUser(1);
        User deletedUser = userDAO.getUserById(1);
        assertNull(deletedUser);
    }

    @Test
    public void testAuthenticateUser() throws SQLException {  // Corrected the method name here
        userDAO.createUser(testUser);
        User authenticatedUser = userDAO.authenticateUser("testuser", "password");  // Corrected the method call here
        assertNotNull(authenticatedUser);
        assertEquals(testUser.getUsername(), authenticatedUser.getUsername());
    }
}
