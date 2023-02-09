package com.swp391.QuizSytem.service;

import com.swp391.QuizSytem.entity.*;
import com.swp391.QuizSytem.model.dto.ResultDto;
import com.swp391.QuizSytem.repository.LessionRepository;
import com.swp391.QuizSytem.repository.QuizRepository;
import com.swp391.QuizSytem.repository.UserRepository;
import com.swp391.QuizSytem.repository.WordRepository;
import com.swp391.QuizSytem.response.RegularResponse;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class QuizServiceImpl implements QuizService{
    @Autowired
    private WordRepository wordRepository;
    @Autowired
    private LessionRepository lessionRepository;
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseEntity<RegularResponse> generateNewQuiz(Long lessionId, Long userId) {

        List<Word> words = wordRepository.findByLessionId(lessionId);
        int size =  words.size();
        List<MultipleChoiceQuestion> multipleChoiceQuestions = new ArrayList<>();
        Quiz quiz = new Quiz();
        Lession lession = lessionRepository.findById(lessionId).orElseGet(null);
        User user = new User();
        user.setId(userId);
        if(lession == null){
            return ResponseEntity.ok().body(new RegularResponse(
                    "NOT FOUND",
                    null,
                    "NOT FOUND"
            ));
        }
        words.forEach(word -> {
            MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
            multipleChoiceQuestion.setCorrectAnswer(word.getAnswer());
            Choice correctChoice =  new Choice();
            correctChoice.setAnswer(word.getAnswer());
            List<Choice> choices = new ArrayList<>();
            choices.add(correctChoice);
            int count = 0;
             while(true){
                Word w = words.get((int)Math.floor(Math.random() * size));
                Choice c = new Choice();
                c.setAnswer(w.getAnswer());
                if(choices.contains(c)){
                    continue;
                }
                choices.add(c);
                count++;
                if(size <= 4){
                    if(count == size-1){
                        break;
                    }
                }
                if(count == 3){
                    break;
                }
            }
            multipleChoiceQuestion.setChoices(choices);
            multipleChoiceQuestions.add(multipleChoiceQuestion);
        });

        quiz.setLession(lession);
        quiz.setCreateDatetime(new Date());
        quiz.setMultipleChoiceQuestions(multipleChoiceQuestions);
        quiz.setUser(user);
        return ResponseEntity.ok().body(new RegularResponse("OK",
                quiz,
                "query successfully"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<RegularResponse> saveTestResult(Quiz quiz, Long userId) {
        Long correctAnswers = 0L;
        for(MultipleChoiceQuestion multipleChoiceQuestion : quiz.getMultipleChoiceQuestions()){
            if(multipleChoiceQuestion.getChoosedAnswer() != null  && multipleChoiceQuestion.getChoosedAnswer().trim().toLowerCase().equals(
                    multipleChoiceQuestion.getCorrectAnswer().trim().toLowerCase()
            )){
                correctAnswers++;
            }
            for(Choice c : multipleChoiceQuestion.getChoices()){
                c.setMultipleChoiceQuestion(multipleChoiceQuestion);
            }
            multipleChoiceQuestion.setQuiz(quiz);
        }
        Long totalQuestions = new Long(quiz.getMultipleChoiceQuestions().size());
        quiz.setCorrectAnswers(correctAnswers);
        quiz.setNumberOfQuestions(totalQuestions);
        quiz.setFinishedDatetime(new Date());
        User user = userRepository.findById(userId).orElseGet(() -> null);
        quiz.setUser(user);
        quizRepository.save(quiz);
        ResultDto resultDto = new ResultDto();
        resultDto.setCorrectAnswers(correctAnswers);
        resultDto.setTotalOfQuestions(totalQuestions);
        return ResponseEntity.ok().body(new RegularResponse("ok",
                resultDto,
                "ok"));
    }

    @Override
    public ResponseEntity<RegularResponse> viewHistory(Long userId) {
        return ResponseEntity.ok().body(new RegularResponse("Query successfully",
                quizRepository.findByUserId(userId),
                "ok"));
    }

    @Override
    public ResponseEntity<RegularResponse> viewHistoryDetail(Long userId) {
        return ResponseEntity.ok().body(new RegularResponse("Query successfully",
                quizRepository.findById(userId),
                "ok"));
    }
}
