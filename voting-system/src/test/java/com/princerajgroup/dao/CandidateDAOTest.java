package com.princerajgroup.dao;

import com.princerajgroup.model.Candidate;
import org.junit.jupiter.api.*;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class CandidateDAOTest {
    private CandidateDAO candidateDAO;
    private Candidate testCandidate;

    @BeforeEach
    void setUp() {
        candidateDAO = new CandidateDAO();
        testCandidate = new Candidate(0, "Test Candidate", "http://example.com/logo.png");
    }

    @Test
    void testCreateCandidate() throws SQLException {
        candidateDAO.createCandidate(testCandidate);
        assertNotNull(candidateDAO.getCandidateById(testCandidate.getId()));
    }

    @Test
    void testGetCandidateById() throws SQLException {
        candidateDAO.createCandidate(testCandidate);
        Candidate retrieved = candidateDAO.getCandidateById(testCandidate.getId());
        assertEquals(testCandidate.getName(), retrieved.getName());
    }

    @Test
    void testUpdateCandidate() throws SQLException {
        candidateDAO.createCandidate(testCandidate);
        testCandidate.setName("Updated Name");
        candidateDAO.updateCandidate(testCandidate);
        Candidate updated = candidateDAO.getCandidateById(testCandidate.getId());
        assertEquals("Updated Name", updated.getName());
    }

    @Test
    void testDeleteCandidate() throws SQLException {
        candidateDAO.createCandidate(testCandidate);
        candidateDAO.deleteCandidate(testCandidate.getId());
        assertNull(candidateDAO.getCandidateById(testCandidate.getId()));
    }

    @Test
    void testGetAllCandidates() throws SQLException {
        candidateDAO.createCandidate(testCandidate);
        assertFalse(candidateDAO.getAllCandidates().isEmpty());
    }

    @Test
    void testCreateCandidateWithInvalidData() {
        Candidate invalidCandidate = new Candidate(0, "", "");
        assertThrows(SQLException.class, () -> candidateDAO.createCandidate(invalidCandidate));
    }
}