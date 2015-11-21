package ru.yeroshenko.web.ord;

import ru.yeroshenko.dao.OrdDao;
import ru.yeroshenko.domain.Account;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.domain.CarManager;
import ru.yeroshenko.domain.Ord;
import ru.yeroshenko.util.HibernateUtil;
import ru.yeroshenko.web.user.LogInServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by evgeniya on 15/11/15.
 */
public class ListOrdCabDriverServlet extends HttpServlet {

    //todo check
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute(LogInServlet.AUTHORIZED_USER);
        if (account == null) {
            response.sendRedirect("/login");
            return;
        }
        if (account instanceof CarManager) {
            response.sendRedirect("/list-ord-manager");
            return;
        }
        CabDriver cabDriver = (CabDriver) account;

        OrdDao ordDao = new OrdDao(HibernateUtil.getSessionFactory());
        List<Ord> ords = ordDao.findAllByDriver(cabDriver);

        request.setAttribute("newListOfOrdsByDriver", ords);
        request.getRequestDispatcher("/jsp/ord/ords-list-driver.jsp").forward(request, response);

    }

}


