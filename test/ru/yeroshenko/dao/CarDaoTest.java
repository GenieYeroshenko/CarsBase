package ru.yeroshenko.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ru.yeroshenko.domain.Car;
import ru.yeroshenko.util.HibernateUtil;

import static org.junit.Assert.*;

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


    }

    @Test
    public void testUpdate() throws Exception {
        Car car = new Car();
        car.setLicencePlate("EN 2222");
        car.setModel("bmw");

        carDao.update(car);


    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testFindById() throws Exception {

    }
}