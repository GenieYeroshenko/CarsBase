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
        car1.setCarTypeLorry(true);
        car1.setCarStatus(true);
        CabDriver cabDriver = new CabDriver();
        cabDriver.setLogin("Kolya");
        car1.setCabDriver(cabDriver);

        carDao.add(car1);
        Car car2 = new Car();
        car2.setLicencePlate("EN 333");
        car2.setModel("kia");
        car2.setCarTypeLorry(false);
        car2.setCarStatus(false);
        CabDriver cabDriver2 = new CabDriver();
        cabDriver2.setLogin("Vasya");
        car2.setCabDriver(cabDriver2);

        carDao.add(car2);
        List<Car> cars = carDao.findAll();
        assertEquals(cars.size(), 2);
    }

    @Test
    public void testSetAndSaveCabDriverToCar() throws Exception {
        Car car = new Car();
        CabDriver cabDriver = new CabDriver();
        car.setModel("kia");
        cabDriver.setLogin("Tom");
        car.setCabDriver(cabDriver);
        carDao.add(car);
        Car carFromDb = carDao.findById(car.getId());
        assertEquals(carFromDb.getModel(), car.getModel());
        assertEquals(carFromDb.getCabDriver().getLogin(), cabDriver.getLogin());
        carDao.delete(car);
    }

    //todo findAllCarsByCabDriver
    public void testFindAllByDriver() throws Exception {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        car1.setLicencePlate("EN 2222");
        car1.setLicencePlate("EN 3333");
        car1.setLicencePlate("EN 4444");

        CabDriver cabDriver1 = new CabDriver();
        CabDriver cabDriver2 = new CabDriver();
        cabDriver1.setLogin("Vasya");
        cabDriver2.setLogin("Kolya");

        car1.setCabDriver(cabDriver1);
        car2.setCabDriver(cabDriver1);
        car3.setCabDriver(cabDriver2);
        carDao.add(car1);
        carDao.add(car2);
        carDao.add(car2);

        List<Car> cars = carDao.findAllByDriver(cabDriver1);
        assertEquals(cars.size(), 0);

        carDao.delete(car1);
        carDao.delete(car2);
        carDao.delete(car3);
    }
}