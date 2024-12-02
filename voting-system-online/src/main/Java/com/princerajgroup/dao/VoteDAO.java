package com.princerajgroup.dao;

import com.princerajgroup.model.Vote;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoteDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/voting_system";
    private static final String USER = "voting_user";
    private static final String PASSWORD = "yourpassword";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Create a new vote
    public void createVote(Vote vote) throws SQLException {
        String query = "INSERT INTO votes (id, userId, candidateId, createdAt) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, vote.getId());
            stmt.setInt(2, vote.getUserId());
            stmt.setInt(3, vote.getCandidateId());
            stmt.setTimestamp(4, vote.getCreatedAt());
            stmt.executeUpdate();
        }
    }

    // Get a vote by ID
    public Vote getVoteById(int id) throws SQLException {
        String query = "SELECT * FROM votes WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Vote(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getInt("candidateId"),
                        rs.getTimestamp("createdAt")
                );
            }
        }
        return null;
    }

    // Get all votes
    public List<Vote> getAllVotes() throws SQLException {
        String query = "SELECT * FROM votes";
        List<Vote> votes = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                votes.add(new Vote(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getInt("candidateId"),
                        rs.getTimestamp("createdAt")
                ));
            }
        }
        return votes;
    }

    // Delete a vote by ID
    public void deleteVote(int id) throws SQLException {
        String query = "DELETE FROM votes WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
