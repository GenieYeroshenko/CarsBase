package ru.yeroshenko.web.ord;

import org.hibernate.SessionFactory;
import ru.yeroshenko.dao.CarDao;
import ru.yeroshenko.dao.OrdDao;
import ru.yeroshenko.domain.Ord;
import ru.yeroshenko.service.OrdService;
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
public class UpdateOrdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        String idFromFormToUpdate = request.getParameter("id");

        long id = Long.parseLong(idFromFormToUpdate);

        OrdDao ordDao = new OrdDao(HibernateUtil.getSessionFactory());
        Ord ord = ordDao.findById(id);

        request.setAttribute("updatedOrd", ord);
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


        Ord ord = new Ord();
        ord.setCarTypeLorry(carTypeLorry);
        ord.setRout(rout);
        ord.setOrdStatus(ordStatus);
        ord.setDate(date);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        OrdService service = new OrdService(new CarDao(sessionFactory), new OrdDao(sessionFactory));



        OrdDao ordDao = new OrdDao(HibernateUtil.getSessionFactory());

        Ord updatedOrd = ordDao.findById(id);
        updatedOrd.setCarTypeLorry(carTypeLorry);
        updatedOrd.setRout(rout);
        updatedOrd.setOrdStatus(ordStatus);
        updatedOrd.setDate(date);

        service.updateOrd(updatedOrd, carId);
        //ordDao.update(updatedOrd);

        //request.getRequestDispatcher("/jsp/cars-list.jsp").forward(request, response);
        request.getRequestDispatcher("/list-ord").forward(request, response);

    }
}
