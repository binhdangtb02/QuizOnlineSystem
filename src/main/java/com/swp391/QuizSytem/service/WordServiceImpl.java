package com.swp391.QuizSytem.service;

import com.swp391.QuizSytem.entity.Word;
import com.swp391.QuizSytem.repository.WordRepository;
import com.swp391.QuizSytem.response.RegularResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WordServiceImpl implements WordService{
    @Autowired
    private WordRepository wordRepository;
    @Override
    public ResponseEntity<RegularResponse> getWordByLessionId(Long lessionId) {
        List<Word> listWords =   wordRepository.findByLessionId(lessionId);
        return ResponseEntity.ok().body(new RegularResponse(
                "OK",
                listWords,
                "Query successfully"
        ));

    }
}
