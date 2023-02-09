package com.swp391.QuizSytem.service;

import com.swp391.QuizSytem.response.RegularResponse;
import org.springframework.http.ResponseEntity;

public interface WordService {
    public ResponseEntity<RegularResponse> getWordByLessionId(Long lessionId);
}
