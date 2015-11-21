package ru.yeroshenko.dao;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ru.yeroshenko.domain.Car;
import ru.yeroshenko.domain.Ord;
import ru.yeroshenko.domain.Ord.OrdStatus;
import ru.yeroshenko.util.HibernateUtil;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Genie Yeroshenko on 10/11/15.
 */
public class OrdDaoTest {

    OrdDao ordDao;
    CarDao carDao;

    @Before
    public void setUp() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        ordDao = new OrdDao(sessionFactory);
        carDao = new CarDao(sessionFactory);
    }

    @Test
    public void testUpdateOrd() throws Exception {
        Car car = getCar();

        Ord ord = new Ord();
        ord.setRout("SPb");
        ordDao.createOrd(ord, car.getId());
        long idOrd = ord.getId();
        Ord ordFromDb = ordDao.findById(idOrd);

        ordFromDb.setRout("MSK");

        ordDao.update(ordFromDb);
        Ord ordFromDb2 = ordDao.findById(idOrd);
        assertEquals("MSK", ordFromDb2.getRout());
        ordDao.delete(ordFromDb2);
        carDao.delete(car);
    }


    @Test
    public void testDelete() throws Exception {
        Ord ord = new Ord();
        ord.setRout("SPb");
        ordDao.add(ord);
        long id = ord.getId();
        ordDao.delete(ord);
        Ord ordFromDb = ordDao.findById(id);
        assertNull(ordFromDb);
    }

    @Test
    public void testFindById() {
        Ord ord = new Ord();
        ord.setRout("SPb");
        ordDao.add(ord);
        long id = ord.getId();
        Ord ordFromDb = ordDao.findById(id);
        assertEquals(ordFromDb.getRout(), ord.getRout());
        ordDao.delete(ord);
    }

    @Test
    public void testFindAll() {
        Car car = getCar();

        Ord ord1 = new Ord();
        ord1.setRout("NY");
        ordDao.createOrd(ord1, car.getId());


        Ord ord2 = new Ord();
        ord2.setRout("NY");
        ordDao.createOrd(ord2, car.getId());
        List<Ord> ords = ordDao.findAll();

        assertEquals(2, ords.size());

        ordDao.delete(ord1);
        ordDao.delete(ord2);
        carDao.delete(car);
    }

    @Test
    public void testFindAllByStatus() {
        Ord ord1 = new Ord();
        Ord ord2 = new Ord();
        Ord ord3 = new Ord();
        ord1.setOrdStatus(OrdStatus.ASSIGNED);
        ord2.setOrdStatus(OrdStatus.DONE);
        ord3.setOrdStatus(OrdStatus.ASSIGNED);
        ordDao.add(ord1);
        ordDao.add(ord2);
        ordDao.add(ord3);
        List<Ord> ords = ordDao.findAllByStatus(OrdStatus.ASSIGNED);
        assertEquals(ords.size(), 2);
        ordDao.delete(ord1);
        ordDao.delete(ord2);
        ordDao.delete(ord3);
    }

    @Test
    public void testCreate() throws Exception {
        Car carFromDb = getCar();

        Ord ord = new Ord();
        ord.setRout("SPb");
        ord.setCar(carFromDb);

        ordDao.createOrd(ord, carFromDb.getId());
        long idOrd = ord.getId();
        Ord ordFromDb = ordDao.findById(idOrd);

        assertEquals(ordFromDb.getRout(), ordFromDb.getRout());
        ordDao.delete(ord);
        carDao.delete(carFromDb);
    }

    private Car getCar() {
        Car car = new Car();
        car.setLicencePlate("33 eee");
        carDao.add(car);
        return car;
    }
}