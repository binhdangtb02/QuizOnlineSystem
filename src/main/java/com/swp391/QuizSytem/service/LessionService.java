package com.swp391.QuizSytem.service;

import com.swp391.QuizSytem.entity.Lession;
import com.swp391.QuizSytem.entity.Word;
import com.swp391.QuizSytem.response.RegularResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LessionService {
    public ResponseEntity<RegularResponse> getLstLessionByUserId(Long userId);
    public ResponseEntity<RegularResponse> upsertLession(Lession lession, Long userId);
}
