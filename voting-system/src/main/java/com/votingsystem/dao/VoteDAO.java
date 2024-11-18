package com.votingsystem.dao;

import com.votingsystem.model.Vote;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class VoteDAO {
    private EntityManagerFactory entityManagerFactory;

    public VoteDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("VotingSystemPU");
    }

    public void saveVote(Vote vote) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(vote);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public List<Vote> getAllVotes() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT v FROM Vote v", Vote.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    public List<Vote> getVotesByCandidateId(Long candidateId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT v FROM Vote v WHERE v.candidate.id = :candidateId", Vote.class)
                                .setParameter("candidateId", candidateId)
                                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public List<Vote> getVotesByUserId(Long userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT v FROM Vote v WHERE v.user.id = :userId", Vote.class)
                                .setParameter("userId", userId)
                                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
