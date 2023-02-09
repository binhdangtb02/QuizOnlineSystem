package com.swp391.QuizSytem.repository;

import com.swp391.QuizSytem.entity.Word;
import com.swp391.QuizSytem.response.RegularResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findByLessionId(Long lessionId);
}
