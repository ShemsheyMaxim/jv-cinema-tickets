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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public void completeOrder(@RequestParam Long userId) {
        ShoppingCart shoppingCartForUser = shoppingCartService.getByUser(userService.get(userId));
        orderService.completeOrder(shoppingCartForUser);
    }

    @GetMapping
    public List<OrderResponseDto> getHistoryOrdersForUser(@RequestParam Long userId) {
        return orderService.getOrdersHistory(userService.get(userId))
                .stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }
}
