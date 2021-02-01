package com.cinema.dao.hibernate;

import com.cinema.dao.MovieSessionDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.lib.Dao;
import com.cinema.model.MovieSession;
import com.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoHibernate implements MovieSessionDao {
    @Override
    public MovieSession add(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
}
