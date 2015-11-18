package ru.yeroshenko.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.yeroshenko.domain.Car;
import ru.yeroshenko.domain.Ord;
import ru.yeroshenko.util.HibernateUtil;

/**
 * Created by evgeniya on 17/11/15.
 */
public class OrdService {


    public void createOrd(Ord ord, long carId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Car carFromDB = (Car) session.get(Car.class, carId);
            ord.setCar(carFromDB);
            session.persist(ord);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void updateOrd(Ord ord, long carId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Car carFromDB = (Car) session.get(Car.class, carId);
            ord.setCar(carFromDB);
            session.update(ord);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
