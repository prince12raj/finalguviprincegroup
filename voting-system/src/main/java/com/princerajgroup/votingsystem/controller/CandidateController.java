package com.princerajgroup.votingsystem.controller;

import com.votingsystem.dao.CandidateDAO;
import com.votingsystem.dao.CandidateDAOImpl;
import com.votingsystem.model.Candidate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateDAO candidateDAO;

    // Constructor to inject CandidateDAO implementation
    public CandidateController() {
        this.candidateDAO = new CandidateDAOImpl();  // Use dependency injection or a service layer in real-world applications
    }

    // Endpoint to add a new candidate
    @PostMapping("/add")
    public String addCandidate(@RequestBody Candidate candidate) {
        try {
            candidateDAO.addCandidate(candidate);
            return "Candidate added successfully!";
        } catch (Exception e) {
            return "Error adding candidate: " + e.getMessage();
        }
    }

    // Endpoint to update an existing candidate
    @PutMapping("/update/{id}")
    public String updateCandidate(@PathVariable int id, @RequestBody Candidate candidate) {
        try {
            candidate.setId(id);  // Ensure the ID is set correctly
            candidateDAO.updateCandidate(candidate);
            return "Candidate updated successfully!";
        } catch (Exception e) {
            return "Error updating candidate: " + e.getMessage();
        }
    }

    // Endpoint to delete a candidate by ID
    @DeleteMapping("/delete/{id}")
    public String removeCandidate(@PathVariable int id) {
        try {
            candidateDAO.removeCandidate(id);
            return "Candidate removed successfully!";
        } catch (Exception e) {
            return "Error removing candidate: " + e.getMessage();
        }
    }

    // Endpoint to get all candidates
    @GetMapping("/")
    public List<Candidate> getAllCandidates() {
        return candidateDAO.getAllCandidates();
    }

    // Endpoint to get a candidate by ID
    @GetMapping("/{id}")
    public Candidate getCandidateById(@PathVariable int id) {
        return candidateDAO.getCandidateById(id);
    }
}
