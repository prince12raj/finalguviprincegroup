package com.princerajgroup.votingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votingsystem.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
