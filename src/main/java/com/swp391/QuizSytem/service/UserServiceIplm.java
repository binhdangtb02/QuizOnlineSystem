package com.swp391.QuizSytem.service;

import com.swp391.QuizSytem.entity.Role;
import com.swp391.QuizSytem.entity.User;
import com.swp391.QuizSytem.model.dto.*;
import com.swp391.QuizSytem.repository.RoleRepository;
import com.swp391.QuizSytem.repository.UserRepository;
import com.swp391.QuizSytem.response.RegularResponse;
import com.swp391.QuizSytem.response.SignInResponse;
import com.swp391.QuizSytem.security.CustomUserDetailService;
import com.swp391.QuizSytem.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
@AllArgsConstructor

public class UserServiceIplm implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    public ResponseEntity<SignInResponse> login(UserSignInDto userSignInDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userSignInDto.getUsername(), userSignInDto.getPassword()));
        final UserDetails user = customUserDetailService.loadUserByUsername(userSignInDto.getUsername());
        if (user != null) {
            return ResponseEntity.ok().body(new SignInResponse("Success", "Login success !", jwtTokenProvider.generateToken(user)));
        }
        return ResponseEntity.badRequest().body(new SignInResponse("Error", "Can not verify this account !", "Deny"));
    }

    @Override
    public ResponseEntity<RegularResponse> register(UserSignUpDto userSignUpDto) {
        //        Check username is duplicate in DB
        if (userRepository.findByUsername(userSignUpDto.getUsername()) != null) {
            return ResponseEntity.status(201).body(new RegularResponse("Warning", null, "Register failed because already username exist !"));
        }
//        Check role is accepted or not
        Iterator listRoleItr = userSignUpDto.getListRole().iterator();
        while (listRoleItr.hasNext()) {
            if (!roleRepository.existsByName(listRoleItr.next().toString())) {
                return ResponseEntity.status(202).body(new RegularResponse("Warning", null, "Your role invalid !"));
            }
        }
        User user = new User();
        user.setFirstName(userSignUpDto.getFirstName());
        user.setLastName(userSignUpDto.getLastName());
        user.setAge(userSignUpDto.getAge());
        user.setUsername(userSignUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(userSignUpDto.getPassword()));
        Set<Role> temp = new HashSet<Role>();
        for (String roleName : userSignUpDto.getListRole()) {
            Role roleNeedAdd = roleRepository.findByName(roleName);
            if (roleNeedAdd != null) {
                temp.add(roleNeedAdd);
            }
        }
        user.setListRole(temp);
        user.setCreatedAt(userSignUpDto.getCreatedAt());
        user.setActive(userSignUpDto.isActive());
        userRepository.save(user);
        return ResponseEntity.ok().body(new RegularResponse("Success", user, "Sign up success"));
    }

    @Override
    public ResponseEntity<RegularResponse> changePassword(UserChangePassDto userChangePassDto) {
        return null;
    }

    @Override
    public ResponseEntity<RegularResponse> delete(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(201).body(new RegularResponse("Warning", null,"Not found user !"));
        }
        user.setActive(false);
        userRepository.save(user);
        return ResponseEntity.ok().body(new RegularResponse("Success", null,"Disable account success"));
    }

    @Override
    public ResponseEntity<RegularResponse> update(String username, UpdateUserDto updateUserDto) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(201).body(new RegularResponse("Warning", null, "Not found user !"));
        }
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setAge(updateUserDto.getAge());
        userRepository.save(user);
        return ResponseEntity.ok().body(new RegularResponse("Success", user, "Update success"));
    }
}

