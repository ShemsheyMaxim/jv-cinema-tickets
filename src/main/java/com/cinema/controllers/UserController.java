package com.cinema.controllers;

import com.cinema.model.User;
import com.cinema.model.dto.UserResponseDto;
import com.cinema.service.UserService;
import com.cinema.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/by-email")
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        User userByEmail = userService.findByEmail(email).orElseThrow(() ->
                new RuntimeException("User for email " + email + " not found."));
        return userMapper.toUserDto(userByEmail);
    }
}
