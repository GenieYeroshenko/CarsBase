package ru.yeroshenko.dao;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ru.yeroshenko.domain.CarManager;
import ru.yeroshenko.util.HibernateUtil;

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
    public void testFindById() throws Exception {
        CarManager carManager = new CarManager();
        carManager.setLogin("Vasya4");
        carManagerDao.add(carManager);
        long id = carManager.getId();
        CarManager carManagerFromDb = carManagerDao.findById(id);

        carManagerDao.delete(carManager);

        assertEquals(carManagerFromDb.getLogin(), carManager.getLogin());
    }

}