package ru.yeroshenko.web.ord;

import ru.yeroshenko.dao.OrdDao;
import ru.yeroshenko.domain.Account;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.domain.Ord;
import ru.yeroshenko.web.user.LogInServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by evgeniya on 15/11/15.
 */
public class ListOrdCarManagerServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute(LogInServlet.AUTHORIZED_USER);
        if (account == null) {
            response.sendRedirect("/login");
            return;
        }
        if (account instanceof CabDriver) {
            response.sendRedirect("/list-ord-driver");
            return;
        }

        ServletContext context = request.getSession().getServletContext();
        OrdDao ordDao = (OrdDao) context.getAttribute("ordDao");
        String statusFromForm = request.getParameter("ordStatus");
        List<Ord> ords;
        if (statusFromForm == null) {
            ords = ordDao.findAll();
        } else {
            Ord.OrdStatus ordStatus = Ord.OrdStatus.valueOf(statusFromForm);
            ords = ordDao.findAllByStatus(ordStatus);
        }


        request.setAttribute("newListOfOrds", ords);
        request.getRequestDispatcher("/jsp/ord/ords-list-manager.jsp").forward(request, response);
    }
}


