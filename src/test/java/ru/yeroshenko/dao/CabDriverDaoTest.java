package ru.yeroshenko.dao;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.util.HibernateUtil;

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
        cabDriver.setLogin("Vasya");
        cabDriverDao.add(cabDriver);
        assertTrue(cabDriver.getId() > 0);
        cabDriverDao.delete(cabDriver);
    }

    @Test
    public void testUpdate() throws Exception {
        CabDriver cabDriver = new CabDriver();
        cabDriver.setLogin("Vasya");
        cabDriverDao.add(cabDriver);
        long idD = cabDriver.getId();
        CabDriver cabDriverFromDb = cabDriverDao.findById(idD);
        cabDriver.setLogin("Sveta");
        cabDriverDao.update(cabDriver);
        long idD2 = cabDriver.getId();
        CabDriver cabDriverFromDb2 = cabDriverDao.findById(idD2);
        assertEquals("Vasya", cabDriverFromDb.getLogin());
        assertEquals("Sveta", cabDriverFromDb2.getLogin());
        cabDriverDao.delete(cabDriver);
    }

    @Test
    public void testDelete() throws Exception {
        CabDriver cabDriver = new CabDriver();
        cabDriver.setLogin("Vasya");
        cabDriverDao.add(cabDriver);
        long id = cabDriver.getId();
        cabDriverDao.delete(cabDriver);
        CabDriver cabDriverFromDb = cabDriverDao.findById(id);
        assertNull(cabDriverFromDb);
    }

    @Test
    public void testFindById() throws Exception {
        CabDriver cabDriver = new CabDriver();
        cabDriver.setLogin("Vasya");
        cabDriverDao.add(cabDriver);
        long id = cabDriver.getId();
        CabDriver cabDriverFromDb = cabDriverDao.findById(id);
        assertEquals(cabDriverFromDb.getLogin(), cabDriver.getLogin());
        cabDriverDao.delete(cabDriver);
    }

    @Test
    public void testFindAll() throws Exception {
        CabDriver cabDriver1 = new CabDriver();
        cabDriver1.setLogin("Vasya");
        cabDriverDao.add(cabDriver1);
        CabDriver cabDriver2 = new CabDriver();
        cabDriver2.setLogin("Kolya");
        cabDriverDao.add(cabDriver2);
        List<CabDriver> drivers = cabDriverDao.findAll();
        assertEquals(drivers.size(), 2);
        cabDriverDao.delete(cabDriver1);
        cabDriverDao.delete(cabDriver2);
    }
}