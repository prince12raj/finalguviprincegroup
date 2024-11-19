package com.votingsystem.dao;

import java.util.List;

import com.votingsystem.model.Vote;

public interface VoteDAO {
    void castVote(int userId, int candidateId);
    List<Vote> getVotesByUserId(int userId);
    List<Vote> getVotesByCandidateId(int candidateId);
    List<Vote> getAllVotes();
}
