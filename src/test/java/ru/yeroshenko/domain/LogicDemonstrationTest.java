package ru.yeroshenko.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.yeroshenko.dao.*;
import ru.yeroshenko.util.HibernateUtil;

import java.time.LocalDate;

/**
 * Class, wich creates all domain objects for DEMO
 */
public abstract class LogicDemonstrationTest {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        AccountDao accountDao = new AccountDao(sessionFactory);
        CabDriverDao cabDriverDao = new CabDriverDao(sessionFactory);
        CarManagerDao carManagerDao = new CarManagerDao(sessionFactory);
        CarDao carDao = new CarDao(sessionFactory);
        OrdDao ordDao = new OrdDao(sessionFactory);


//Create CabDrivers (5 accounts)
        CabDriver cabDriver1 = new CabDriver();
        cabDriver1.setLogin("Tom");
        cabDriver1.setPassword("Tom");

        CabDriver cabDriver2 = new CabDriver();
        cabDriver2.setLogin("Sam");
        cabDriver2.setPassword("Sam");

        CabDriver cabDriver3 = new CabDriver();
        cabDriver3.setLogin("Nick");
        cabDriver3.setPassword("Nick");

        CabDriver cabDriver4 = new CabDriver();
        cabDriver4.setLogin("Stiv");
        cabDriver4.setPassword("Stiv");

        CabDriver cabDriver5 = new CabDriver();
        cabDriver5.setLogin("Dan");
        cabDriver5.setPassword("Dan");

        accountDao.add(cabDriver1);
        accountDao.add(cabDriver2);
        accountDao.add(cabDriver3);
        accountDao.add(cabDriver4);
        accountDao.add(cabDriver5);


//Create CarManagers (1 account)
        CarManager carManager = new CarManager();
        carManager.setLogin("Ann");
        carManager.setPassword("Ann");

        accountDao.add(carManager);


//Create Cars (9 cars)
        Car car1 = new Car();
        car1.setCabDriver(cabDriver1);
        car1.setCarStatus(true);
        car1.setLicencePlate("A 111 AA 11 RUS");
        car1.setCarTypeLorry(false);
        car1.setModel("Ferrari");

        Car car2 = new Car();
        car2.setCabDriver(cabDriver1);
        car2.setCarStatus(false);
        car2.setLicencePlate("A 222 AA 11 RUS");
        car2.setCarTypeLorry(false);
        car2.setModel("Rolls-Royce");

        Car car3 = new Car();
        car3.setCabDriver(cabDriver1);
        car3.setCarStatus(true);
        car3.setLicencePlate("A 333 AA 11 RUS");
        car3.setCarTypeLorry(false);
        car3.setModel("Bentley");

        Car car4 = new Car();
        car4.setCabDriver(cabDriver1);
        car4.setCarStatus(true);
        car4.setLicencePlate("A 111 AA 11 RUS");
        car4.setCarTypeLorry(true);
        car4.setModel("LAZ");

        Car car5 = new Car();
        car5.setCabDriver(cabDriver1);
        car5.setCarStatus(true);
        car5.setLicencePlate("A 555 AA 11 RUS");
        car5.setCarTypeLorry(false);
        car5.setModel("Lamborghini");

        Car car6 = new Car();
        car6.setCabDriver(cabDriver2);
        car6.setCarStatus(false);
        car6.setLicencePlate("A 666 AA 11 RUS");
        car6.setCarTypeLorry(false);
        car6.setModel("Porsche");

        Car car7 = new Car();
        car7.setCabDriver(cabDriver3);
        car7.setCarStatus(true);
        car7.setLicencePlate("A 777 AA 11 RUS");
        car7.setCarTypeLorry(false);
        car7.setModel("BMW");

        Car car8 = new Car();
        car8.setCabDriver(cabDriver4);
        car8.setCarStatus(true);
        car8.setLicencePlate("A 888 AA 11 RUS");
        car8.setCarTypeLorry(false);
        car8.setModel("Jaguar");

        Car car9 = new Car();
        car9.setCabDriver(cabDriver5);
        car9.setCarStatus(true);
        car9.setLicencePlate("A 999 AA 11 RUS");
        car9.setCarTypeLorry(true);
        car9.setModel("ZIL");


        carDao.add(car1, cabDriver1.getId());
        carDao.add(car2, cabDriver1.getId());
        carDao.add(car3, cabDriver1.getId());
        carDao.add(car4, cabDriver1.getId());
        carDao.add(car5, cabDriver1.getId());
        carDao.add(car6, cabDriver2.getId());
        carDao.add(car7, cabDriver3.getId());
        carDao.add(car8, cabDriver4.getId());
        carDao.add(car9, cabDriver5.getId());


//Create Orders (9 orders)
        Ord ord1 = new Ord();
        ord1.setCar(car8);
        ord1.setOrdStatus(Ord.OrdStatus.IN_QUEUE);
        ord1.setRout("SPb - MOW");
        ord1.setCarTypeLorry(false);
        ord1.setDate(LocalDate.now());

        Ord ord2 = new Ord();
        ord2.setCar(car2);
        ord2.setOrdStatus(Ord.OrdStatus.ASSIGNED);
        ord2.setRout("MOW - SPb");
        ord2.setCarTypeLorry(false);
        ord2.setDate(LocalDate.now());

        Ord ord3 = new Ord();
        ord3.setCar(car3);
        ord3.setOrdStatus(Ord.OrdStatus.IN_TRANSIT);
        ord3.setRout("SPb - Urjupinsk");
        ord3.setCarTypeLorry(false);
        ord3.setDate(LocalDate.now());

        Ord ord4 = new Ord();
        ord4.setCar(car4);
        ord4.setOrdStatus(Ord.OrdStatus.DONE);
        ord4.setRout("SPb - MOW");
        ord4.setCarTypeLorry(true);
        ord4.setDate(LocalDate.now());

        Ord ord5 = new Ord();
        ord5.setCar(car8);
        ord5.setOrdStatus(Ord.OrdStatus.IN_QUEUE);
        ord5.setRout("SPb - MOW");
        ord5.setCarTypeLorry(false);
        ord5.setDate(LocalDate.now());

        Ord ord6 = new Ord();
        ord6.setCar(car5);
        ord6.setOrdStatus(Ord.OrdStatus.IN_QUEUE);
        ord6.setRout("MOW - SPb");
        ord6.setCarTypeLorry(false);
        ord6.setDate(LocalDate.now());

        Ord ord7 = new Ord();
        ord7.setCar(car6);
        ord7.setOrdStatus(Ord.OrdStatus.IN_QUEUE);
        ord7.setRout("SPb - MOW");
        ord7.setCarTypeLorry(false);
        ord7.setDate(LocalDate.now());

        Ord ord8 = new Ord();
        ord8.setCar(car6);
        ord8.setOrdStatus(Ord.OrdStatus.ASSIGNED);
        ord8.setRout("MOW - SPb");
        ord8.setCarTypeLorry(false);
        ord8.setDate(LocalDate.now());

        Ord ord9 = new Ord();
        ord9.setCar(car9);
        ord9.setOrdStatus(Ord.OrdStatus.ASSIGNED);
        ord9.setRout("SPb - MOW");
        ord9.setCarTypeLorry(true);
        ord9.setDate(LocalDate.now());

        ordDao.createOrd(ord1, car8.getId());
        ordDao.createOrd(ord2, car2.getId());
        ordDao.createOrd(ord3, car3.getId());
        ordDao.createOrd(ord4, car4.getId());
        ordDao.createOrd(ord5, car8.getId());
        ordDao.createOrd(ord6, car7.getId());
        ordDao.createOrd(ord7, car6.getId());
        ordDao.createOrd(ord8, car6.getId());
        ordDao.createOrd(ord9, car9.getId());

    }
}

