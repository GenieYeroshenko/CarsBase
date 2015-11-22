package ru.yeroshenko.web.car;

import ru.yeroshenko.dao.CarDao;
import ru.yeroshenko.domain.Account;
import ru.yeroshenko.domain.Car;
import ru.yeroshenko.domain.CarManager;
import ru.yeroshenko.web.user.LogInServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by evgeniya on 15/11/15.
 */
public class ChangeCarStatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute(LogInServlet.AUTHORIZED_USER);
        if (account == null) {
            response.sendRedirect("/login");
            return;
        } else if (account instanceof CarManager) {
            response.sendRedirect("/list-ord-manager");
            return;
        }

        response.setContentType("text/html");
        long id = Long.parseLong(request.getParameter("id"));

        ServletContext context = request.getSession().getServletContext();
        CarDao carDao = (CarDao) context.getAttribute("carDao");
        Car car = carDao.findById(id);
        car.setCarStatus(!car.getCarStatus());

        carDao.update(car);
        response.sendRedirect("/list-car");
    }
}
