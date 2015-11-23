package ru.yeroshenko.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.yeroshenko.domain.CarManager;


/**
 * Created by Genie Yeroshenko on 10/11/15.
 */
public class CarManagerDao {


    private SessionFactory sessionFactory;

    public CarManagerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(CarManager carManager) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(carManager);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void delete(CarManager carManager) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(carManager);
            session.flush();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public CarManager findById(long id) {
        Session session = sessionFactory.openSession();
        CarManager carManager = (CarManager) session.get(CarManager.class, id);
        session.close();
        return carManager;
    }

}
