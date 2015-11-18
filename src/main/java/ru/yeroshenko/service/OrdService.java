package ru.yeroshenko.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.yeroshenko.dao.CarDao;
import ru.yeroshenko.dao.OrdDao;
import ru.yeroshenko.domain.Car;
import ru.yeroshenko.domain.Ord;
import ru.yeroshenko.util.HibernateUtil;

/**
 * Created by evgeniya on 17/11/15.
 */
public class OrdService {

    private CarDao carDao;
    private OrdDao ordDao;

    public OrdService(CarDao carDao, OrdDao ordDao) {
        this.carDao = carDao;
        this.ordDao = ordDao;
    }

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
