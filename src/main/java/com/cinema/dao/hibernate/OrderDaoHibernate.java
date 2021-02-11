package com.cinema.dao.hibernate;

import com.cinema.dao.OrderDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.model.Order;
import com.cinema.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoHibernate implements OrderDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order add(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert order " + order + " to table. ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> listOrderByUserQuery = session.createQuery("SELECT DISTINCT o "
                    + "FROM Order o LEFT JOIN FETCH o.tickets WHERE o.user = :user", Order.class);
            listOrderByUserQuery.setParameter("user", user);
            return listOrderByUserQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get history orders for user " + user, e);
        }
    }
}
