package com.princerajgroup.dao;

import com.princerajgroup.model.User;
import org.junit.jupiter.api.*;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    private UserDAO userDAO;
    private User testUser;

    @BeforeEach
    void setUp() {
        userDAO = new UserDAO();
        testUser = new User(0, "testuser", "test@example.com", "password123");
    }

    @Test
    void testCreateUser() throws SQLException {
        userDAO.createUser(testUser);
        assertNotEquals(0, testUser.getId());
    }

    @Test
    void testGetUserById() throws SQLException {
        userDAO.createUser(testUser);
        User retrievedUser = userDAO.getUserById(testUser.getId());
        assertNotNull(retrievedUser);
        assertEquals(testUser.getUsername(), retrievedUser.getUsername());
    }

    @Test
    void testGetUserByEmail() throws SQLException {
        userDAO.createUser(testUser);
        User retrievedUser = userDAO.getUserByEmail(testUser.getEmail());
        assertNotNull(retrievedUser);
        assertEquals(testUser.getEmail(), retrievedUser.getEmail());
    }

    @Test
    void testUpdateUser() throws SQLException {
        userDAO.createUser(testUser);
        testUser.setUsername("updateduser");
        userDAO.updateUser(testUser);
        User updatedUser = userDAO.getUserById(testUser.getId());
        assertEquals("updateduser", updatedUser.getUsername());
    }

    @Test
    void testDeleteUser() throws SQLException {
        userDAO.createUser(testUser);
        userDAO.deleteUser(testUser.getId());
        assertNull(userDAO.getUserById(testUser.getId()));
    }

    @Test
    void testGetAllUsers() throws SQLException {
        userDAO.createUser(testUser);
        assertFalse(userDAO.getAllUsers().isEmpty());
    }
}