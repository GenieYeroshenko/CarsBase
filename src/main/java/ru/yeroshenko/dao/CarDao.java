package ru.yeroshenko.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.domain.Car;

import java.util.List;

//import ru.yeroshenko.domain.CarType;

/**
 * Created by Genie Yeroshenko on 10/11/15.
 */
public class CarDao {

    private SessionFactory sessionFactory;

    public CarDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void update(Car car) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(car);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
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

    public void add(Car car, long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            CabDriver cabDriver = (CabDriver) session.get(CabDriver.class, id);
            car.setCabDriver(cabDriver);
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
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(car);
            session.flush();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Car findById(long id) {
        Session session = sessionFactory.openSession();
        Car car = (Car) session.get(Car.class, id);
        session.close();
        return car;
    }

    public List<Car> findAll() {
        Session session = sessionFactory.openSession();
        List list = session.createQuery("from Car").list();
        session.close();
        return list;
    }

    public List<Car> findAllByDriver(CabDriver cabDriver) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Car car where car.cabDriver = ?");
        query.setParameter(0, cabDriver);
        List list = query.list();
        session.close();
        return list;
    }

    public List<Car> findAllByType(Boolean carTypeLorry) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Car car where car.carTypeLorry = ?");
        query.setParameter(0, carTypeLorry);
        List list = query.list();
        session.close();
        return list;
    }


    public List<Car> findAllByStatus(Boolean carStatus) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Car car where car.carStatus = ?");
        query.setParameter(0, carStatus);
        List list = query.list();
        session.close();
        return list;
    }

}
