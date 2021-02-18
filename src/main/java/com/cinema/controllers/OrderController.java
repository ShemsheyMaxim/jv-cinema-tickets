package com.cinema.controllers;

import com.cinema.model.ShoppingCart;
import com.cinema.model.dto.OrderResponseDto;
import com.cinema.service.OrderService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import com.cinema.service.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderMapper orderMapper, OrderService orderService,
                           ShoppingCartService shoppingCartService, UserService userService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @PostMapping("/complete")
    public void completeOrder(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String email = userDetails.getUsername();
            ShoppingCart shoppingCartByUserEmail = shoppingCartService
                    .getByUser(userService.findByEmail(email).get());
            orderService.completeOrder(shoppingCartByUserEmail);
        }
    }

    @GetMapping
    public List<OrderResponseDto> getHistoryOrdersForUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        String email = null;
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            email = userDetails.getUsername();

        }
        return orderService.getOrdersHistory(userService.findByEmail(email).get())
                .stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }
}
