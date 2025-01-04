package com.princerajgroup.servlet;

import com.princerajgroup.dao.VoteDAO;
import com.princerajgroup.model.Vote;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

@WebServlet("/vote/*")
public class VoteServlet extends HttpServlet {
    private VoteDAO voteDAO;

    public void init() {
        voteDAO = new VoteDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            switch (action) {
                case "/new":
                    showVoteForm(request, response);
                    break;
                case "/list":
                    listVotes(request, response);
                    break;
                default:
                    listVotes(request, response);
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
                case "/cast":
                    castVote(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/vote/list");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listVotes(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        request.setAttribute("votes", voteDAO.getAllVotes());
        request.getRequestDispatcher("/WEB-INF/views/vote-list.jsp").forward(request, response);
    }

    private void showVoteForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/vote-form.jsp").forward(request, response);
    }

    private void castVote(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int candidateId = Integer.parseInt(request.getParameter("candidateId"));
        Vote newVote = new Vote(0, userId, candidateId, Timestamp.from(Instant.now()));
        voteDAO.createVote(newVote);
        response.sendRedirect(request.getContextPath() + "/vote/list");
    }
}
