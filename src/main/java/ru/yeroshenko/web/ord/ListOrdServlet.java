package ru.yeroshenko.web.ord;

import ru.yeroshenko.dao.OrdDao;
import ru.yeroshenko.domain.Ord;
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
public class ListOrdServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrdDao ordDao = new OrdDao(HibernateUtil.getSessionFactory());
        List<Ord> ords = ordDao.findAll();

        request.setAttribute("newListOfOrds", ords);
        request.getRequestDispatcher("/jsp/ords-list.jsp").forward(request, response);
    }

}


