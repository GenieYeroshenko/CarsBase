package ru.yeroshenko.dao;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.domain.Car;
import ru.yeroshenko.domain.Ord;
import ru.yeroshenko.domain.Ord.OrdStatus;
import ru.yeroshenko.util.HibernateUtil;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Genie Yeroshenko on 10/11/15.
 */
public class OrdDaoTest {

    private OrdDao ordDao;
    private CarDao carDao;
    private AccountDao accountDao;

    @Before
    public void setUp() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        ordDao = new OrdDao(sessionFactory);
        carDao = new CarDao(sessionFactory);
        accountDao = new AccountDao(sessionFactory);
    }

    @Test
    public void testUpdateOrd() throws Exception {
        Car car = getCar();

        Ord ord = new Ord();
        ord.setRout("SPb");
        ordDao.createOrd(ord, car.getId());
        long idOrd = ord.getId();
        Ord ordFromDb = ordDao.findById(idOrd);

        ordFromDb.setRout("MSK");

        ordDao.updateOrd(ordFromDb, idOrd);
        Ord ordFromDb2 = ordDao.findById(idOrd);
        assertEquals("MSK", ordFromDb2.getRout());
        ordDao.delete(ordFromDb2);
        carDao.delete(car);
    }

    @Test
    public void testDelete() throws Exception {
        Ord ord = new Ord();
        ord.setRout("SPb");
        Car car = new Car();
        car.setModel("kia333");
        carDao.add(car);

        ordDao.createOrd(ord, car.getId());
        long idOrd = ord.getId();
        ordDao.delete(ord);
        Ord ordFromDb = ordDao.findById(idOrd);

        carDao.delete(car);

        assertNull(ordFromDb);
    }

    @Test
    public void testFindById() {
        Ord ord = new Ord();
        ord.setRout("SPb");
        Car car = new Car();
        car.setModel("kia222");
        CabDriver cabDriver = new CabDriver();
        cabDriver.setLogin("forkia222");
        car.setCabDriver(cabDriver);
        carDao.add(car);

        ordDao.createOrd(ord, car.getId());
        long idOrd = ord.getId();
        Ord ordFromDb = ordDao.findById(idOrd);

        ordDao.delete(ord);
        carDao.delete(car);
        accountDao.delete(ord.getCar().getCabDriver());

        assertEquals(ordFromDb.getRout(), ord.getRout());
    }

    @Test
    public void testFindAll() {
        Car car = getCar();

        Ord ord1 = new Ord();
        ord1.setRout("NY");
        ordDao.createOrd(ord1, car.getId());


        Ord ord2 = new Ord();
        ord2.setRout("NY");
        ordDao.createOrd(ord2, car.getId());
        List<Ord> ords = ordDao.findAll();

        assertEquals(2, ords.size());

        ordDao.delete(ord1);
        ordDao.delete(ord2);
        carDao.delete(car);
    }

    @Test
    public void testFindAllByStatus() {
        Ord ord1 = new Ord();
        Ord ord2 = new Ord();
        Ord ord3 = new Ord();
        ord1.setOrdStatus(OrdStatus.ASSIGNED);
        ord2.setOrdStatus(OrdStatus.DONE);
        ord3.setOrdStatus(OrdStatus.ASSIGNED);

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        carDao.add(car1);
        carDao.add(car2);
        carDao.add(car3);

        long idCar1 = car1.getId();
        long idCar2 = car2.getId();
        long idCar3 = car3.getId();

        ordDao.createOrd(ord1, idCar1);
        ordDao.createOrd(ord2, idCar2);
        ordDao.createOrd(ord3, idCar3);

        List<Ord> ords = ordDao.findAllByStatus(OrdStatus.ASSIGNED);
        assertEquals(ords.size(), 2);
        ordDao.delete(ord1);
        ordDao.delete(ord2);
        ordDao.delete(ord3);
        carDao.delete(car1);
        carDao.delete(car2);
        carDao.delete(car3);
    }

    @Test
    public void testCreateOrd() throws Exception {
        Car carFromDb = getCar();

        Ord ord = new Ord();
        ord.setRout("SPb");
        ord.setCar(carFromDb);

        ordDao.createOrd(ord, carFromDb.getId());
        long idOrd = ord.getId();
        Ord ordFromDb = ordDao.findById(idOrd);

        assertEquals(ordFromDb.getRout(), ordFromDb.getRout());
        ordDao.delete(ord);
        carDao.delete(carFromDb);
    }

    private Car getCar() {
        Car car = new Car();
        car.setLicencePlate("33 eee");
        carDao.add(car);
        return car;
    }

    @Test
    public void testFindAllByDriver() {

        CabDriver cabDriver1 = new CabDriver();
        CabDriver cabDriver3 = new CabDriver();
        accountDao.add(cabDriver1);
        accountDao.add(cabDriver3);

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        car1.setCabDriver(cabDriver1);
        car2.setCabDriver(cabDriver1);
        car3.setCabDriver(cabDriver3);
        carDao.add(car1, cabDriver1.getId());
        carDao.add(car2, cabDriver1.getId());
        carDao.add(car3, cabDriver3.getId());

        Ord ord1 = new Ord();
        Ord ord2 = new Ord();
        Ord ord3 = new Ord();

        long idCar1 = car1.getId();
        long idCar2 = car2.getId();
        long idCar3 = car3.getId();

        ordDao.createOrd(ord1, idCar1);
        ordDao.createOrd(ord2, idCar2);
        ordDao.createOrd(ord3, idCar3);

        List<Ord> ords = ordDao.findAllByDriver(cabDriver1);
        ordDao.delete(ord1);
        ordDao.delete(ord2);
        ordDao.delete(ord3);

        carDao.delete(car1);
        carDao.delete(car2);
        carDao.delete(car3);
        accountDao.delete(cabDriver1);
        accountDao.delete(cabDriver3);

        assertEquals(ords.size(), 2);
    }

    @Test
    public void testFindAllByDriverAndOrdStatuses() {

        CabDriver cabDriver1 = new CabDriver();
        CabDriver cabDriver3 = new CabDriver();
        accountDao.add(cabDriver1);
        accountDao.add(cabDriver3);

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        carDao.add(car1, cabDriver1.getId());
        carDao.add(car2, cabDriver1.getId());
        carDao.add(car3, cabDriver3.getId());

        Ord ord1 = new Ord();
        Ord ord2 = new Ord();
        Ord ord3 = new Ord();

        ord1.setOrdStatus(OrdStatus.DONE);
        ord2.setOrdStatus(OrdStatus.IN_QUEUE);
        ord3.setOrdStatus(OrdStatus.ASSIGNED);

        long idCar1 = car1.getId();
        long idCar2 = car2.getId();
        long idCar3 = car3.getId();

        ordDao.createOrd(ord1, idCar1);
        ordDao.createOrd(ord2, idCar2);
        ordDao.createOrd(ord3, idCar3);

        OrdStatus[] statuses = {OrdStatus.DONE};
        List<Ord> ords = ordDao.findAllByDriverAndOrdStatuses(cabDriver1, statuses);

        ordDao.delete(ord1);
        ordDao.delete(ord2);
        ordDao.delete(ord3);

        carDao.delete(car1);
        carDao.delete(car2);
        carDao.delete(car3);
        accountDao.delete(cabDriver1);
        accountDao.delete(cabDriver3);

        assertEquals(ords.size(), 1);
    }
}
