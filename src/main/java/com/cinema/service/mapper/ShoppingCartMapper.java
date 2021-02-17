package com.cinema.service.mapper;

import com.cinema.model.ShoppingCart;
import com.cinema.model.dto.ShoppingCartResponseDto;

public interface ShoppingCartMapper {
    ShoppingCartResponseDto toShoppingCartDto(ShoppingCart shoppingCart);
}
