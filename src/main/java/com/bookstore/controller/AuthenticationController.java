package com.bookstore.controller;

import com.bookstore.dto.user.UserRegistrationRequestDto;
import com.bookstore.dto.user.UserResponseDto;
import com.bookstore.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserServiceImpl userService;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return userService.register(requestDto);
    }
}
