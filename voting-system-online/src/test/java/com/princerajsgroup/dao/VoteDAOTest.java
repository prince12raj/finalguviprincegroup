package com.princerajsgroup.dao;

import com.princerajgroup.dao.VoteDAO;
import com.princerajgroup.model.Vote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VoteDAOTest {

    private VoteDAO voteDAO;
    private Vote testVote;

    @BeforeEach
    public void setUp() {
        voteDAO = new VoteDAO();
        testVote = new Vote(1, 1, 1, new Timestamp(System.currentTimeMillis())); // User 1 votes for Candidate 1
    }

    @Test
    public void testCreateVote() throws SQLException {
        voteDAO.createVote(testVote);
        Vote fetchedVote = voteDAO.getVoteById(1);
        assertNotNull(fetchedVote);
        assertEquals(testVote.getUserId(), fetchedVote.getUserId());
        assertEquals(testVote.getCandidateId(), fetchedVote.getCandidateId());
    }

    @Test
    public void testGetVoteById() throws SQLException {
        voteDAO.createVote(testVote);
        Vote fetchedVote = voteDAO.getVoteById(1);
        assertNotNull(fetchedVote);
        assertEquals(testVote.getUserId(), fetchedVote.getUserId());
        assertEquals(testVote.getCandidateId(), fetchedVote.getCandidateId());
    }

    @Test
    public void testGetAllVotes() throws SQLException {
        voteDAO.createVote(testVote);
        List<Vote> votes = voteDAO.getAllVotes();
        assertTrue(votes.size() > 0);
    }

    @Test
    public void testDeleteVote() throws SQLException {
        voteDAO.createVote(testVote);
        voteDAO.deleteVote(1);
        Vote deletedVote = voteDAO.getVoteById(1);
        assertNull(deletedVote);
    }

    @Test
    public void testCreateMultipleVotes() throws SQLException {
        Vote secondVote = new Vote(2, 1, 2, new Timestamp(System.currentTimeMillis()));
        voteDAO.createVote(testVote);
        voteDAO.createVote(secondVote);

        List<Vote> votes = voteDAO.getAllVotes();
        assertTrue(votes.size() >= 2);
    }

    @Test
    public void testCreateVoteWithTimestamp() throws SQLException {
        Vote voteWithTimestamp = new Vote(3, 2, 1, new Timestamp(System.currentTimeMillis()));
        voteDAO.createVote(voteWithTimestamp);
        Vote fetchedVote = voteDAO.getVoteById(3);
        assertNotNull(fetchedVote);
        assertEquals(voteWithTimestamp.getCreatedAt(), fetchedVote.getCreatedAt());
    }
}
