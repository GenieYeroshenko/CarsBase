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
        cabDriverDao.delete(cabDriver);
    }

    //todo
    @Test
    public void testUpdate() throws Exception {
        CabDriver cabDriver = new CabDriver();

        cabDriver.setName("Vasya");

        cabDriverDao.add(cabDriver);
        long idD = cabDriver.getId();

        CabDriver cabDriverFromDb = cabDriverDao.findById(idD);

        cabDriver.setName("Sveta");
        cabDriverDao.update(cabDriver);
        long idD2 = cabDriver.getId();

        CabDriver cabDriverFromDb2 = cabDriverDao.findById(idD2);

        assertEquals("Sveta", cabDriverFromDb2.getName());

        cabDriverDao.delete(cabDriver);

    }

//
//    @Test
//    public void testUpdate() throws Exception {
//        CabDriver cabDriver = new CabDriver();
//        Car car = new Car();
//        cabDriver.setName("Vasya");
//
//        car.setCabDriver(cabDriver);
//
//        cabDriverDao.add(cabDriver);
//        long id = cabDriver.getId();
//
//        CabDriver cabDriverFromDb = cabDriverDao.findById(id);
//        cabDriverFromDb.setName("Vasya");
//        car.setModel("Kia");
//
//
//        cabDriverDao.update(cabDriverFromDb);
//        //Car carFromDb = carDao.findById(id);
//        //carDao.update(carFromDb);
//
//        // assertEquals("Kia", carFromDb.getModel());
//        assertEquals("Vasya", cabDriverFromDb.getName());
//        //assertEquals("Kia", carFromDb.getModel());
//
//
//    }


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
        cabDriverDao.delete(cabDriver);

    }

    @Test
    public void testFindAll() throws Exception {
        CabDriver cabDriver1 = new CabDriver();
        cabDriver1.setName("Vasya");
        cabDriverDao.add(cabDriver1);
        CabDriver cabDriver2 = new CabDriver();
        cabDriver2.setName("Kolya");
        cabDriverDao.add(cabDriver2);
        List<CabDriver> drivers = cabDriverDao.findAll();
        assertEquals(drivers.size(), 2);
        cabDriverDao.delete(cabDriver1);
        cabDriverDao.delete(cabDriver2);


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
        CabDriver cabDriver = new CabDriver();
        cabDriver.setName("Tom");
        cabDriver.setCars(cars);
        car1.setCabDriver(cabDriver);
        car2.setCabDriver(cabDriver);
        cabDriverDao.add(cabDriver);
        CabDriver cabDriverFromDb = cabDriverDao.findById(cabDriver.getId());
        assertEquals(cabDriver.getCars().size(), cabDriverFromDb.getCars().size());
        cabDriverDao.delete(cabDriver);

    }



}