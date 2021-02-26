package com.cinema.service.impl;

import com.cinema.dao.ShoppingCartDao;
import com.cinema.dao.TicketDao;
import com.cinema.model.PerformanceSession;
import com.cinema.model.ShoppingCart;
import com.cinema.model.Ticket;
import com.cinema.model.User;
import com.cinema.service.ShoppingCartService;
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
