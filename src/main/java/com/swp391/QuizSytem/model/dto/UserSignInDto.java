package com.swp391.QuizSytem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserSignInDto {
    private String username;
    private String password;
}
