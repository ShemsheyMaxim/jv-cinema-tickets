package com.opera.service.mapper.impl;

import com.opera.model.Order;
import com.opera.model.Ticket;
import com.opera.model.dto.OrderResponseDto;
import com.opera.service.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {
    @Override
    public OrderResponseDto toOrderDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setUserId(order.getUser().getId());
        responseDto.setOrderDate(order.getOrderDate());
        List<Long> ticketIds = order.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        responseDto.setTicketIds(ticketIds);
        return responseDto;
    }
}
