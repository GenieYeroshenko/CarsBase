package ru.yeroshenko.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.domain.Car;

import java.util.List;

/**
 * Class with basic methods that service DAO Car
 */
public class CarDao {

    private SessionFactory sessionFactory;

    /**
     * @param sessionFactory - a factory to create new Session instances
     */
    public CarDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     *
     * @param car - one of the fild of the order, that saved into its table
     * Method changes and saves car in one transaction
     */
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

    /**
     *
     * @param car - one of the fild of the order, that saved into its table
     * Method saves car in one transaction
     */
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

    /**
     *
     * @param car- one of the fild of the order, that saved into its table
     * @param id - personal id-number
     * Method saves car with cabDriver in one transaction
     */
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

    /**
     *
     * @param car -  one of the fild of the order, that saved into its table
     * Method deletes car in one transaction
     */
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

    /**
     *
     * @param id - one of the fild of the order, that saved into its table
     * @return car, which was found by id
     */
    public Car findById(long id) {
        Session session = sessionFactory.openSession();
        Car car = (Car) session.get(Car.class, id);
        session.close();
        return car;
    }

    /**
     *
     * @return list of all cars
     */
    public List<Car> findAll() {
        Session session = sessionFactory.openSession();
        List list = session.createQuery("from Car").list();
        session.close();
        return list;
    }

    /**
     *
     * @param cabDriver - User, with a personal access to specialized functions by role
     * @return list of all cars with a specific driver
     */
    public List<Car> findAllByDriver(CabDriver cabDriver) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Car car where car.cabDriver = ?");
        query.setParameter(0, cabDriver);
        List list = query.list();
        session.close();
        return list;
    }

    /**
     *
     * @param carTypeLorry - type of car, which should be set by CabDriver
     * @return list of all cars with specific type
     */
    public List<Car> findAllByType(Boolean carTypeLorry) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Car car where car.carTypeLorry = ?");
        query.setParameter(0, carTypeLorry);
        List list = query.list();
        session.close();
        return list;
    }

    /**
     *
     * @param carStatus - serviceability status of the car, which should be set by CabDriver
     * @return list of all cars with specific serviceability status
     */
    public List<Car> findAllByStatus(Boolean carStatus) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Car car where car.carStatus = ?");
        query.setParameter(0, carStatus);
        List list = query.list();
        session.close();
        return list;
    }

}
