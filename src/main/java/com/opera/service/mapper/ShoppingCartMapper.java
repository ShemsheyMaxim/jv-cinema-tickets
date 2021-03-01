package com.opera.service.mapper;

import com.opera.model.ShoppingCart;
import com.opera.model.dto.ShoppingCartResponseDto;

public interface ShoppingCartMapper {
    ShoppingCartResponseDto toShoppingCartDto(ShoppingCart shoppingCart);
}
