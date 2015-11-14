package ru.yeroshenko.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.yeroshenko.domain.Ord;

import java.util.List;

/**
 * Created by Genie Yeroshenko on 10/11/15.
 */
public class OrdDao {

    private SessionFactory sessionFactory;

    public OrdDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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

    public void add(Ord ord) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(ord);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void delete(Ord ord) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(ord);
            session.flush();
            System.out.println("Ord deleted");
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Ord findById(long id) {
        Session session = sessionFactory.openSession();
        Ord ord = (Ord) session.get(Ord.class, id);
        session.close();
        return ord;
    }

    public List<Ord> findAll() {
        Session session = sessionFactory.openSession();
        List list = session.createQuery("from Ord").list();
        session.close();
        return list;
    }

    //todo 0?????? (0, ordStatus)
    public List<Ord> findAllByStatus(Ord.OrdStatus ordStatus) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Ord ord where ord.ordStatus = ?");
        query.setParameter(0, ordStatus);
        List list = query.list();
        session.close();
        return list;
    }

}
