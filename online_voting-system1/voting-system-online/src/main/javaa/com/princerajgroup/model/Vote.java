package com.princerajgroup.model;

import java.sql.Timestamp;

public class Vote {
    private int id;
    private int userId;
    private int candidateId;
    private Timestamp createdAt;

    public Vote(int id, int userId, int candidateId, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.candidateId = candidateId;
        this.createdAt = createdAt;
    }

    public Vote() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
