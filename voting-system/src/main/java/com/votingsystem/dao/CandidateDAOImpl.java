package com.votingsystem.dao;

import com.votingsystem.model.Candidate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAOImpl implements CandidateDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/voting_system";
    private static final String USER = "voting_user";
    private static final String PASSWORD = "yourpassword";

    @Override
    public void addCandidate(Candidate candidate) {
        String query = "INSERT INTO candidates (name, logo_url) VALUES (?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pre = con.prepareStatement(query)) {

            pre.setString(1, candidate.getName());
            pre.setString(2, candidate.getLogoUrl());
            pre.executeUpdate();
            System.out.println("Candidate added successfully.");

        } catch (SQLException e) {
            System.err.println("Error while adding candidate: " + e.getMessage());
        }
    }

    @Override
    public void removeCandidate(int id) {
        String query = "DELETE FROM candidates WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pre = con.prepareStatement(query)) {

            pre.setInt(1, id);
            int rowsAffected = pre.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Candidate removed successfully.");
            } else {
                System.out.println("No candidate found with the given ID.");
            }

        } catch (SQLException e) {
            System.err.println("Error while removing candidate: " + e.getMessage());
        }
    }

    @Override
    public Candidate getCandidateById(int id) {
        String query = "SELECT * FROM candidates WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pre = con.prepareStatement(query)) {

            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return new Candidate(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("logo_url")
                );
            } else {
                System.out.println("No candidate found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error while fetching candidate by ID: " + e.getMessage());
        }
        return null; // Return null if candidate not found
    }

    @Override
    public void updateCandidate(Candidate candidate) {
        String query = "UPDATE candidates SET name = ?, logo_url = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pre = con.prepareStatement(query)) {

            pre.setString(1, candidate.getName());
            pre.setString(2, candidate.getLogoUrl());
            pre.setInt(3, candidate.getId());
            int rowsAffected = pre.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Candidate updated successfully.");
            } else {
                System.out.println("No candidate found with the given ID.");
            }

        } catch (SQLException e) {
            System.err.println("Error while updating candidate: " + e.getMessage());
        }
    }

    @Override
    public List<Candidate> getAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        String query = "SELECT * FROM candidates";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                candidates.add(new Candidate(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("logo_url")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error while fetching all candidates: " + e.getMessage());
        }
        return candidates;
    }
}
