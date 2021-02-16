package com.cinema.service.mapper.impl;

import com.cinema.model.Order;
import com.cinema.model.ShoppingCart;
import com.cinema.model.Ticket;
import com.cinema.model.User;
import com.cinema.model.dto.OrderRequestDto;
import com.cinema.model.dto.OrderResponseDto;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import com.cinema.service.mapper.OrderMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public OrderMapperImpl(UserService userService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public OrderResponseDto toOrderDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setUserId(order.getUser().getId());
        responseDto.setOrderDate(order.getOrderDate());
        List<Long> ticketsId = new ArrayList<>();
        List<Ticket> tickets = order.getTickets();
        for (Ticket ticket : tickets) {
            ticketsId.add(ticket.getId());
        }
        responseDto.setTicketsId(ticketsId);
        return responseDto;
    }

    @Override
    public Order toOrderEntity(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        User user = userService.get(orderRequestDto.getUserId());
        order.setUser(user);
        ShoppingCart byUser = shoppingCartService.getByUser(user);
        order.setTickets(byUser.getTickets());
        order.setOrderDate(orderRequestDto.getOrderDate());
        return order;
    }
}
