package com.swp391.QuizSytem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "QUIZ")
@Data
public class Quiz {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "LESSION_ID")
    @JsonIgnore
    private Lession lession;
    @Column(name = "NUMBER_OF_QUESTIONS")
    private Long numberOfQuestions;
    @Column(name = "CORRECT_ANSWERS")
    private Long correctAnswers;
    @Column(name = "FINISHED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishedDatetime;
    @OneToOne
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private User user;
    @Column(name = "CREATE_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDatetime;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quiz",cascade = CascadeType.ALL)
    private List<MultipleChoiceQuestion> multipleChoiceQuestions;


}
