package com.princerajgroup.dao;

import com.princerajgroup.model.Candidate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/voting_system";
    private static final String USER = "voting_user";
    private static final String PASSWORD = "yourpassword";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Create a new candidate
    public void createCandidate(Candidate candidate) throws SQLException {
        String query = "INSERT INTO candidates (id, name, logoUrl) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, candidate.getId());
            stmt.setString(2, candidate.getName());
            stmt.setString(3, candidate.getLogoUrl());
            stmt.executeUpdate();
        }
    }

    // Get a candidate by ID
    public Candidate getCandidateById(int id) throws SQLException {
        String query = "SELECT * FROM candidates WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Candidate(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("logoUrl")
                );
            }
        }
        return null;
    }

    // Get all candidates
    public List<Candidate> getAllCandidates() throws SQLException {
        String query = "SELECT * FROM candidates";
        List<Candidate> candidates = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                candidates.add(new Candidate(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("logoUrl")
                ));
            }
        }
        return candidates;
    }

    // Update a candidate's information
    public void updateCandidate(Candidate candidate) throws SQLException {
        String query = "UPDATE candidates SET name = ?, logoUrl = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, candidate.getName());
            stmt.setString(2, candidate.getLogoUrl());
            stmt.setInt(3, candidate.getId());
            stmt.executeUpdate();
        }
    }

    // Delete a candidate by ID
    public void deleteCandidate(int id) throws SQLException {
        String query = "DELETE FROM candidates WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
