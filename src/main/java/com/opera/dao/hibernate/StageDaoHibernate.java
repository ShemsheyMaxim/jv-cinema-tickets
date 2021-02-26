package com.opera.dao.hibernate;

import com.opera.dao.StageDao;
import com.opera.exception.DataProcessingException;
import com.opera.model.Stage;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StageDaoHibernate implements StageDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public StageDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Stage add(Stage stage) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(stage);
            transaction.commit();
            return stage;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert stage "
                    + stage + " to table. ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Stage> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Stage> getAllStages =
                    session.createQuery("SELECT s FROM Stage s", Stage.class);
            return getAllStages.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all stages. ", e);
        }
    }

    @Override
    public Optional<Stage> get(Long stageId) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Stage.class, stageId));
        } catch (Exception e) {
            throw new DataProcessingException("Can't find stage for id "
                    + stageId, e);
        }
    }
}
