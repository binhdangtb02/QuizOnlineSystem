package com.swp391.QuizSytem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "CHOICE")
@Data
public class Choice {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "MULTIPLE_CHOICE_QUESTION_ID")
    @JsonBackReference
    private MultipleChoiceQuestion multipleChoiceQuestion;
    @Column(name = "ANSWER")
    private String answer;


}
