package ru.yeroshenko.web.user;

import ru.yeroshenko.dao.CabDriverDao;
import ru.yeroshenko.dao.CarManagerDao;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.domain.CarManager;
import ru.yeroshenko.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by evgeniya on 15/11/15.
 */
//todo
public class RegistrationServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        //todo login exist

        if (role.equals("cabDriver")) {
            CabDriver cabDriver = new CabDriver();
            cabDriver.setLogin(login);
            cabDriver.setPassword(password);
            CabDriverDao cabDriverDao = new CabDriverDao(HibernateUtil.getSessionFactory());
            cabDriverDao.add(cabDriver);
            request.getSession().setAttribute("authorizedUser", cabDriver);
            response.sendRedirect("/list-trip");
        } else if (role.equals("carManager")) {
            CarManager carManager = new CarManager();
            carManager.setLogin(login);
            carManager.setPassword(password);
            CarManagerDao carManagerDao = new CarManagerDao(HibernateUtil.getSessionFactory());
            carManagerDao.add(carManager);
            request.getSession().setAttribute("authorizedUser", carManager);
            response.sendRedirect("/list-ord");
        }
        //todo else

    }
}
