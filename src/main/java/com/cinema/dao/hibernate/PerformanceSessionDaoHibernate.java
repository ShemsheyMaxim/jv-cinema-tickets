package com.cinema.dao.hibernate;

import com.cinema.dao.PerformanceSessionDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.model.PerformanceSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PerformanceSessionDaoHibernate implements PerformanceSessionDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PerformanceSessionDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PerformanceSession add(PerformanceSession performanceSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(performanceSession);
            transaction.commit();
            return performanceSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert performance session "
                    + performanceSession + " to table. ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<PerformanceSession> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            PerformanceSession performanceSession = session.get(PerformanceSession.class, id);
            return Optional.ofNullable(performanceSession);
        } catch (Exception e) {
            throw new DataProcessingException("Can't find performance session for id: " + id, e);
        }
    }

    @Override
    public List<PerformanceSession> findAvailableSessions(Long performanceId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<PerformanceSession> sessionsOfPerformanceQuery =
                    session.createQuery("SELECT ps FROM PerformanceSession ps "
                            + "WHERE ps.performance.id = :performanceId "
                            + "AND DATE_FORMAT(ps.showTime, '%Y-%m-%d') "
                            + "= :date", PerformanceSession.class);
            sessionsOfPerformanceQuery.setParameter("performanceId", performanceId);
            sessionsOfPerformanceQuery.setParameter("date",
                    date.format(DateTimeFormatter.ISO_LOCAL_DATE));
            return sessionsOfPerformanceQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving "
                    + "all available sessions of performance for id" + performanceId
                    + " and date " + date, e);
        }
    }

    @Override
    public PerformanceSession update(PerformanceSession performanceSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(performanceSession);
            transaction.commit();
            return performanceSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update performance session for id "
                    + performanceSession.getId(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public PerformanceSession delete(Long performanceSessionId) {
        try (Session session = sessionFactory.openSession()) {
            Query<PerformanceSession> deletePerformanceSessionQuery =
                    session.createQuery("DELETE FROM PerformanceSession ps "
                            + "WHERE ps.id = :performanceSessionId", PerformanceSession.class);
            deletePerformanceSessionQuery.setParameter("performanceSessionId",
                    performanceSessionId);
            return deletePerformanceSessionQuery.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't delete performance session for id "
                    + performanceSessionId, e);
        }
    }
}
