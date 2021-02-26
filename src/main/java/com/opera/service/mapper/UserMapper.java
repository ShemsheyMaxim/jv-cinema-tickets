package com.opera.service.mapper;

import com.opera.model.User;
import com.opera.model.dto.UserResponseDto;

public interface UserMapper {
    UserResponseDto toUserDto(User user);
}
