package ru.yeroshenko.web;

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

/**
 * Created by evgeniya on 15/11/15.
 */
//todo
public class AddOrdServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        //String dateFromForm = request.getParameter("date");
        String routFromForm = request.getParameter("rout");
        String carTypeLorryFromForm = request.getParameter("carTypeLorry");
        String carFromForm = request.getParameter("carId");
        String ordStatusFromForm = request.getParameter("ordStatus");

        LocalDate date = LocalDate.now();
        String rout = routFromForm;
        Boolean carTypeLorry = Boolean.parseBoolean(carTypeLorryFromForm);
        Ord.OrdStatus ordStatus = Ord.OrdStatus.valueOf(ordStatusFromForm);//?????????????????????????

        long carId = Integer.parseInt(carFromForm);
        CarDao carDao = new CarDao(HibernateUtil.getSessionFactory());
        Car carFromDB = carDao.findById(carId);


        Ord ord = new Ord();
        ord.setCarTypeLorry(carTypeLorry);
        ord.setRout(rout);
        ord.setOrdStatus(ordStatus);
        ord.setDate(date);
        ord.setCar(carFromDB);

        OrdDao ordDao = new OrdDao(HibernateUtil.getSessionFactory());
        ordDao.add(ord);

        request.getRequestDispatcher("/list-ord").forward(request, response);
    }

}
