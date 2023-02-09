package com.swp391.QuizSytem.service;

import com.swp391.QuizSytem.entity.Lession;
import com.swp391.QuizSytem.entity.User;
import com.swp391.QuizSytem.entity.Word;
import com.swp391.QuizSytem.mapper.UserMapper;
import com.swp391.QuizSytem.repository.LessionRepository;
import com.swp391.QuizSytem.repository.UserRepository;
import com.swp391.QuizSytem.repository.WordRepository;
import com.swp391.QuizSytem.response.RegularResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class LessionServiceImpl implements LessionService{
    @Autowired
    private LessionRepository lessionRepository;
    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseEntity<RegularResponse> getLstLessionByUserId(Long  userId) {
        List<Lession> lessions = lessionRepository.findByUserId(userId);
        return ResponseEntity.ok().body(new RegularResponse(
                "OK",
               lessions,
                "Query successfully"
        ));
    }

    @Override
        @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<RegularResponse> upsertLession(Lession lession, Long userId) {
        try{
            List<Word> words = lession.getWords();
            for(Word word  : words){
                word.setLession(lession);
                word.setCreateDatetime(new Date());
                word.setUpdateDatetime(new Date());
            }
            lession.setCreateDatetime(new Date());
            lession.setUpdateDatetime(new Date());
            User user = userRepository.findById(userId).orElseGet(() -> null );
            lession.setUser(user);
            lession = lessionRepository.save(lession);
          return ResponseEntity.ok().body(new RegularResponse(
                    "OK",
                  lession,
                    "save successfully"
            ));
        }catch (Exception e){
            return ResponseEntity.ok().body(new RegularResponse(
                    "failed",
                    null,
                    "save failed"
            ));
        }
    }
}
