package com.swp391.QuizSytem.model.dto;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private Long age;
    private String username;
    private Set<String> listRole;
    private Date createdAt;
    private boolean isActive;
}
