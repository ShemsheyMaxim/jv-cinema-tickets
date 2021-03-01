package com.opera.controllers;

import com.opera.model.User;
import com.opera.model.dto.UserResponseDto;
import com.opera.service.UserService;
import com.opera.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
