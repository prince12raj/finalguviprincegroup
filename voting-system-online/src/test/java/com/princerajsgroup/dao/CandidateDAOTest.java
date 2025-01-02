package com.princerajsgroup.dao;

import com.princerajgroup.dao.CandidateDAO;
import com.princerajgroup.model.Candidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CandidateDAOTest {

    private CandidateDAO candidateDAO;
    private Candidate testCandidate;

    @BeforeEach
    public void setUp() {
        candidateDAO = new CandidateDAO();
        testCandidate = new Candidate(1, "Test Candidate", "logoUrl");
    }

    @Test
    public void testCreateCandidate() throws SQLException {
        candidateDAO.createCandidate(testCandidate);
        Candidate fetchedCandidate = candidateDAO.getCandidateById(1);
        assertNotNull(fetchedCandidate);
        assertEquals(testCandidate.getName(), fetchedCandidate.getName());
    }

    @Test
    public void testGetCandidateById() throws SQLException {
        candidateDAO.createCandidate(testCandidate);
        Candidate fetchedCandidate = candidateDAO.getCandidateById(1);
        assertNotNull(fetchedCandidate);
        assertEquals(testCandidate.getName(), fetchedCandidate.getName());
    }

    @Test
    public void testGetAllCandidates() throws SQLException {
        candidateDAO.createCandidate(testCandidate);
        List<Candidate> candidates = candidateDAO.getAllCandidates();
        assertTrue(candidates.size() > 0);
    }

    @Test
    public void testUpdateCandidate() throws SQLException {
        candidateDAO.createCandidate(testCandidate);
        testCandidate.setName("Updated Candidate");
        candidateDAO.updateCandidate(testCandidate);

        Candidate updatedCandidate = candidateDAO.getCandidateById(1);
        assertEquals("Updated Candidate", updatedCandidate.getName());
    }

    @Test
    public void testDeleteCandidate() throws SQLException {
        candidateDAO.createCandidate(testCandidate);
        candidateDAO.deleteCandidate(1);
        Candidate deletedCandidate = candidateDAO.getCandidateById(1);
        assertNull(deletedCandidate);
    }

    @Test
    public void testCreateMultipleCandidates() throws SQLException {
        Candidate candidate2 = new Candidate(2, "Candidate Two", "logoUrl2");
        candidateDAO.createCandidate(testCandidate);
        candidateDAO.createCandidate(candidate2);
        
        List<Candidate> candidates = candidateDAO.getAllCandidates();
        assertTrue(candidates.size() >= 2);
    }
}
