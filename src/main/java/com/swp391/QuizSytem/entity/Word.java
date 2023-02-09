package com.swp391.QuizSytem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "WORD")
@Data
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TEXT")
    private String text;
    @Column(name = "ANSWER")
    private String answer;
    @Column(name = "CREATE_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDatetime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATETIME")
    private Date updateDatetime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LESSION_ID")
    @JsonBackReference
    private Lession lession;


}
