package com.webapp.repository;

import com.webapp.models.TrainingSession;
import com.webapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {
    List<TrainingSession> findByUser(User user);

    boolean existsByName(String name);
}
