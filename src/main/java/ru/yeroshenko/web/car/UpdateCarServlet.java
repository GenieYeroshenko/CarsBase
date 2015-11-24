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
 * Class, that creates dynamic content for updating the Car from data base
 */
public class UpdateCarServlet extends HttpServlet {

    /**
     * @param request  for forvarding to Updating form
     * @param response for getting information about authorization for data security
     * @throws ServletException
     * @throws IOException
     */
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

        request.setAttribute("updatedCar", car);
        request.getRequestDispatcher("/jsp/car/update-car.jsp").forward(request, response);
    }

    /**
     *
     * @param request for getting information about updating filds of the Car from UserForm
     * @param response for redirecting User to the list of all Cars after updating
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String model = request.getParameter("model");
        String licencePlate = request.getParameter("licencePlate");
        Boolean carStatus = Boolean.parseBoolean(request.getParameter("carStatus"));
        Boolean carTypeLorry = Boolean.parseBoolean(request.getParameter("carTypeLorry"));

        ServletContext context = request.getSession().getServletContext();
        CarDao carDao = (CarDao) context.getAttribute("carDao");
        Car updatedCar = carDao.findById(id);

        updatedCar.setModel(model);
        updatedCar.setLicencePlate(licencePlate);
        updatedCar.setCarStatus(carStatus);
        updatedCar.setCarTypeLorry(carTypeLorry);

        carDao.update(updatedCar);
        response.sendRedirect("/list-car");
    }
}
