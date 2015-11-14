package ru.yeroshenko.dao;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ru.yeroshenko.domain.Ord;
import ru.yeroshenko.domain.Ord.OrdStatus;
import ru.yeroshenko.util.HibernateUtil;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Genie Yeroshenko on 10/11/15.
 */
public class OrdDaoTest {

    OrdDao ordDao;

    @Before
    public void setUp() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        ordDao = new OrdDao(sessionFactory);
    }

    @Test
    public void testUpdate() throws Exception {
        Ord ord = new Ord();
        ord.setRout("SPb");
        ordDao.add(ord);
        long id = ord.getId();
        Ord ordFromDb = ordDao.findById(id);
        ordFromDb.setRout("MSK");
        ordDao.update(ordFromDb);
        Ord ordFromDb2 = ordDao.findById(id);
        assertEquals("MSK", ordFromDb2.getRout());
    }

    @Test
    public void testAdd() throws Exception {
        Ord ord = new Ord();
        ord.setRout("SPb");
        ordDao.add(ord);
        assertTrue(ord.getId() > 0);
    }

    @Test
    public void testDelete() throws Exception {
        Ord ord = new Ord();
        ord.setRout("SPb");
        ordDao.add(ord);
        long id = ord.getId();
        ordDao.delete(ord);
        Ord ordFromDb = ordDao.findById(id);
        assertNull(ordFromDb);
    }

    @Test
    public void testFindById() {
        Ord ord = new Ord();
        ord.setRout("SPb");
        ordDao.add(ord);
        long id = ord.getId();
        Ord ordFromDb = ordDao.findById(id);
        assertEquals(ordFromDb.getRout(), ord.getRout());
    }

    @Test
    public void testFindAll() {
        Ord ord = new Ord();
        ord.setRout("SPb");
        ordDao.add(ord);
        List<Ord> ords = ordDao.findAll();
        assertTrue(!ords.isEmpty());
    }

    @Test
    public void testFindAllByStatus() {
        Ord ord1 = new Ord();
        Ord ord2 = new Ord();
        Ord ord3 = new Ord();
        ord1.setOrdStatus(OrdStatus.ASSIGNED);
        ord2.setOrdStatus(OrdStatus.DONE);
        ord3.setOrdStatus(OrdStatus.ASSIGNED);
        ordDao.add(ord1);
        ordDao.add(ord2);
        ordDao.add(ord3);
        List<Ord> ords = ordDao.findAllByStatus(OrdStatus.ASSIGNED);
        assertEquals(ords.size(), 2);
    }
}