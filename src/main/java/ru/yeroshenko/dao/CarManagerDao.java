package ru.yeroshenko.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.yeroshenko.domain.CarManager;


/**
 * Class with basic methods that service DAO CarManager
 */
public class CarManagerDao {


    private SessionFactory sessionFactory;

    /**
     * @param sessionFactory - a factory to create new Session instances
     */
    public CarManagerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    /**
     *
     * @param id - personal id-number
     * @return CarManager, which was found by id
     */
    public CarManager findById(long id) {
        Session session = sessionFactory.openSession();
        CarManager carManager = (CarManager) session.get(CarManager.class, id);
        session.close();
        return carManager;
    }

}
