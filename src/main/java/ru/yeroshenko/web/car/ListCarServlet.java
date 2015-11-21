package ru.yeroshenko.web.car;

import ru.yeroshenko.dao.CarDao;
import ru.yeroshenko.domain.Car;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by evgeniya on 15/11/15.
 */
public class ListCarServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("authorizedUser") == null) {
            response.sendRedirect("/login");
            return;
        }
        ServletContext context = request.getSession().getServletContext();
        CarDao carDao = (CarDao) context.getAttribute("carDao");

        List<Car> cars = carDao.findAll();

        request.setAttribute("newListOfCars", cars);
        request.getRequestDispatcher("/jsp/car/cars-list.jsp").forward(request, response);


    }
}
