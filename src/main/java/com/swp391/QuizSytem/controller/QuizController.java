package com.swp391.QuizSytem.controller;

import com.swp391.QuizSytem.entity.MultipleChoiceQuestion;
import com.swp391.QuizSytem.entity.Quiz;
import com.swp391.QuizSytem.entity.User;
import com.swp391.QuizSytem.repository.UserRepository;
import com.swp391.QuizSytem.response.RegularResponse;
import com.swp391.QuizSytem.security.JwtTokenProvider;
import com.swp391.QuizSytem.service.QuizService;
import com.swp391.QuizSytem.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @GetMapping("/generate")
    public ResponseEntity<RegularResponse> generateNewQuiz(@RequestParam(name = "lessionid") Long lessionId,
                                                           @RequestHeader("Authorization") String token) {
        token = AuthenticationUtil.bearerTokenToToken(token);
        User user =  userRepository.findByUsername(jwtTokenProvider.extractUsername(token));
        return quizService.generateNewQuiz(lessionId, user.getId());
    }
    @GetMapping("/save")
    public ResponseEntity<RegularResponse> checkTestResult(@RequestBody Quiz quiz, @RequestHeader("Authorization") String token) {
        token = AuthenticationUtil.bearerTokenToToken(token);
        User user =  userRepository.findByUsername(jwtTokenProvider.extractUsername(token));
        return quizService.saveTestResult(quiz, user.getId());
    }
    @GetMapping("/history")
    public ResponseEntity<RegularResponse> viewQuizHistory(@RequestHeader("Authorization") String token) {
        token = AuthenticationUtil.bearerTokenToToken(token);
        User user =  userRepository.findByUsername(jwtTokenProvider.extractUsername(token));
        return quizService.viewHistory(user.getId());
    }
    @PostMapping("/history/detail")
    public ResponseEntity<RegularResponse> viewQuizHistoryDetail(@RequestHeader(name = "quizid") Long quizId) {
    return  quizService.viewHistoryDetail(quizId);
    }
}
