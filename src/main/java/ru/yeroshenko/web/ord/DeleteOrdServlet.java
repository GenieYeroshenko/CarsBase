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

/**
 * Created by evgeniya on 15/11/15.
 */
public class DeleteOrdServlet extends HttpServlet {

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
        String idFromForm = request.getParameter("id");
        long id = Long.parseLong(idFromForm);

        ServletContext context = request.getSession().getServletContext();
        OrdDao ordDao = (OrdDao) context.getAttribute("ordDao");
        Ord ord = ordDao.findById(id);
        ordDao.delete(ord);

        request.getRequestDispatcher("/list-ord-manager").forward(request, response);
    }
}
