package ru.yeroshenko.web.ord;

import org.hibernate.SessionFactory;
import ru.yeroshenko.dao.CarDao;
import ru.yeroshenko.dao.OrdDao;
import ru.yeroshenko.domain.Account;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.domain.Car;
import ru.yeroshenko.domain.Ord;
import ru.yeroshenko.util.HibernateUtil;
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
        //String dateFromForm = request.getParameter("date");
        String routFromForm = request.getParameter("rout");
        String carTypeLorryFromForm = request.getParameter("carTypeLorry");
        String carFromForm = request.getParameter("carId");
        String ordStatusFromForm = request.getParameter("ordStatus");

        LocalDate date = LocalDate.now();
        String rout = routFromForm;
        Boolean carTypeLorry = Boolean.parseBoolean(carTypeLorryFromForm);
        Ord.OrdStatus ordStatus = Ord.OrdStatus.valueOf(ordStatusFromForm);


        Ord ord = new Ord();
        ord.setCarTypeLorry(carTypeLorry);
        ord.setRout(rout);
        ord.setOrdStatus(ordStatus);
        ord.setDate(date);
        long carId = Integer.parseInt(carFromForm);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        ServletContext context = request.getSession().getServletContext();
        OrdDao ordDao = (OrdDao) context.getAttribute("ordDao");

        //OrdDao dao = new OrdDao(sessionFactory);
        ordDao.createOrd(ord, carId);
        response.sendRedirect("/list-ord");

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
        //CarDao carDao = new CarDao(HibernateUtil.getSessionFactory());
        List<Car> cars = carDao.findAll();

        request.setAttribute("newListOfCars", cars);
        request.getRequestDispatcher("/jsp/ord/add-ord.jsp").forward(request, response);


    }
}