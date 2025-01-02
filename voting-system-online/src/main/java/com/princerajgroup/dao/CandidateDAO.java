package com.princerajgroup.dao;

import com.princerajgroup.model.Candidate;
import com.princerajgroup.jdbc.DatabaseConnection; // Import the DatabaseConnection class
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO {
    
    // SQL Queries
    private static final String INSERT_CANDIDATE = "INSERT INTO candidates (name, logoUrl) VALUES (?, ?)";
    private static final String SELECT_CANDIDATE_BY_ID = "SELECT * FROM candidates WHERE id = ?";
    private static final String SELECT_ALL_CANDIDATES = "SELECT * FROM candidates";
    private static final String UPDATE_CANDIDATE = "UPDATE candidates SET name = ?, logoUrl = ? WHERE id = ?";
    private static final String DELETE_CANDIDATE = "DELETE FROM candidates WHERE id = ?";

    // Method to get a database connection using the DatabaseConnection class
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();  // Use the static method to get the connection
    }

    // Create a new candidate
    public void createCandidate(Candidate candidate) throws SQLException {
        String query = INSERT_CANDIDATE;

        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, candidate.getName());
            stmt.setString(2, candidate.getLogoUrl());
            stmt.executeUpdate();
        }
    }

    // Get a candidate by ID
    public Candidate getCandidateById(int id) throws SQLException {
        String query = SELECT_CANDIDATE_BY_ID;

        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Candidate(rs.getInt("id"), rs.getString("name"), rs.getString("logoUrl"));
                }
            }
        }
        return null;  // Return null if no candidate found
    }

    // Get all candidates
    public List<Candidate> getAllCandidates() throws SQLException {
        List<Candidate> candidates = new ArrayList<>();
        String query = SELECT_ALL_CANDIDATES;

        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                candidates.add(new Candidate(rs.getInt("id"), rs.getString("name"), rs.getString("logoUrl")));
            }
        }
        return candidates;
    }

    // Update a candidate's information
    public void updateCandidate(Candidate candidate) throws SQLException {
        String query = UPDATE_CANDIDATE;

        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, candidate.getName());
            stmt.setString(2, candidate.getLogoUrl());
            stmt.setInt(3, candidate.getId());
            stmt.executeUpdate();
        }
    }

    // Delete a candidate by ID
    public void deleteCandidate(int id) throws SQLException {
        String query = DELETE_CANDIDATE;

        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
