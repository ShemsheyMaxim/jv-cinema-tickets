package com.cinema.controllers;

import com.cinema.model.User;
import com.cinema.model.dto.UserRequestDto;
import com.cinema.service.AuthenticationService;
import com.cinema.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthenticationController {
    private final UserMapper userMapper;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(UserMapper userMapper,
                                    AuthenticationService authenticationService) {
        this.userMapper = userMapper;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserRequestDto userRequestDto) {
        User user = userMapper.toUserEntity(userRequestDto);
        authenticationService.register(user.getEmail(), user.getPassword());
    }
}
