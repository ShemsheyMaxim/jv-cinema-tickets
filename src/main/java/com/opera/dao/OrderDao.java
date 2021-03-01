package com.opera.dao;

import com.opera.model.Order;
import com.opera.model.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrdersHistory(User user);
}
