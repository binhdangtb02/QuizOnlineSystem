package com.swp391.QuizSytem.service;

import com.swp391.QuizSytem.entity.Quiz;
import com.swp391.QuizSytem.response.RegularResponse;
import org.springframework.http.ResponseEntity;

public interface QuizService {
    ResponseEntity<RegularResponse> generateNewQuiz(Long lessionId, Long userId);

    ResponseEntity<RegularResponse> saveTestResult(Quiz quiz, Long userId);

    ResponseEntity<RegularResponse> viewHistory(Long userId);

    ResponseEntity<RegularResponse> viewHistoryDetail(Long userId);

}
