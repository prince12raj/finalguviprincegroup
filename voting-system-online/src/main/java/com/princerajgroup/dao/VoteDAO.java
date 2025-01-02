package com.princerajgroup.dao;

import com.princerajgroup.model.Vote;
import com.princerajgroup.jdbc.DatabaseConnection; // Import DatabaseConnection class
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoteDAO {

    // SQL Queries
    private static final String INSERT_VOTE = "INSERT INTO votes (userId, candidateId, createdAt) VALUES (?, ?, ?)";
    private static final String SELECT_VOTE_BY_ID = "SELECT * FROM votes WHERE id = ?";
    private static final String SELECT_ALL_VOTES = "SELECT * FROM votes";
    private static final String DELETE_VOTE = "DELETE FROM votes WHERE id = ?";

    // Method to get a database connection using DatabaseConnection class
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection(); // Use DatabaseConnection to get connection
    }

    // Create a new vote
    public void createVote(Vote vote) throws SQLException {
        String query = INSERT_VOTE;

        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, vote.getUserId());
            stmt.setInt(2, vote.getCandidateId());
            stmt.setTimestamp(3, vote.getCreatedAt());
            stmt.executeUpdate();
        }
    }

    // Get a vote by ID
    public Vote getVoteById(int id) throws SQLException {
        String query = SELECT_VOTE_BY_ID;

        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Vote(rs.getInt("id"), rs.getInt("userId"), rs.getInt("candidateId"),
                            rs.getTimestamp("createdAt"));
                }
            }
        }
        return null; // Return null if no vote found
    }

    // Get all votes
    public List<Vote> getAllVotes() throws SQLException {
        List<Vote> votes = new ArrayList<>();
        String query = SELECT_ALL_VOTES;

        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                votes.add(new Vote(rs.getInt("id"), rs.getInt("userId"), rs.getInt("candidateId"),
                        rs.getTimestamp("createdAt")));
            }
        }
        return votes;
    }

    // Delete a vote by ID
    public void deleteVote(int id) throws SQLException {
        String query = DELETE_VOTE;

        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
