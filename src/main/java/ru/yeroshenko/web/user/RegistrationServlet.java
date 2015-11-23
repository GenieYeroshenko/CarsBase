package ru.yeroshenko.web.user;

import ru.yeroshenko.dao.AccountDao;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.domain.CarManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by evgeniya on 15/11/15.
 */
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

        ServletContext context = request.getSession().getServletContext();
        AccountDao accountDao = (AccountDao) context.getAttribute("accountDao");

        if ((accountDao.countAccountsWithLogin(login)) > 0) {
            request.setAttribute("error", "пользователь с такм логином уже существует");
            request.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
        } else if (role.equals("cabDriver")) {
            CabDriver cabDriver = new CabDriver();
            cabDriver.setLogin(login);
            cabDriver.setPassword(password);
            accountDao.add(cabDriver);
            request.getSession().setAttribute(LogInServlet.AUTHORIZED_USER, cabDriver);
            response.sendRedirect("/list-ord-driver");
            return;
        } else if (role.equals("carManager")) {
            CarManager carManager = new CarManager();
            carManager.setLogin(login);
            carManager.setPassword(password);
            accountDao.add(carManager);
            request.getSession().setAttribute(LogInServlet.AUTHORIZED_USER, carManager);
            response.sendRedirect("/list-ord-manager");
            return;
        }
    }
}
