package com.ali.secretsmanager.controller;

import com.ali.secretsmanager.dto.LoginRequestDto;
import com.ali.secretsmanager.dto.UserRegisterRequestDto;
import com.ali.secretsmanager.entity.User;
import com.ali.secretsmanager.service.UserService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody UserRegisterRequestDto requestDto) {
        User user = User.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .passwordHash(requestDto.getPasswordHash())
                .build();

        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User loginUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return userService.loginUser(
                loginRequestDto.getEmail(),
                loginRequestDto.getPasswordHash()
        );
    }
}