package com.princerajgroup.dao;

import com.princerajgroup.model.Candidate;
import com.princerajgroup.jdbc.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO {
    private static final String INSERT_CANDIDATE = "INSERT INTO candidates (name, logoUrl) VALUES (?, ?)";
    private static final String SELECT_CANDIDATE_BY_ID = "SELECT * FROM candidates WHERE id = ?";
    private static final String SELECT_ALL_CANDIDATES = "SELECT * FROM candidates";
    private static final String UPDATE_CANDIDATE = "UPDATE candidates SET name = ?, logoUrl = ? WHERE id = ?";
    private static final String DELETE_CANDIDATE = "DELETE FROM candidates WHERE id = ?";

    public void createCandidate(Candidate candidate) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_CANDIDATE)) {
            stmt.setString(1, candidate.getName());
            stmt.setString(2, candidate.getLogoUrl());
            stmt.executeUpdate();
        }
    }

    public Candidate getCandidateById(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_CANDIDATE_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Candidate(rs.getInt("id"), rs.getString("name"), rs.getString("logoUrl"));
                }
            }
        }
        return null;
    }

    public List<Candidate> getAllCandidates() throws SQLException {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_CANDIDATES);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                candidates.add(new Candidate(rs.getInt("id"), rs.getString("name"), rs.getString("logoUrl")));
            }
        }
        return candidates;
    }

    public void updateCandidate(Candidate candidate) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_CANDIDATE)) {
            stmt.setString(1, candidate.getName());
            stmt.setString(2, candidate.getLogoUrl());
            stmt.setInt(3, candidate.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteCandidate(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_CANDIDATE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}