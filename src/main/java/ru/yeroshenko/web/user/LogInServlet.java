package ru.yeroshenko.web.user;

import ru.yeroshenko.dao.AccountDao;
import ru.yeroshenko.domain.Account;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.domain.CarManager;

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


public class LogInServlet extends HttpServlet {

    public static final String AUTHORIZED_USER = "authorizedUser";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        ServletContext context = request.getSession().getServletContext();
        AccountDao accountDao = (AccountDao) context.getAttribute("accountDao");
        List<Account> accounts = accountDao.findAllUsersByLogin(login);
        if (accounts.size() > 1) {
            //todo change error messages
            request.setAttribute("error", "ошибка в логине");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }

        if (accounts.isEmpty()) {
            request.setAttribute("error", "такого аккаунта не существует");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }

        Account account = accounts.get(0);
        if (password.equals(account.getPassword())) {
            request.getSession().setAttribute(AUTHORIZED_USER, account);
            if (account instanceof CabDriver) {
                response.sendRedirect("/list-ord-driver");
            } else if (account instanceof CarManager) {
                response.sendRedirect("/list-ord-manager");
            }
        } else {
            request.setAttribute("error", "ошибка в пароле");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }
}
