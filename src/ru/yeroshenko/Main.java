package ru.yeroshenko;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.yeroshenko.domain.*;
import ru.yeroshenko.util.HibernateUtil;


/**
 * Created by Genie Yeroshenko on 08/11/15.
 */
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.persist(new Car());
        session.persist(new Ord());
        session.persist(new Trip());

        session.close();
    }
}
