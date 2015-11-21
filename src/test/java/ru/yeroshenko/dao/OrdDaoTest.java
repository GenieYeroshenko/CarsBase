package ru.yeroshenko.dao;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ru.yeroshenko.domain.Car;
import ru.yeroshenko.domain.Ord;
import ru.yeroshenko.domain.Ord.OrdStatus;
import ru.yeroshenko.util.HibernateUtil;

import java.util.List;

import static org.junit.Assert.*;

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

    //todo =updateOrd?
    @Test
    public void testUpdate() throws Exception {
        Ord ord = new Ord();
        ord.setRout("SPb");
        ordDao.add(ord);
        long id = ord.getId();
        Ord ordFromDb = ordDao.findById(id);
        ordFromDb.setRout("MSK");
        ordDao.update(ordFromDb);
        Ord ordFromDb2 = ordDao.findById(id);
        assertEquals("MSK", ordFromDb2.getRout());
        ordDao.delete(ordFromDb);
    }

    //todo check
    @Test
    public void testUpdateOrd() throws Exception {
        Car car = new Car();
        car.setLicencePlate("33 eee");
        carDao.add(car);
        long idCar = car.getId();
        Car carFromDb = carDao.findById(idCar);

        Ord ord = new Ord();
        ord.setRout("SPb");
        ord.setCar(carFromDb);
        ordDao.add(ord);
        long idOrd = ord.getId();
        Ord ordFromDb = ordDao.findById(idOrd);

        ordFromDb.setRout("MSK");
        carFromDb.setLicencePlate("44 aaa");
        ordFromDb.setCar(carFromDb);

        ordDao.update(ordFromDb);
        Ord ordFromDb2 = ordDao.findById(idOrd);
        assertEquals("44 aaa", ordFromDb2.getCar().getLicencePlate());
//        ordDao.delete(ord);
//        carDao.delete(car);
    }


    //todo add=create?
    @Test
    public void testAdd() throws Exception {
        Ord ord = new Ord();
        ord.setRout("SPb");
        ordDao.add(ord);
        assertTrue(ord.getId() > 0);
        ordDao.delete(ord);
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
        Ord ord1 = new Ord();
        ord1.setRout("NY");
        ordDao.add(ord1);
        Ord ord2 = new Ord();
        ord2.setRout("NY");
        ordDao.add(ord2);
        List<Ord> ords = ordDao.findAll();
        assertEquals(ords.size(), 2);
        ordDao.delete(ord1);
        ordDao.delete(ord2);
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

    //todo check
    @Test
    public void testCreate() throws Exception {
        Car car = new Car();
        car.setLicencePlate("EN 2222");
        car.setModel("bmw");
        carDao.add(car);
        long idCar = car.getId();
        Car carFromDb = carDao.findById(idCar);

        Ord ord = new Ord();
        ord.setRout("SPb");
        ord.setCar(carFromDb);

        ordDao.add(ord);
        long idOrd = ord.getId();
        Ord ordFromDb = ordDao.findById(idOrd);

        assertEquals(ordFromDb.getCar().getLicencePlate(), car.getLicencePlate());
        //ordDao.delete(ord);
        //carDao.delete(car);
    }
}