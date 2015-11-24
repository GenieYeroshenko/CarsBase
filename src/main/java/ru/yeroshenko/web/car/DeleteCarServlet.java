package ru.yeroshenko.web.car;

import org.hibernate.exception.ConstraintViolationException;
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
 * Class, that creates dynamic content for deleting the Car from data base
 */
public class DeleteCarServlet extends HttpServlet {
    /**
     * @param request  for getting information from UserForm
     * @param response for redirecting User to the list of all Cars after deleting, or to login page if User is not authorized
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
        try {
            carDao.delete(car);
        } catch (ConstraintViolationException e) {
            request.setAttribute("error", "There is an order for this car, deleting is forbidden");
            request.getRequestDispatcher("/jsp/car/cars-delete.jsp").forward(request, response);
            return;
        }
        response.sendRedirect("/list-car");
    }
}
