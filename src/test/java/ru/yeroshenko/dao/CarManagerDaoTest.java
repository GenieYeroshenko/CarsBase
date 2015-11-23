package ru.yeroshenko.dao;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ru.yeroshenko.domain.CarManager;
import ru.yeroshenko.util.HibernateUtil;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Genie Yeroshenko on 10/11/15.
 */
public class CarManagerDaoTest {

    CarManagerDao carManagerDao;

    CarDao carDao;


    @Before
    public void setUp() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        carManagerDao = new CarManagerDao(sessionFactory);
        carDao = new CarDao(sessionFactory);
    }

    @Test
    public void testAdd() throws Exception {
        CarManager carManager = new CarManager();
        carManager.setLogin("Vasya1");

        carManagerDao.add(carManager);
        carManagerDao.delete(carManager);

        assertTrue(carManager.getId() > 0);
    }

    @Test
    public void testDelete() throws Exception {
        CarManager carManager = new CarManager();
        carManager.setLogin("Vasya3");
        carManagerDao.add(carManager);
        long id = carManager.getId();
        carManagerDao.delete(carManager);

        CarManager carManagerFromDb = carManagerDao.findById(id);

        assertNull(carManagerFromDb);
    }


    @Test
    public void testUpdate() throws Exception {
        CarManager carManager = new CarManager();
        carManager.setLogin("Vasya2");
        carManagerDao.add(carManager);

        long idD = carManager.getId();
        CarManager carManagerFromDb = carManagerDao.findById(idD);
        carManager.setLogin("Sveta");
        carManagerDao.update(carManager);

        long idD2 = carManager.getId();
        CarManager carManagerFromDb2 = carManagerDao.findById(idD2);

        carManagerDao.delete(carManager);

        assertEquals("Vasya2", carManagerFromDb.getLogin());
        assertEquals("Sveta", carManagerFromDb2.getLogin());
    }


    @Test
    public void testFindById() throws Exception {
        CarManager carManager = new CarManager();
        carManager.setLogin("Vasya4");
        carManagerDao.add(carManager);
        long id = carManager.getId();
        CarManager carManagerFromDb = carManagerDao.findById(id);

        carManagerDao.delete(carManager);

        assertEquals(carManagerFromDb.getLogin(), carManager.getLogin());
    }

    @Test
    public void testFindAll() throws Exception {
        CarManager carManager1 = new CarManager();
        carManager1.setLogin("Vasya5");
        carManagerDao.add(carManager1);

        CarManager carManager2 = new CarManager();
        carManager2.setLogin("Kolya2");
        carManagerDao.add(carManager2);

        List<CarManager> carManagers = carManagerDao.findAll();

        carManagerDao.delete(carManager1);
        carManagerDao.delete(carManager2);

        assertEquals(carManagers.size(), 2);
    }

}