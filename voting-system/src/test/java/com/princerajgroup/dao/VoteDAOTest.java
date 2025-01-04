package com.princerajgroup.dao;

import com.princerajgroup.model.Vote;
import org.junit.jupiter.api.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.*;

class VoteDAOTest {
    private VoteDAO voteDAO;
    private Vote testVote;
    private UserDAO userDAO;
    private CandidateDAO candidateDAO;

    @BeforeEach
    void setUp() throws SQLException {
        voteDAO = new VoteDAO();
        userDAO = new UserDAO();
        candidateDAO = new CandidateDAO();
        
        // Create test user and candidate first
        testVote = new Vote(0, 1, 1, Timestamp.from(Instant.now()));
    }

    @Test
    void testCreateVote() throws SQLException {
        voteDAO.createVote(testVote);
        assertNotNull(voteDAO.getVoteById(testVote.getId()));
    }

    @Test
    void testGetVoteById() throws SQLException {
        voteDAO.createVote(testVote);
        Vote retrieved = voteDAO.getVoteById(testVote.getId());
        assertEquals(testVote.getUserId(), retrieved.getUserId());
    }

    @Test
    void testGetAllVotes() throws SQLException {
        voteDAO.createVote(testVote);
        assertFalse(voteDAO.getAllVotes().isEmpty());
    }

    @Test
    void testDeleteVote() throws SQLException {
        voteDAO.createVote(testVote);
        voteDAO.deleteVote(testVote.getId());
        assertNull(voteDAO.getVoteById(testVote.getId()));
    }

    @Test
    void testCreateVoteWithInvalidUser() {
        Vote invalidVote = new Vote(0, -1, 1, Timestamp.from(Instant.now()));
        assertThrows(SQLException.class, () -> voteDAO.createVote(invalidVote));
    }

    @Test
    void testCreateVoteWithInvalidCandidate() {
        Vote invalidVote = new Vote(0, 1, -1, Timestamp.from(Instant.now()));
        assertThrows(SQLException.class, () -> voteDAO.createVote(invalidVote));
    }
}