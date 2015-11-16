package ru.yeroshenko.web;

import ru.yeroshenko.dao.OrdDao;
import ru.yeroshenko.domain.Ord;
import ru.yeroshenko.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by evgeniya on 15/11/15.
 */
//todo
public class AddOrdServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String carTypeLorryFromForm = request.getParameter("carTypeLorry");
        String routFromForm = request.getParameter("rout");
        //String ordStatusFromForm = request.getParameter("ordStatus");
        //String carFromForm = request.getParameter("licencePlate");
        //String cabDriverFromForm = request.getParameter("cabDriver.name");
        //String date =

        Boolean carTypeLorry = Boolean.parseBoolean(carTypeLorryFromForm);
        String rout = routFromForm;
        //OrdStatus ordStatus = OrdStatus.valueOf(ordStatusFromForm);//?????????????
        //Date date = new Date();//????????????????????????????????????????????????????


        Ord ord = new Ord();
        ord.setCarTypeLorry(carTypeLorry);
        ord.setRout(rout);
        //ord.setOrdStatus(ordStatus);
        //ord.setDate(date);
        //ord.setCar(carFromForm);

        OrdDao ordDao = new OrdDao(HibernateUtil.getSessionFactory());
        ordDao.add(ord);

        request.getRequestDispatcher("/list-ord").forward(request, response);
    }


}
