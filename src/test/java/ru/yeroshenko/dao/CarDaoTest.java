package ru.yeroshenko.dao;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ru.yeroshenko.domain.CabDriver;
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
        carDao.delete(car);
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
        Car carFromDb2 = carDao.findById(id);
        assertEquals("mercedes", carFromDb2.getModel());
        carDao.delete(carFromDb);
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
        carDao.delete(car);
    }

    @Test
    public void testFindAll() throws Exception {
        Car car1 = new Car();
        car1.setLicencePlate("EN 2222");
        car1.setModel("bmw");
        carDao.add(car1);
        Car car2 = new Car();
        car2.setLicencePlate("EN 333");
        car2.setModel("kia");
        carDao.add(car2);
        List<Car> cars = carDao.findAll();
        assertEquals(cars.size(), 2);
    }

    @Test
    public void testSetAndSaveCabDriverToCar() throws Exception {
        Car car = new Car();
        CabDriver cabDriver = new CabDriver();
        car.setModel("kia");
        cabDriver.setName("Tom");
        car.setCabDriver(cabDriver);
        carDao.add(car);
        Car carFromDb = carDao.findById(car.getId());
        assertEquals(carFromDb.getModel(), car.getModel());
        assertEquals(carFromDb.getCabDriver().getName(), cabDriver.getName());
        carDao.delete(car);
    }
}