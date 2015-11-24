package ru.yeroshenko.web.ord;

import ru.yeroshenko.dao.OrdDao;
import ru.yeroshenko.domain.Account;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.domain.CarManager;
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
 * Class, that creates dynamic content to display the information about all assigned Orders for the special CabDriver from data base
 */
public class ListOrdCabDriverServlet extends HttpServlet {
    /**
     * @param request  to display the information about all assigned Orders for the special CabDriver from data base
     * @param response for getting information about authorization for data security
     * @throws ServletException
     * @throws IOException
     */
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
        ServletContext context = request.getSession().getServletContext();
        OrdDao ordDao = (OrdDao) context.getAttribute("ordDao");
        Ord.OrdStatus[] availableStatuses = {Ord.OrdStatus.ASSIGNED, Ord.OrdStatus.IN_TRANSIT, Ord.OrdStatus.DONE};
        List<Ord> ords = ordDao.findAllByDriverAndOrdStatuses(cabDriver, availableStatuses);

        request.setAttribute("newListOfOrdsByDriver", ords);
        request.getRequestDispatcher("/jsp/ord/ords-list-driver.jsp").forward(request, response);
        response.sendRedirect("/list-ord-driver");
    }
}


