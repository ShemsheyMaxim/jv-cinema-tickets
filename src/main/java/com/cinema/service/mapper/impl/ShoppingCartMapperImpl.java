package com.cinema.service.mapper.impl;

import com.cinema.model.ShoppingCart;
import com.cinema.model.Ticket;
import com.cinema.model.dto.ShoppingCartResponseDto;
import com.cinema.service.mapper.ShoppingCartMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapperImpl implements ShoppingCartMapper {
    @Override
    public ShoppingCartResponseDto toShoppingCartDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto responseDto = new ShoppingCartResponseDto();
        responseDto.setId(shoppingCart.getId());
        List<Long> ticketIds = shoppingCart.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        responseDto.setTicketIds(ticketIds);
        return responseDto;
    }
}
