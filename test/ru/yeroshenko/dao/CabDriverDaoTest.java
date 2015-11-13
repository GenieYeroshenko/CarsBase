package ru.yeroshenko.dao;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.domain.Car;
import ru.yeroshenko.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Genie Yeroshenko on 10/11/15.
 */
public class CabDriverDaoTest {

    CabDriverDao cabDriverDao;
    CarDao carDao;

    @Before
    public void setUp() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        cabDriverDao = new CabDriverDao(sessionFactory);
        carDao = new CarDao(sessionFactory);
    }

    @Test
    public void testAdd() throws Exception {
        CabDriver cabDriver = new CabDriver();
        cabDriver.setName("Vasya");

        cabDriverDao.add(cabDriver);

        assertTrue(cabDriver.getId() > 0);
    }

    @Test
    public void testUpdate() throws Exception {
        CabDriver cabDriver = new CabDriver();
        cabDriver.setName("Vasya");
        cabDriverDao.add(cabDriver);
        long id = cabDriver.getId();

        CabDriver cabDriverFromDb = cabDriverDao.findById(id);
        cabDriverFromDb.setName("Vasya");

        cabDriverDao.update(cabDriverFromDb);
        Car carFromDb2 = carDao.findById(id);

        assertEquals("Vasya", cabDriverFromDb.getName());
    }

    @Test
    public void testDelete() throws Exception {
        CabDriver cabDriver = new CabDriver();
        cabDriver.setName("Vasya");
        cabDriverDao.add(cabDriver);
        long id = cabDriver.getId();
        cabDriverDao.delete(cabDriver);
        CabDriver cabDriverFromDb = cabDriverDao.findById(id);
        assertNull(cabDriverFromDb);
    }


    @Test
    public void testFindById() throws Exception {
        CabDriver cabDriver = new CabDriver();
        cabDriver.setName("Vasya");
        cabDriverDao.add(cabDriver);
        long id = cabDriver.getId();
        CabDriver cabDriverFromDb = cabDriverDao.findById(id);
        assertEquals(cabDriverFromDb.getName(), cabDriver.getName());
    }

    @Test
    public void testFindAll() throws Exception {
        CabDriver cabDriver = new CabDriver();
        cabDriver.setName("Vasya");

        cabDriverDao.add(cabDriver);

        List<CabDriver> cabDrivers = cabDriverDao.findAll();

        assertTrue(!cabDrivers.isEmpty());
    }



    @Test
    public void testGetAllCarsFromCabDriver() throws Exception {
        Car car1 = new Car();
        Car car2 = new Car();
        car1.setModel("kia");
        car2.setModel("kia2");

        List<Car> cars = new ArrayList<Car>();
        cars.add(car1);
        cars.add(car2);
        System.out.println("number of cars in list: " + cars.size());

        CabDriver cabDriver = new CabDriver();
        cabDriver.setName("Tom");

        cabDriver.setCars(cars);
        car1.setCabDriver(cabDriver);
        car2.setCabDriver(cabDriver);
        cabDriverDao.add(cabDriver);

        CabDriver cabDriverFromDb = cabDriverDao.findById(cabDriver.getId());

        assertEquals(cabDriver.getCars().size(), cabDriverFromDb.getCars().size());
    }

}