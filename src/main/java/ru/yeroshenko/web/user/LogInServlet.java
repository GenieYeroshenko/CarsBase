package ru.yeroshenko.web.user;

import ru.yeroshenko.dao.AccountDao;
import ru.yeroshenko.domain.Account;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.domain.CarManager;
import ru.yeroshenko.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by evgeniya on 15/11/15.
 */

//todo
public class LogInServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        AccountDao accountDao = new AccountDao(HibernateUtil.getSessionFactory());
        List<Account> accounts = accountDao.findByLogin(login);
        if (accounts.size() > 1) {
            request.setAttribute("error", "ошибка логин");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }

        if (accounts.isEmpty()) {
            request.setAttribute("error", "такого аккаунта не существует");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }

        Account account = accounts.get(0);

        if (password.equals(account.getPassword())) {

            request.getSession().setAttribute("authorizedUser", account);

            if (account instanceof CabDriver) {
                response.sendRedirect("/list-car");
            } else if (account instanceof CarManager) {
                response.sendRedirect("/list-ord");
            }

        } else {
            request.setAttribute("error", "ошибка пароль");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }
}
