package ru.yeroshenko.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.yeroshenko.domain.CarManager;


/**
 * Created by Genie Yeroshenko on 10/11/15.
 */
public class CarManagerDao {


    private SessionFactory sessionFactory;

    public CarManagerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public CarManager findById(long id) {
        Session session = sessionFactory.openSession();
        CarManager carManager = (CarManager) session.get(CarManager.class, id);
        session.close();
        return carManager;
    }

}
