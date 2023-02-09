package com.swp391.QuizSytem.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class LessionDto {
    private Long id;
    private String name;
    private Date createDatetime;
    private Date updateDatetime;
    private UserDto userDto;
}
