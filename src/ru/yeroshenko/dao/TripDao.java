package ru.yeroshenko.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.yeroshenko.domain.Trip;

import java.util.List;

/**
 * Created by Genie Yeroshenko on 10/11/15.
 */
public class TripDao {

    private SessionFactory sessionFactory;

    public TripDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void update(Trip trip) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(trip);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void add(Trip trip) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(trip);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void delete(Trip trip) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(trip);
            session.flush();
            System.out.println("Trip deleted");
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Trip findById(long id) {
        Session session = sessionFactory.openSession();
        Trip trip = (Trip) session.get(Trip.class, id);
        session.close();
        return trip;
    }

    public List<Trip> findAll() {
        Session session = sessionFactory.openSession();
        List list = session.createQuery("from Trip").list();
        session.close();
        return list;
    }

//todo ?????? (0, tripStatus)
    public List<Trip> findAllByStatus(Boolean tripStatus) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Trip trip where trip.tripStatus = ?");
        query.setParameter(0, tripStatus);
        List list = query.list();
        session.close();
        return list;
    }


}
