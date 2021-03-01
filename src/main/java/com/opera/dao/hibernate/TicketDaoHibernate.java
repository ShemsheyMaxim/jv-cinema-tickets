package com.opera.dao.hibernate;

import com.opera.dao.TicketDao;
import com.opera.exception.DataProcessingException;
import com.opera.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoHibernate implements TicketDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public TicketDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Ticket add(Ticket ticket) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert ticket "
                    + ticket + " to table. ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
