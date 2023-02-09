package com.swp391.QuizSytem.model.dto;

import lombok.*;

@Data
public class UserChangePassDto {
    private String currentPassword;
    private String newPassword;
}

