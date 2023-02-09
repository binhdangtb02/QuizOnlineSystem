package com.swp391.QuizSytem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {
    private Long totalOfQuestions;
    private Long correctAnswers;
}
