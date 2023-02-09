package com.swp391.QuizSytem.repository;

import com.swp391.QuizSytem.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Optional<Quiz> findById(Long id);
    List<Quiz> findByUserId(Long id);


}
