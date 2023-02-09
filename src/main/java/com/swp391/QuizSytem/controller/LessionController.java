package com.swp391.QuizSytem.controller;

import com.swp391.QuizSytem.entity.Lession;
import com.swp391.QuizSytem.entity.User;
import com.swp391.QuizSytem.repository.UserRepository;
import com.swp391.QuizSytem.repository.WordRepository;
import com.swp391.QuizSytem.response.RegularResponse;
import com.swp391.QuizSytem.security.JwtTokenProvider;
import com.swp391.QuizSytem.service.LessionService;
import com.swp391.QuizSytem.service.WordService;
import com.swp391.QuizSytem.service.WordServiceImpl;
import com.swp391.QuizSytem.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lession")
public class LessionController {
    @Autowired
    private LessionService lessionService;
    @Autowired
    private WordService wordService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/view")
    public ResponseEntity<RegularResponse> getLstLessionByUserId(@RequestHeader("Authorization") String token){
        token = AuthenticationUtil.bearerTokenToToken(token);
        User user =  userRepository.findByUsername(jwtTokenProvider.extractUsername(token));

        return lessionService.getLstLessionByUserId(user.getId());
    }
    @GetMapping("/viewword/{lessionId}")
    public ResponseEntity<RegularResponse> getLstWordByLessionId(@PathVariable Long lessionId){
        return wordService.getWordByLessionId(lessionId);
    }

    @PostMapping("/upsert")
    public ResponseEntity<RegularResponse> upsertNewLession(@RequestBody Lession lession, @RequestHeader("Authorization") String token){
        token = AuthenticationUtil.bearerTokenToToken(token);
        User user =  userRepository.findByUsername(jwtTokenProvider.extractUsername(token));
        return lessionService.upsertLession(lession, user.getId());
    }

}
