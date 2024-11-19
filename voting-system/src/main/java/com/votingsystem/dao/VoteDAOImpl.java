package com.votingsystem.dao;

import com.votingsystem.model.Vote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoteDAOImpl implements VoteDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/voting_system";
    private static final String USER = "voting_user";
    private static final String PASSWORD = "yourpassword";

    @Override
    public void castVote(int userId, int candidateId) {
        String query = "INSERT INTO votes (user_id, candidate_id) VALUES (?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pre = con.prepareStatement(query)) {

            pre.setInt(1, userId);
            pre.setInt(2, candidateId);
            pre.executeUpdate();
            System.out.println("Vote cast successfully.");

        } catch (SQLException e) {
            System.err.println("Error casting vote: " + e.getMessage());
        }
    }

    @Override
    public List<Vote> getVotesByUserId(int userId) {
        List<Vote> votes = new ArrayList<>();
        String query = "SELECT * FROM votes WHERE user_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pre = con.prepareStatement(query)) {

            pre.setInt(1, userId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                votes.add(new Vote(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("candidate_id"),
                        rs.getTimestamp("created_at")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching votes for user " + userId + ": " + e.getMessage());
        }
        return votes;
    }

    @Override
    public List<Vote> getVotesByCandidateId(int candidateId) {
        List<Vote> votes = new ArrayList<>();
        String query = "SELECT * FROM votes WHERE candidate_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pre = con.prepareStatement(query)) {

            pre.setInt(1, candidateId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                votes.add(new Vote(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("candidate_id"),
                        rs.getTimestamp("created_at")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching votes for candidate " + candidateId + ": " + e.getMessage());
        }
        return votes;
    }

    @Override
    public List<Vote> getAllVotes() {
        List<Vote> votes = new ArrayList<>();
        String query = "SELECT * FROM votes";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                votes.add(new Vote(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("candidate_id"),
                        rs.getTimestamp("created_at")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching all votes: " + e.getMessage());
        }
        return votes;
    }
}
