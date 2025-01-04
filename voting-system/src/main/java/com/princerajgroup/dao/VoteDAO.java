package com.princerajgroup.dao;

import com.princerajgroup.model.Vote;
import com.princerajgroup.jdbc.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoteDAO {
    private static final String INSERT_VOTE = "INSERT INTO votes (userId, candidateId, createdAt) VALUES (?, ?, ?)";
    private static final String SELECT_VOTE_BY_ID = "SELECT * FROM votes WHERE id = ?";
    private static final String SELECT_ALL_VOTES = "SELECT * FROM votes";
    private static final String DELETE_VOTE = "DELETE FROM votes WHERE id = ?";

    public void createVote(Vote vote) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_VOTE)) {
            stmt.setInt(1, vote.getUserId());
            stmt.setInt(2, vote.getCandidateId());
            stmt.setTimestamp(3, vote.getCreatedAt());
            stmt.executeUpdate();
        }
    }

    public Vote getVoteById(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_VOTE_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Vote(rs.getInt("id"), rs.getInt("userId"), 
                                  rs.getInt("candidateId"), rs.getTimestamp("createdAt"));
                }
            }
        }
        return null;
    }

    public List<Vote> getAllVotes() throws SQLException {
        List<Vote> votes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_VOTES);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                votes.add(new Vote(rs.getInt("id"), rs.getInt("userId"),
                                 rs.getInt("candidateId"), rs.getTimestamp("createdAt")));
            }
        }
        return votes;
    }

    public void deleteVote(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_VOTE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}