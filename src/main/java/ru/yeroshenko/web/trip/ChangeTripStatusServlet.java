package ru.yeroshenko.web.trip;

import ru.yeroshenko.dao.TripDao;
import ru.yeroshenko.domain.Trip;
import ru.yeroshenko.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by evgeniya on 15/11/15.
 */
public class ChangeTripStatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("authorizedUser") == null) {
            response.sendRedirect("/login");
            return;
        }

        response.setContentType("text/html");
        String idFromForm = request.getParameter("id");

        long id = Long.parseLong(idFromForm);

        TripDao tripDao = new TripDao(HibernateUtil.getSessionFactory());
        Trip trip = tripDao.findById(id);
        trip.setTripStatus(!trip.getTripStatus());

        tripDao.update(trip);

        request.getRequestDispatcher("/list-trip").forward(request, response);
    }
}
