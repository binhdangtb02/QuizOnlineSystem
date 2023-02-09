package com.swp391.QuizSytem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MULTIPLE_CHOICE_QUESTION")
@Data
public class MultipleChoiceQuestion {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUIZ_ID")
    @JsonIgnore
    private Quiz quiz;
    private String correctAnswer;
    private String choosedAnswer;
    @OneToMany(mappedBy = "multipleChoiceQuestion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Choice> choices;




}
