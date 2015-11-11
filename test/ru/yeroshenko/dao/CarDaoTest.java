package ru.yeroshenko.dao;

import org.junit.Assert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.domain.Car;
import ru.yeroshenko.util.HibernateUtil;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

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
        Car carFromDb2 = carDao.findById(id);

        assertEquals("mercedes", carFromDb2.getModel());
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

    @Test
    public void testSetAndSaveCabDriverToCar() throws Exception {
        Car car = new Car();
        CabDriver cabDriver = new CabDriver();
        car.setModel("kia");
        cabDriver.setName("Tom");
        car.setCabDriver(cabDriver);
        carDao.add(car);

        Car carFromDb = carDao.findById(car.getId());
        assertEquals(carFromDb.getCabDriver().getName(), cabDriver.getName());
    }

//todo All cars from one driver
  /*  @Test
    public void testGetAllCarsFromCabDriver() throws Exception {
        Car car1 = new Car();
        car1.setModel("bmw");
        Car car2 = new Car();
        car2.setModel("lada");
        Car car3 = new Car();
        car3.setModel("volga");

        CabDriver cabDriver1 = new CabDriver();
        cabDriver1.setName("Pit");
        CabDriver cabDriver2 = new CabDriver();
        cabDriver2.setName("Nick");

        car1.setCabDriver(cabDriver1);
        car2.setCabDriver(cabDriver1);
        car3.setCabDriver(cabDriver2);

        carDao.add(car1);
        carDao.add(car2);
        carDao.add(car3);



        List<Car> allCarsByDriver = carDao.findAllByDriver(cabDriver1);
        assertEquals(allCarsByDriver.size(),2);

    }*/



}