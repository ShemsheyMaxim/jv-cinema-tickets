package com.opera.service;

import com.opera.model.PerformanceSession;
import com.opera.model.ShoppingCart;
import com.opera.model.User;

public interface ShoppingCartService {
    void addSession(PerformanceSession performanceSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
