package com.princerajgroup.servlet;

import com.princerajgroup.dao.CandidateDAO;
import com.princerajgroup.model.Candidate;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/candidates")
public class CandidateServlet extends HttpServlet {
    private CandidateDAO candidateDAO;

    @Override
    public void init() {
        candidateDAO = new CandidateDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action == null ? "list" : action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteCandidate(request, response);
                    break;
                default:
                    listCandidates(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("insert".equals(action)) {
                insertCandidate(request, response);
            } else if ("update".equals(action)) {
                updateCandidate(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCandidates(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Candidate> candidateList = candidateDAO.getAllCandidates();
        request.setAttribute("candidateList", candidateList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/candidate-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/candidate-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Candidate existingCandidate = candidateDAO.getCandidateById(id);
        request.setAttribute("candidate", existingCandidate);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/candidate-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertCandidate(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String logoUrl = request.getParameter("logoUrl");
        Candidate newCandidate = new Candidate(0, name, logoUrl);
        candidateDAO.createCandidate(newCandidate);
        response.sendRedirect("candidates");
    }

    private void updateCandidate(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String logoUrl = request.getParameter("logoUrl");
        Candidate updatedCandidate = new Candidate(id, name, logoUrl);
        candidateDAO.updateCandidate(updatedCandidate);
        response.sendRedirect("candidates");
    }

    private void deleteCandidate(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        candidateDAO.deleteCandidate(id);
        response.sendRedirect("candidates");
    }
}
