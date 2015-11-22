package ru.yeroshenko.web.car;

import ru.yeroshenko.dao.CarDao;
import ru.yeroshenko.domain.Account;
import ru.yeroshenko.domain.CabDriver;
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
public class AddCarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String modelFromForm = request.getParameter("model");
        String licencePlateFromForm = request.getParameter("licencePlate");
        String carStatusFromForm = request.getParameter("carStatus");
        String carTypeLorryFromForm = request.getParameter("carTypeLorry");

        String model = modelFromForm;
        String licencePlate = licencePlateFromForm;
        Boolean carStatus = Boolean.parseBoolean(carStatusFromForm);
        Boolean carTypeLorry = Boolean.parseBoolean(carTypeLorryFromForm);

        Car car = new Car();
        car.setModel(model);
        car.setLicencePlate(licencePlate);
        car.setCarStatus(carStatus);
        car.setCarTypeLorry(carTypeLorry);

        ServletContext context = request.getSession().getServletContext();

        CabDriver cabDriver = (CabDriver) request.getSession().getAttribute(LogInServlet.AUTHORIZED_USER);
        CarDao carDao = (CarDao) context.getAttribute("carDao");
        carDao.add(car, cabDriver.getId());
        response.sendRedirect("/list-car");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute(LogInServlet.AUTHORIZED_USER);
        if (account == null) {
            response.sendRedirect("/login");
            return;
        } else if (account instanceof CarManager) {
            response.sendRedirect("/list-ord-manager");
        }

        response.setContentType("text/html");
        request.getRequestDispatcher("/jsp/car/add-car.jsp").forward(request, response);
    }
}
