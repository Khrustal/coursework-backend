package com.webapp.repository;

import com.webapp.models.User;
import com.webapp.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findByUser(User getRequest);

    Boolean existsByOriginal(String original);

    Optional<Word> findByOriginal(String original);

    boolean existsByOriginalAndUserId(String original, Long userId);
}
