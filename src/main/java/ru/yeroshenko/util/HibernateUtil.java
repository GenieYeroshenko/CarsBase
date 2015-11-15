package ru.yeroshenko.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

//import org.hibernate.cfg.AnnotationConfiguration;
//import org.hibernate.*;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    static {
        try {
            // todo rework deprecated
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();


        //    sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
         } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


//    private static final SessionFactory sessionFactory;
//    static {
//        try {
//            sessionFactory = new AnnotationConfiguration()
//                    .configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            // Log exception!
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static Session getSession()
//            throws HibernateException {
//        return sessionFactory.openSession();
//    }
}