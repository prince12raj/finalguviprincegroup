package com.princerajgroup.votingsystem.controller;

import com.votingsystem.dao.VoteDAO;
import com.votingsystem.dao.VoteDAOImpl;
import com.votingsystem.model.Vote;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteController {

    private final VoteDAO voteDAO;

    // Constructor to inject VoteDAO implementation
    public VoteController() {
        this.voteDAO = new VoteDAOImpl();  // Use dependency injection or a service layer in real-world applications
    }

    // Endpoint to cast a vote
    @PostMapping("/cast")
    public String castVote(@RequestParam int userId, @RequestParam int candidateId) {
        try {
            voteDAO.castVote(userId, candidateId);
            return "Vote cast successfully!";
        } catch (Exception e) {
            return "Error casting vote: " + e.getMessage();
        }
    }

    // Endpoint to get all votes for a specific user
    @GetMapping("/user/{userId}")
    public List<Vote> getVotesByUserId(@PathVariable int userId) {
        return voteDAO.getVotesByUserId(userId);
    }

    // Endpoint to get all votes for a specific candidate
    @GetMapping("/candidate/{candidateId}")
    public List<Vote> getVotesByCandidateId(@PathVariable int candidateId) {
        return voteDAO.getVotesByCandidateId(candidateId);
    }

    // Endpoint to get all votes (Admin or general query)
    @GetMapping("/")
    public List<Vote> getAllVotes() {
        return voteDAO.getAllVotes();
    }
}
