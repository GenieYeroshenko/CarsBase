package ru.yeroshenko.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.yeroshenko.domain.CabDriver;

/**
 *
 * Class with basic methods that service DAO CabDriver
 */
public class CabDriverDao {

    private SessionFactory sessionFactory;

    /**
     * @param sessionFactory - a factory to create new Session instances
     */
    public CabDriverDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     *
     * @param cabDriver - User, with a personal access to specialized functions by role
     * Method changes and saves cabDriver in one transaction
     */
    public void update(CabDriver cabDriver) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(cabDriver);
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
     * @param id - personal id-number
     * @return CabDriver, which was found by id
     */
    public CabDriver findById(long id) {
        Session session = sessionFactory.openSession();
        CabDriver cabDriver = (CabDriver) session.get(CabDriver.class, id);
        session.close();
        return cabDriver;
    }

}
