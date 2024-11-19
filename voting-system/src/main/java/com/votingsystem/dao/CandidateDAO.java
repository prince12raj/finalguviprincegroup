package com.votingsystem.dao;

import java.util.List;

import com.votingsystem.model.Candidate;

public interface CandidateDAO {
    void addCandidate(Candidate candidate);
    void removeCandidate(int id);
    Candidate getCandidateById(int id);
    void updateCandidate(Candidate candidate);
    List<Candidate> getAllCandidates();
}
