package ru.yeroshenko.util;

import org.hibernate.SessionFactory;
import ru.yeroshenko.dao.CarDao;

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
        CarDao carDao = new CarDao(sessionFactory);
        servletContext.setAttribute("carDao", carDao);

    }

    public void contextDestroyed(ServletContextEvent sce) {

    }


}
