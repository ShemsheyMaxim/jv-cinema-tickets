package com.opera.service.impl;

import com.opera.dao.ShoppingCartDao;
import com.opera.dao.TicketDao;
import com.opera.model.PerformanceSession;
import com.opera.model.ShoppingCart;
import com.opera.model.Ticket;
import com.opera.model.User;
import com.opera.service.ShoppingCartService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartDao shoppingCartDao;
    private final TicketDao ticketDao;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, TicketDao ticketDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.ticketDao = ticketDao;
    }

    @Override
    public void addSession(PerformanceSession performanceSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setPerformanceSession(performanceSession);
        ticket.setUser(user);
        ShoppingCart userShoppingCart = shoppingCartDao.getByUser(user);
        List<Ticket> tickets = userShoppingCart.getTickets();
        tickets.add(ticket);
        ticketDao.add(ticket);
        shoppingCartDao.update(userShoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        List<Ticket> tickets = shoppingCart.getTickets();
        tickets.clear();
        shoppingCartDao.update(shoppingCart);
    }
}
