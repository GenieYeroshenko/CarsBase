package ru.yeroshenko.web.ord;

import ru.yeroshenko.dao.CarDao;
import ru.yeroshenko.dao.OrdDao;
import ru.yeroshenko.domain.Account;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.domain.Car;
import ru.yeroshenko.domain.Ord;
import ru.yeroshenko.web.user.LogInServlet;

import javax.servlet.ServletContext;
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
//todo
public class AddOrdServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        long carId = Integer.parseInt(request.getParameter("carId"));
        LocalDate date = LocalDate.now();
        String rout = request.getParameter("rout");
        Boolean carTypeLorry = Boolean.parseBoolean(request.getParameter("carTypeLorry"));
        Ord.OrdStatus ordStatus = Ord.OrdStatus.valueOf(request.getParameter("ordStatus"));

        Ord ord = new Ord();
        ord.setCarTypeLorry(carTypeLorry);
        ord.setRout(rout);
        ord.setOrdStatus(ordStatus);
        ord.setDate(date);

        ServletContext context = request.getSession().getServletContext();
        OrdDao ordDao = (OrdDao) context.getAttribute("ordDao");
        ordDao.createOrd(ord, carId);
        response.sendRedirect("/list-ord-manager");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute(LogInServlet.AUTHORIZED_USER);
        if (account == null) {
            response.sendRedirect("/login");
            return;
        } else if (account instanceof CabDriver) {
            response.sendRedirect("/list-ord-driver");
        }

        response.setContentType("text/html");

        ServletContext context = request.getSession().getServletContext();
        CarDao carDao = (CarDao) context.getAttribute("carDao");
        List<Car> cars = carDao.findAll();

        request.setAttribute("newListOfCars", cars);
        request.getRequestDispatcher("/jsp/ord/add-ord.jsp").forward(request, response);
    }
}