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

    private CarManagerDao carManagerDao;
    private AccountDao accountDao;


    @Before
    public void setUp() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        carManagerDao = new CarManagerDao(sessionFactory);
        accountDao = new AccountDao(sessionFactory);
    }

    @Test
    public void testAdd() throws Exception {
        CarManager carManager = new CarManager();
        carManager.setLogin("Vasya1");

        accountDao.add(carManager);
        accountDao.delete(carManager);

        assertTrue(carManager.getId() > 0);
    }

    @Test
    public void testDelete() throws Exception {
        CarManager carManager = new CarManager();
        carManager.setLogin("Vasya3");
        accountDao.add(carManager);
        long id = carManager.getId();
        accountDao.delete(carManager);

        CarManager carManagerFromDb = carManagerDao.findById(id);

        assertNull(carManagerFromDb);
    }

    @Test
    public void testFindById() throws Exception {
        CarManager carManager = new CarManager();
        carManager.setLogin("Vasya4");
        accountDao.add(carManager);
        long id = carManager.getId();
        CarManager carManagerFromDb = carManagerDao.findById(id);

        accountDao.delete(carManager);

        assertEquals(carManagerFromDb.getLogin(), carManager.getLogin());
    }

}