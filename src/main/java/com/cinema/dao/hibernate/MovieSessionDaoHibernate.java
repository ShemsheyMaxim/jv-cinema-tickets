package com.cinema.dao.hibernate;

import com.cinema.dao.MovieSessionDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.model.MovieSession;
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
public class MovieSessionDaoHibernate implements MovieSessionDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public MovieSessionDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert movieSession "
                    + movieSession + " to table. ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<MovieSession> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            MovieSession movieSession = session.get(MovieSession.class, id);
            return Optional.ofNullable(movieSession);
        } catch (Exception e) {
            throw new DataProcessingException("Can't find movie session for id: " + id, e);
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> sessionsOfMovieQuery =
                    session.createQuery("SELECT ms FROM MovieSession ms "
                            + "WHERE ms.movie.id = :movieId "
                            + "AND DATE_FORMAT(ms.showTime, '%Y-%m-%d') "
                            + "= :date", MovieSession.class);
            sessionsOfMovieQuery.setParameter("movieId", movieId);
            sessionsOfMovieQuery.setParameter("date",
                    date.format(DateTimeFormatter.ISO_LOCAL_DATE));
            return sessionsOfMovieQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving "
                    + "all available sessions of film for id" + movieId
                    + " and date " + date, e);
        }
    }

    @Override
    public MovieSession update(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update movie session for id "
                    + movieSession.getId(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public MovieSession delete(Long movieSessionId) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> deleteMovieSessionQuery =
                    session.createQuery("DELETE FROM MovieSession ms "
                            + "WHERE id = :movieSessionId", MovieSession.class);
            deleteMovieSessionQuery.setParameter("movieSessionId", movieSessionId);
            return deleteMovieSessionQuery.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't delete move session for id "
                    + movieSessionId, e);
        }
    }
}
