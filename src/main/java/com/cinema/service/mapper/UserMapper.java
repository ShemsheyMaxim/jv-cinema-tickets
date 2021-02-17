package com.cinema.service.mapper;

import com.cinema.model.User;
import com.cinema.model.dto.UserResponseDto;

public interface UserMapper {
    UserResponseDto toUserDto(User user);
}
