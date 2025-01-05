package com.princerajgroup.servlet;

import com.princerajgroup.dao.UserDAO;
import com.princerajgroup.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Check if the user exists by username
            User user = userDAO.getUserByUsername(username);
            
            // Check if user exists and password matches
            if (user != null && user.getPassword().equals(password)) {
                // Store user in session for later use
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                
                // Redirect to success page or homepage
                response.sendRedirect(request.getContextPath() + "/welcome");
            } else {
                // If invalid credentials, show error
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}
