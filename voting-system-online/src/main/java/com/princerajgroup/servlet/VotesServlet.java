package com.princerajgroup.servlet;

import com.princerajgroup.dao.VoteDAO;
import com.princerajgroup.model.Vote;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/votes")
public class VotesServlet extends HttpServlet {
    private VoteDAO voteDAO;

    @Override
    public void init() {
        voteDAO = new VoteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action == null ? "list" : action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "delete":
                    deleteVote(request, response);
                    break;
                default:
                    listVotes(request, response);
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
                insertVote(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listVotes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Vote> voteList = voteDAO.getAllVotes();
        request.setAttribute("voteList", voteList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/vote-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/vote-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertVote(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int candidateId = Integer.parseInt(request.getParameter("candidateId"));
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());

        Vote newVote = new Vote(0, userId, candidateId, createdAt);
        voteDAO.createVote(newVote);
        response.sendRedirect("votes");
    }

    private void deleteVote(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        voteDAO.deleteVote(id);
        response.sendRedirect("votes");
    }
}
