package ru.yeroshenko.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.yeroshenko.domain.Account;

import java.util.List;

/**
 * Created by Genie Yeroshenko on 10/11/15.
 */
public class AccountDao {

    private SessionFactory sessionFactory;

    public AccountDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Account> findByLogin(String login) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Account account where account.login = ?");
        query.setParameter(0, login);
        List list = query.list();
        session.close();
        return list;
    }


}
