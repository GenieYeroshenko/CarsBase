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
public class UpdateCarServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("authorizedUser") == null) {
            response.sendRedirect("/login");
            return;
        }

        response.setContentType("text/html");
        String idFromFormToUpdate = request.getParameter("id");

        long id = Long.parseLong(idFromFormToUpdate);

        ServletContext context = request.getSession().getServletContext();
        CarDao carDao = (CarDao) context.getAttribute("carDao");
        //CarDao carDao = new CarDao(HibernateUtil.getSessionFactory());
        Car car = carDao.findById(id);

        request.setAttribute("updatedCar", car);

        request.getRequestDispatcher("/jsp/car/update-car.jsp").forward(request, response);




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String modelFromForm = request.getParameter("model");
        String licencePlateFromForm = request.getParameter("licencePlate");
        String carStatusFromForm = request.getParameter("carStatus");
        String carTypeLorryFromForm = request.getParameter("carTypeLorry");
        String idFromForm = request.getParameter("id");

        long id = Long.parseLong(idFromForm);
        String model = modelFromForm;
        String licencePlate = licencePlateFromForm;
        Boolean carStatus = Boolean.parseBoolean(carStatusFromForm);
        Boolean carTypeLorry = Boolean.parseBoolean(carTypeLorryFromForm);

        ServletContext context = request.getSession().getServletContext();
        CarDao carDao = (CarDao) context.getAttribute("carDao");

        //CarDao carDao = new CarDao(HibernateUtil.getSessionFactory());
        Car updatedCar = carDao.findById(id);

        updatedCar.setModel(model);
        updatedCar.setLicencePlate(licencePlate);
        updatedCar.setCarStatus(carStatus);
        updatedCar.setCarTypeLorry(carTypeLorry);

        carDao.update(updatedCar);

        response.sendRedirect("/list-car");
    }
}
