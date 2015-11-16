package ru.yeroshenko.web;

import ru.yeroshenko.dao.TripDao;
import ru.yeroshenko.domain.Trip;
import ru.yeroshenko.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by evgeniya on 15/11/15.
 */
public class ListTripServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TripDao tripDao = new TripDao(HibernateUtil.getSessionFactory());
        List<Trip> trips = tripDao.findAll();

        request.setAttribute("newListOfTrips", trips);
        request.getRequestDispatcher("/jsp/trips-list.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
