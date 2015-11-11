package ru.yeroshenko.dao;

import org.junit.Assert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ru.yeroshenko.domain.Car;
import ru.yeroshenko.util.HibernateUtil;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Genie Yeroshenko on 10/11/15.
 */
public class CarDaoTest {

    CarDao carDao;

    @Before
    public void setUp() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        carDao = new CarDao(sessionFactory);
    }

    @Test
    public void testAdd() throws Exception {
        Car car = new Car();
        car.setLicencePlate("EN 2222");
        car.setModel("bmw");

        carDao.add(car);

        assertTrue(car.getId() > 0);
    }

    @Test
    public void testUpdate() throws Exception {
        Car car = new Car();
        car.setLicencePlate("EN 2222");
        car.setModel("bmw");
        carDao.add(car);
        long id = car.getId();

        Car carFromDb = carDao.findById(id);
        carFromDb.setModel("mercedes");

        carDao.update(carFromDb);
        //Car carFromDb2 = carDao.findById(id);

        assertEquals("mercedes", carFromDb.getModel());
    }

    @Test
    public void testDelete() throws Exception {
        Car car = new Car();
        car.setLicencePlate("EN 1111");
        car.setModel("bmw");
        carDao.add(car);
        long id = car.getId();
        carDao.delete(car);
        Car carFromDb = carDao.findById(id);
        assertNull(carFromDb);
    }


    @Test
    public void testFindById() throws Exception {
        Car car = new Car();
        car.setLicencePlate("EN 2222");
        car.setModel("bmw");
        carDao.add(car);
        long id = car.getId();
        Car carFromDb = carDao.findById(id);
        assertEquals(carFromDb.getLicencePlate(), car.getLicencePlate());
    }

    @Test
    public void testFindAll() throws Exception {
        Car car = new Car();
        car.setLicencePlate("EN 2222");
        car.setModel("bmw");

        carDao.add(car);

        List<Car> cars = carDao.findAll();

        assertTrue(!cars.isEmpty());
    }
}