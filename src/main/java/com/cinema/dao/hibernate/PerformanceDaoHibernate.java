package com.cinema.dao.hibernate;

import com.cinema.dao.PerformanceDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.model.Performance;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PerformanceDaoHibernate implements PerformanceDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PerformanceDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Performance add(Performance performance) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(performance);
            transaction.commit();
            return performance;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert performance "
                    + performance + " to table.", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Performance> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Performance> getAllPerformanceQuery =
                    session.createQuery("SELECT p FROM Performance p", Performance.class);
            return getAllPerformanceQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all performance. ", e);
        }
    }

    @Override
    public Optional<Performance> get(Long performanceId) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Performance.class, performanceId));
        } catch (Exception e) {
            throw new DataProcessingException("Can't find performance for id: " + performanceId, e);
        }
    }
}
