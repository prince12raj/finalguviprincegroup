package com.princerajgroup.servlet;

import com.princerajgroup.dao.CandidateDAO;
import com.princerajgroup.model.Candidate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/candidate/*")
public class CandidateServlet extends HttpServlet {
    private CandidateDAO candidateDAO;

    public void init() {
        candidateDAO = new CandidateDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/list":
                    listCandidates(request, response);
                    break;
                default:
                    listCandidates(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            switch (action) {
                case "/insert":
                    insertCandidate(request, response);
                    break;
                case "/delete":
                    deleteCandidate(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/candidate/list");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCandidates(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        request.setAttribute("candidates", candidateDAO.getAllCandidates());
        request.getRequestDispatcher("/WEB-INF/views/candidate-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/candidate-form.jsp").forward(request, response);
    }

    private void insertCandidate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String logoUrl = request.getParameter("logoUrl");
        Candidate newCandidate = new Candidate(0, name, logoUrl);
        candidateDAO.createCandidate(newCandidate);
        response.sendRedirect(request.getContextPath() + "/candidate/list");
    }

    private void deleteCandidate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        candidateDAO.deleteCandidate(id);
        response.sendRedirect(request.getContextPath() + "/candidate/list");
    }
}
