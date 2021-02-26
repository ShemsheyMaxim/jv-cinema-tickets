package com.opera.service.mapper;

import com.opera.model.Order;
import com.opera.model.dto.OrderResponseDto;

public interface OrderMapper {
    OrderResponseDto toOrderDto(Order order);
}
