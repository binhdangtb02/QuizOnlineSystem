package com.swp391.QuizSytem.service;

import com.swp391.QuizSytem.entity.User;
import com.swp391.QuizSytem.model.dto.*;

import com.swp391.QuizSytem.response.RegularResponse;
import com.swp391.QuizSytem.response.SignInResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<SignInResponse> login(UserSignInDto userSignInDto);

    public ResponseEntity<RegularResponse> register(UserSignUpDto userSignUpDto);

    public ResponseEntity<RegularResponse> changePassword(UserChangePassDto userChangePassDto);

    public ResponseEntity<RegularResponse> delete(String username);

    public ResponseEntity<RegularResponse> update(String username, UpdateUserDto updateUserDto);
}
