package ru.yeroshenko.web;

import ru.yeroshenko.dao.CarDao;
import ru.yeroshenko.domain.Car;
import ru.yeroshenko.util.HibernateUtil;

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

        CarDao carDao = new CarDao(HibernateUtil.getSessionFactory());
        carDao.add(car);

        request.getRequestDispatcher("/list-car").forward(request, response);
    }


}
