package ru.yeroshenko.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.domain.Car;
import ru.yeroshenko.domain.Ord;
import ru.yeroshenko.util.HibernateUtil;

import java.util.List;

/**
 * Class with basic methods that service DAO Ord
 */
public class OrdDao {

    private SessionFactory sessionFactory;

    /**
     * @param sessionFactory - a factory to create new Session instances
     */
    public OrdDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @param ord -  Order, which created by CarManager
     * Method deletes Order in one transaction
     */
    public void delete(Ord ord) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(ord);
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
     * @param id - personal id-number
     * @return Order, which was found by id
     */
    public Ord findById(long id) {
        Session session = sessionFactory.openSession();
        Ord ord = (Ord) session.get(Ord.class, id);
        session.close();
        return ord;
    }

    /**
     * @return list of all orders
     */
    public List<Ord> findAll() {
        Session session = sessionFactory.openSession();
        List list = session.createQuery("from Ord").list();
        session.close();
        return list;
    }

    /**
     * @param ordStatus - status of the Order, which should be set by CabDriver or CarManager
     * @return list of all orders with specific status
     */
    public List<Ord> findAllByStatus(Ord.OrdStatus ordStatus) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Ord ord where ord.ordStatus = ?");
        query.setParameter(0, ordStatus);
        List list = query.list();
        session.close();
        return list;
    }

    /**
     * @param ord - Order, which created by CarManager
     * Method changes and saves order in one transaction by id
     */
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

    /**
     * @param ord - Order, which created by CarManager
     */
    public void update(Ord ord) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(ord);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * @param cabDriver - User, with a personal access to specialize functions by role
     * @param statuses  - special statuses of Order
     * @return list of orders, which was filtered by Driver and Order status
     */
    public List<Ord> findAllByDriverAndOrdStatuses(CabDriver cabDriver, Ord.OrdStatus[] statuses) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Ord ord where ord.car.cabDriver = :driver and ord.ordStatus in :statuses");
        query.setParameter("driver", cabDriver);
        query.setParameterList("statuses", statuses);
        List list = query.list();
        session.close();
        return list;
    }

    /**
     * @param cabDriver - User, with a personal access to specialize functions by role
     * @return list of orders, which was filtered by Driver
     */
    public List<Ord> findAllByDriver(CabDriver cabDriver) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Ord ord where ord.car.cabDriver = ?");
        query.setParameter(0, cabDriver);
        List list = query.list();
        session.close();
        return list;
    }

    /**
     * @param ord   - Order, which created by CarManager
     * @param carId -  personal id-number
     * Method creates order with car by id
     */
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
}
