package ru.yeroshenko.dao;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ru.yeroshenko.domain.Ord;
import ru.yeroshenko.domain.Trip;
import ru.yeroshenko.util.HibernateUtil;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Genie Yeroshenko on 10/11/15.
 */
public class TripDaoTest {

    TripDao tripDao;

    @Before
    public void setUp() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        tripDao = new TripDao(sessionFactory);
    }

    @Test
    public void testUpdate() throws Exception {
        Trip trip = new Trip();
        trip.setCarTypeLorry(false);
        tripDao.add(trip);
        long id = trip.getId();
        Trip tripFromDb = tripDao.findById(id);
        tripFromDb.setCarTypeLorry(true);
        tripDao.update(tripFromDb);
        Trip tripFromDb2 = tripDao.findById(id);
        assertEquals(true, tripFromDb2.getCarTypeLorry());
        tripDao.delete(tripFromDb);
    }

    @Test
    public void testAdd() throws Exception {
        Trip trip = new Trip();
        trip.setCarTypeLorry(true);
        tripDao.add(trip);
        assertTrue(trip.getCarTypeLorry());
        tripDao.delete(trip);
    }

    @Test
    public void testDelete() throws Exception {
        Trip trip = new Trip();
        trip.setCarTypeLorry(true);
        tripDao.add(trip);
        long id = trip.getId();
        tripDao.delete(trip);
        Trip tripFromDb = tripDao.findById(id);
        assertNull(tripFromDb);
    }

    @Test
    public void testFindById() {
        Trip trip = new Trip();
        trip.setCarTypeLorry(true);
        tripDao.add(trip);
        long id = trip.getId();
        Trip tripFromDb = tripDao.findById(id);
        assertEquals(tripFromDb.getCarTypeLorry(), trip.getCarTypeLorry());
        tripDao.delete(trip);
    }

    @Test
    public void testFindAll() {
        Trip trip1 = new Trip();
        trip1.setCarTypeLorry(false);
        trip1.setCarTypeLorry(false);
        trip1.setTripStatus(false);
        Date date = new Date();
        trip1.setDate(date);
        Ord ord = new Ord();
        trip1.setOrd(ord);
        tripDao.add(trip1);



        Trip trip2 = new Trip();
        trip2.setCarTypeLorry(true);
        trip2.setCarTypeLorry(true);
        trip2.setTripStatus(true);
        Date date2 = new Date();
        trip2.setDate(date2);
        Ord ord2 = new Ord();
        trip2.setOrd(ord2);
        tripDao.add(trip2);

        List<Trip> trips = tripDao.findAll();
        assertEquals(trips.size(), 2);
//        tripDao.delete(trip1);
//        tripDao.delete(trip2);
    }

    @Test
    public void testFindAllByStatus() {
        Trip trip1 = new Trip();
        Trip trip2 = new Trip();
        Trip trip3 = new Trip();
        trip1.setTripStatus(true);
        trip2.setTripStatus(false);
        trip3.setTripStatus(true);
        tripDao.add(trip1);
        tripDao.add(trip2);
        tripDao.add(trip3);
        List<Trip> trips = tripDao.findAllByStatus(true);
        assertEquals(trips.size(), 2);
        tripDao.delete(trip1);
        tripDao.delete(trip2);
        tripDao.delete(trip3);
    }
}