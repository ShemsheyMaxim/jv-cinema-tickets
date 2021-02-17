package com.cinema.service.mapper;

import com.cinema.model.Order;
import com.cinema.model.dto.OrderResponseDto;

public interface OrderMapper {
    OrderResponseDto toOrderDto(Order order);
}
