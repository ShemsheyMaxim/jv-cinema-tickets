package com.opera.controllers;

import com.opera.model.ShoppingCart;
import com.opera.model.User;
import com.opera.model.dto.ShoppingCartResponseDto;
import com.opera.service.PerformanceSessionService;
import com.opera.service.ShoppingCartService;
import com.opera.service.UserService;
import com.opera.service.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartMapper shoppingCartMapper;
    private final ShoppingCartService shoppingCartService;
    private final PerformanceSessionService performanceSessionService;
    private final UserService userService;

    @Autowired
    public ShoppingCartController(ShoppingCartMapper shoppingCartMapper,
                                  ShoppingCartService shoppingCartService,
                                  PerformanceSessionService performanceSessionService,
                                  UserService userService) {
        this.shoppingCartMapper = shoppingCartMapper;
        this.shoppingCartService = shoppingCartService;
        this.performanceSessionService = performanceSessionService;
        this.userService = userService;
    }

    @PostMapping("/performance-sessions")
    public void addPerformanceSession(Authentication authentication,
                                      @RequestParam Long performanceSessionId) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String email = principal.getUsername();
        shoppingCartService.addSession(performanceSessionService.get(performanceSessionId),
                userService.findByEmail(email).get());
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String email = principal.getUsername();
        User user = userService.findByEmail(email).get();
        ShoppingCart shoppingCartForUser = shoppingCartService.getByUser(user);
        return shoppingCartMapper.toShoppingCartDto(shoppingCartForUser);
    }
}
