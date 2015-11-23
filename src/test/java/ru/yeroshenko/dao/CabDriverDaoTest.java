package ru.yeroshenko.dao;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.util.HibernateUtil;

import static org.junit.Assert.*;

/**
 * Created by Genie Yeroshenko on 10/11/15.
 */
public class CabDriverDaoTest {

    private CabDriverDao cabDriverDao;
    private AccountDao accountDao;

    @Before
    public void setUp() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        cabDriverDao = new CabDriverDao(sessionFactory);
        accountDao = new AccountDao(sessionFactory);
    }

    @Test
    public void testAdd() throws Exception {
        CabDriver cabDriver = new CabDriver();
        cabDriver.setLogin("Vasya1");
        accountDao.add(cabDriver);

        accountDao.delete(cabDriver);

        assertTrue(cabDriver.getId() > 0);
    }

    @Test
    public void testUpdate() throws Exception {
        CabDriver cabDriver = new CabDriver();
        cabDriver.setLogin("Vasya2");
        accountDao.add(cabDriver);

        long idD = cabDriver.getId();
        CabDriver cabDriverFromDb = cabDriverDao.findById(idD);
        cabDriver.setLogin("Sveta");
        cabDriverDao.update(cabDriver);

        long idD2 = cabDriver.getId();
        CabDriver cabDriverFromDb2 = cabDriverDao.findById(idD2);

        accountDao.delete(cabDriver);

        assertEquals("Vasya2", cabDriverFromDb.getLogin());
        assertEquals("Sveta", cabDriverFromDb2.getLogin());
    }

    @Test
    public void testDelete() throws Exception {
        CabDriver cabDriver = new CabDriver();
        cabDriver.setLogin("Vasya3");
        accountDao.add(cabDriver);
        long id = cabDriver.getId();
        accountDao.delete(cabDriver);

        CabDriver cabDriverFromDb = cabDriverDao.findById(id);

        assertNull(cabDriverFromDb);
    }

    @Test
    public void testFindById() throws Exception {
        CabDriver cabDriver = new CabDriver();
        cabDriver.setLogin("Vasya4");
        accountDao.add(cabDriver);
        long id = cabDriver.getId();
        CabDriver cabDriverFromDb = cabDriverDao.findById(id);

        assertEquals(cabDriverFromDb.getLogin(), cabDriver.getLogin());

        accountDao.delete(cabDriver);
    }

}