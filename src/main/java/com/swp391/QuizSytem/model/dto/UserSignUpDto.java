package com.swp391.QuizSytem.model.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Data
public class UserSignUpDto {
    @NotEmpty(message = "First name can not empty !")
    private String firstName;
    private String lastName;
    private Long age;
    private String username;
    private String password;
    private Set<String> listRole;
    private Date createdAt;
    private boolean isActive;

    private Date getNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date now = new Date();
        String dateNow = sdf.format(now);
        try {
            return sdf.parse(dateNow);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public UserSignUpDto(String firstName, String lastName, Long age, String username, String password, Set<String> listRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.password = password;
        this.listRole = listRole;
        this.createdAt = getNow();
        this.isActive = true;
    }
}
