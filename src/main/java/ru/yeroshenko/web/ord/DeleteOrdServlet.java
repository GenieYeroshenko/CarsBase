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
 * Class, that creates dynamic content for deleting the Order from data base
 */
public class DeleteOrdServlet extends HttpServlet {
    /**
     * @param request  for getting information from UserForm
     * @param response for redirecting User to the list of all Orders after deleting, or to login page if User is not authorized
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute(LogInServlet.AUTHORIZED_USER);
        if (account == null) {
            response.sendRedirect("/login");
            return;
        } else if (account instanceof CabDriver) {
            response.sendRedirect("/list-ord-driver");
            return;
        }

        response.setContentType("text/html");
        long id = Long.parseLong(request.getParameter("id"));

        ServletContext context = request.getSession().getServletContext();
        OrdDao ordDao = (OrdDao) context.getAttribute("ordDao");
        Ord ord = ordDao.findById(id);
        ordDao.delete(ord);

        request.getRequestDispatcher("/list-ord-manager").forward(request, response);
    }
}
