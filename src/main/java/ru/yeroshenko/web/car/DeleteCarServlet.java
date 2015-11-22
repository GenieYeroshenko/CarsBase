package ru.yeroshenko.web.car;

import com.sun.xml.internal.ws.handler.HandlerException;
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
public class DeleteCarServlet extends HttpServlet {

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
        String idFromForm = request.getParameter("id");
        long id = Long.parseLong(idFromForm);

        ServletContext context = request.getSession().getServletContext();
        CarDao carDao = (CarDao) context.getAttribute("carDao");
        Car car = carDao.findById(id);
        try {
            carDao.delete(car);
            //TODO check exception type
        } catch (HandlerException e) {
            // todo
            request.setAttribute("error", "у удаляемого элемента есть связи");
        }

        request.getRequestDispatcher("/list-car").forward(request, response);
    }
}
