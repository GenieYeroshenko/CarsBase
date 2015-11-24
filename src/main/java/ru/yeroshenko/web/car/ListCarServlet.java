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
import java.util.List;

/**
 * Class, that creates dynamic content to display the information about all Cars from data base
 */
public class ListCarServlet extends HttpServlet {

    /**
     * @param request  to display the information about all Cars from data base
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
        CabDriver cabDriver = (CabDriver) account;

        ServletContext context = request.getSession().getServletContext();
        CarDao carDao = (CarDao) context.getAttribute("carDao");
        List<Car> cars = carDao.findAllByDriver(cabDriver);

        request.setAttribute("newListOfCars", cars);
        request.getRequestDispatcher("/jsp/car/cars-list.jsp").forward(request, response);
    }


}
