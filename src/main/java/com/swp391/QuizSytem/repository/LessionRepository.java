package com.swp391.QuizSytem.repository;

import com.swp391.QuizSytem.entity.Lession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LessionRepository extends JpaRepository<Lession, Long> {
    List<Lession> findByUserId(Long  userId);
    Optional<Lession> findById(Long id);
}
