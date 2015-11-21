package ru.yeroshenko.web.car;

import ru.yeroshenko.dao.CarDao;
import ru.yeroshenko.domain.Car;

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
        if (request.getSession().getAttribute("authorizedUser") == null) {
            response.sendRedirect("/login");
            return;
        }

        response.setContentType("text/html");
        String idFromForm = request.getParameter("id");

        long id = Long.parseLong(idFromForm);

        ServletContext context = request.getSession().getServletContext();
        CarDao carDao = (CarDao) context.getAttribute("carDao");

        //CarDao carDao = new CarDao(HibernateUtil.getSessionFactory());
        Car car = carDao.findById(id);
        car.setCarStatus(!car.getCarStatus());

        carDao.update(car);

        //request.getRequestDispatcher("/list-car").forward(request, response);
        response.sendRedirect("/list-car");

    }


}
