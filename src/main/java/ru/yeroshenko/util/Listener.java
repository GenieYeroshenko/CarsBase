package ru.yeroshenko.util;

import org.hibernate.SessionFactory;
import ru.yeroshenko.dao.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by evgeniya on 21/11/15.
 */
public class Listener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        System.out.println("ServletContextListener started");
        ServletContext servletContext = event.getServletContext();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        AccountDao accountDao = new AccountDao(sessionFactory);
        servletContext.setAttribute("accountDao", accountDao);

        CabDriverDao cabDriverDao = new CabDriverDao(sessionFactory);
        servletContext.setAttribute("cabDriverDao", cabDriverDao);

        CarDao carDao = new CarDao(sessionFactory);
        servletContext.setAttribute("carDao", carDao);

        CarManagerDao carManagerDao = new CarManagerDao(sessionFactory);
        servletContext.setAttribute("carManagerDao", carManagerDao);

        OrdDao ordDao = new OrdDao(sessionFactory);
        servletContext.setAttribute("ordDao", ordDao);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
