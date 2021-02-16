package com.cinema.service.mapper.impl;

import com.cinema.model.ShoppingCart;
import com.cinema.model.Ticket;
import com.cinema.model.User;
import com.cinema.model.dto.ShoppingCartRequestDto;
import com.cinema.model.dto.ShoppingCartResponseDto;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import com.cinema.service.mapper.ShoppingCartMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapperImpl implements ShoppingCartMapper {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartMapperImpl(UserService userService,
                                  ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public ShoppingCartResponseDto toShoppingCartDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto responseDto = new ShoppingCartResponseDto();
        responseDto.setUserId(shoppingCart.getId());
        List<Long> ticketsId = shoppingCart.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        responseDto.setTicketIds(ticketsId);
        return responseDto;
    }

    @Override
    public ShoppingCart toShoppingCartEntity(ShoppingCartRequestDto shoppingCartRequestDto) {
        User user = userService.get(shoppingCartRequestDto.getUserId());
        return shoppingCartService.getByUser(user);
    }
}
