package ru.yeroshenko.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.yeroshenko.domain.Car;

/**
 * Created by Genie Yeroshenko on 10/11/15.
 */
public class CarDao {

    public CarDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void update(Car car) {
        Session session = sessionFactory.getCurrentSession();
        session.update(car);
    }

    public void add(Car car) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(car);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void delete(Car car) {

    }

    public void findById(long id) {

    }

    private SessionFactory sessionFactory;


}
