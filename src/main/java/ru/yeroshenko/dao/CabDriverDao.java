package ru.yeroshenko.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.yeroshenko.domain.CabDriver;

/**
 * Created by Genie Yeroshenko on 10/11/15.
 */
public class CabDriverDao {

    private SessionFactory sessionFactory;

    public CabDriverDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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

    public CabDriver findById(long id) {
        Session session = sessionFactory.openSession();
        CabDriver cabDriver = (CabDriver) session.get(CabDriver.class, id);
        session.close();
        return cabDriver;
    }

}
