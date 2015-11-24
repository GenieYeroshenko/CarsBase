package ru.yeroshenko.web.ord;

import ru.yeroshenko.dao.OrdDao;
import ru.yeroshenko.domain.Account;
import ru.yeroshenko.domain.CarManager;
import ru.yeroshenko.domain.Ord;
import ru.yeroshenko.web.user.LogInServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class, that creates dynamic content for changing Car serviceability status
 */
public class ChangeOrdStatusServlet extends HttpServlet {
    /**
     * @param request  for getting information from UserForm
     * @param response for redirecting User to the list of all Orders changing, or to login page if User is not authorized
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute(LogInServlet.AUTHORIZED_USER);
        if (account == null) {
            response.sendRedirect("/login");
            return;
        } else if (account instanceof CarManager) {
            response.sendRedirect("/list-ord-manager");
            return;
        }

        response.setContentType("text/html");
        long id = Long.parseLong(request.getParameter("id"));
        Ord.OrdStatus status = Ord.OrdStatus.valueOf(request.getParameter("status"));

        ServletContext context = request.getSession().getServletContext();
        OrdDao ordDao = (OrdDao) context.getAttribute("ordDao");

        Ord ord = ordDao.findById(id);
        ord.setOrdStatus(status);

        ordDao.update(ord);
        response.sendRedirect("/list-ord-driver");
    }
}
