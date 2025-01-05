package com.princerajgroup.servlet;

import com.princerajgroup.dao.UserDAO;
import com.princerajgroup.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/auth/register-form.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validate passwords
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("/WEB-INF/views/auth/register-form.jsp").forward(request, response);
            return;
        }

        try {
            // Check if the username already exists
            if (userDAO.getUserByUsername(username) != null) {
                request.setAttribute("error", "Username already exists");
                request.getRequestDispatcher("/WEB-INF/views/auth/register-form.jsp").forward(request, response);
                return;
            }

            // Create new user and save to the database
            User newUser = new User(0, username, email, password);
            userDAO.createUser(newUser);

            // Redirect to login page after successful registration
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
