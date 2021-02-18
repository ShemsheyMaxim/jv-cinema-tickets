package com.cinema.controllers;

import com.cinema.model.ShoppingCart;
import com.cinema.model.User;
import com.cinema.model.dto.ShoppingCartResponseDto;
import com.cinema.service.MovieSessionService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import com.cinema.service.mapper.ShoppingCartMapper;
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
    private final MovieSessionService movieSessionService;
    private final UserService userService;

    @Autowired
    public ShoppingCartController(ShoppingCartMapper shoppingCartMapper,
                                  ShoppingCartService shoppingCartService,
                                  MovieSessionService movieSessionService,
                                  UserService userService) {
        this.shoppingCartMapper = shoppingCartMapper;
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(Authentication authentication, @RequestParam Long movieSessionId) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String email = userDetails.getUsername();
            shoppingCartService.addSession(movieSessionService.get(movieSessionId),
                    userService.findByEmail(email).get());
        }
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        String email = null;
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            email = userDetails.getUsername();
        }
        User user = userService.findByEmail(email).get();
        ShoppingCart shoppingCartForUser = shoppingCartService.getByUser(user);
        return shoppingCartMapper.toShoppingCartDto(shoppingCartForUser);
    }
}
