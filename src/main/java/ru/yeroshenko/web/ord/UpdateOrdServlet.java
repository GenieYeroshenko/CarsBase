package ru.yeroshenko.web.ord;

import ru.yeroshenko.dao.CarDao;
import ru.yeroshenko.dao.OrdDao;
import ru.yeroshenko.domain.Car;
import ru.yeroshenko.domain.Ord;
import ru.yeroshenko.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by evgeniya on 15/11/15.
 */
public class UpdateOrdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        String idFromFormToUpdate = request.getParameter("id");

        long id = Long.parseLong(idFromFormToUpdate);

        OrdDao ordDao = new OrdDao(HibernateUtil.getSessionFactory());
        Ord ord = ordDao.findById(id);

        request.setAttribute("updatedOrd", ord);

        CarDao carDao = new CarDao(HibernateUtil.getSessionFactory());
        List<Car> cars = carDao.findAll();

        request.setAttribute("newListOfCars", cars);
        request.getRequestDispatcher("/jsp/update-ord.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        String idFromForm = request.getParameter("id");
        String routFromForm = request.getParameter("rout");
        String carTypeLorryFromForm = request.getParameter("carTypeLorry");

        String carIdFromForm = request.getParameter("carId");
        String ordStatusFromForm = request.getParameter("ordStatus");


        long id = Long.parseLong(idFromForm);
        LocalDate date = LocalDate.now();
        String rout = routFromForm;
        Boolean carTypeLorry = Boolean.parseBoolean(carTypeLorryFromForm);
        Ord.OrdStatus ordStatus = Ord.OrdStatus.valueOf(ordStatusFromForm);
        long carId = Integer.parseInt(carIdFromForm);

        OrdDao ordDao = new OrdDao(HibernateUtil.getSessionFactory());

        Ord updatedOrd = ordDao.findById(id);
        updatedOrd.setCarTypeLorry(carTypeLorry);
        updatedOrd.setRout(rout);
        updatedOrd.setOrdStatus(ordStatus);
        updatedOrd.setDate(date);

        ordDao.updateOrd(updatedOrd, carId);

        response.sendRedirect("/list-ord");
//        request.getRequestDispatcher("/list-ord").forward(request, response);

    }
}
