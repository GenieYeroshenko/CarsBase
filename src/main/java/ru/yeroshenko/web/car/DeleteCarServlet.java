package ru.yeroshenko.web.car;

import com.sun.xml.internal.ws.handler.HandlerException;
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
public class DeleteCarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("authorizedUser") == null) {
            response.sendRedirect("/login");
            return;
        }

        response.setContentType("text/html");
        String idFromForm = request.getParameter("id");

        long id = Long.parseLong(idFromForm);

        CarDao carDao = new CarDao(HibernateUtil.getSessionFactory());
        Car car = carDao.findById(id);
        try {

            carDao.delete(car);
            //TODO check exception type
        } catch (HandlerException e) {
            // todo see at login servlet
            request.setAttribute("error", "");
        }

        request.getRequestDispatcher("/list-car").forward(request, response);
    }


}
