package com.swp391.QuizSytem.controller;

import com.swp391.QuizSytem.common.Convert;
import com.swp391.QuizSytem.model.dto.UpdateUserDto;
import com.swp391.QuizSytem.model.dto.UserSignInDto;
import com.swp391.QuizSytem.model.dto.UserSignUpDto;
import com.swp391.QuizSytem.response.RegularResponse;
import com.swp391.QuizSytem.response.SignInResponse;
import com.swp391.QuizSytem.security.JwtTokenProvider;
import com.swp391.QuizSytem.service.UserServiceIplm;
import com.swp391.QuizSytem.util.AuthenticationUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserServiceIplm userServiceIplm;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/auth/register")
    public ResponseEntity<RegularResponse> register(@RequestBody @Valid UserSignUpDto userSignUpDto) {
        return userServiceIplm.register(userSignUpDto);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<SignInResponse> login(@RequestBody UserSignInDto userSignInDto) {
        return userServiceIplm.login(userSignInDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/delete/{userName}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token, @PathVariable String username) {
        token = AuthenticationUtil.bearerTokenToToken(token);
        if (!jwtTokenProvider.extractUsername(token).equals(username)) {
            return ResponseEntity.badRequest().body(new RegularResponse("Error", null, "Deny the request"));
        }
        return userServiceIplm.delete(username);
    }

    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('CLIENT')")
    @PutMapping("/update/{username}")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String token, @PathVariable String username, @RequestBody UpdateUserDto updateUserDto) {
        token = AuthenticationUtil.bearerTokenToToken(token);
        if (!jwtTokenProvider.extractUsername(token).equals(username)) {
            return ResponseEntity.badRequest().body(new RegularResponse("Error", null, "Deny the request"));
        }
        return userServiceIplm.update(username, updateUserDto);
    }
}
