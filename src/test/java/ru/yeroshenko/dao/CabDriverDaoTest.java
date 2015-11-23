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

    CabDriverDao cabDriverDao;

    @Before
    public void setUp() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        cabDriverDao = new CabDriverDao(sessionFactory);
    }

    @Test
    public void testAdd() throws Exception {
        CabDriver cabDriver = new CabDriver();
        cabDriver.setLogin("Vasya1");
        cabDriverDao.add(cabDriver);

        cabDriverDao.delete(cabDriver);

        assertTrue(cabDriver.getId() > 0);
    }

    @Test
    public void testUpdate() throws Exception {
        CabDriver cabDriver = new CabDriver();
        cabDriver.setLogin("Vasya2");
        cabDriverDao.add(cabDriver);

        long idD = cabDriver.getId();
        CabDriver cabDriverFromDb = cabDriverDao.findById(idD);
        cabDriver.setLogin("Sveta");
        cabDriverDao.update(cabDriver);

        long idD2 = cabDriver.getId();
        CabDriver cabDriverFromDb2 = cabDriverDao.findById(idD2);

        cabDriverDao.delete(cabDriver);

        assertEquals("Vasya2", cabDriverFromDb.getLogin());
        assertEquals("Sveta", cabDriverFromDb2.getLogin());
    }

    @Test
    public void testDelete() throws Exception {
        CabDriver cabDriver = new CabDriver();
        cabDriver.setLogin("Vasya3");
        cabDriverDao.add(cabDriver);
        long id = cabDriver.getId();
        cabDriverDao.delete(cabDriver);

        CabDriver cabDriverFromDb = cabDriverDao.findById(id);

        assertNull(cabDriverFromDb);
    }

    @Test
    public void testFindById() throws Exception {
        CabDriver cabDriver = new CabDriver();
        cabDriver.setLogin("Vasya4");
        cabDriverDao.add(cabDriver);
        long id = cabDriver.getId();
        CabDriver cabDriverFromDb = cabDriverDao.findById(id);

        assertEquals(cabDriverFromDb.getLogin(), cabDriver.getLogin());

        cabDriverDao.delete(cabDriver);
    }

}