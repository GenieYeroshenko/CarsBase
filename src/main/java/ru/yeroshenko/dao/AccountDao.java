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

    public List<Account> findAllUsersByLogin(String login) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Account account where account.login = ?");
        query.setParameter(0, login);
        List list = query.list();
        session.close();
        return list;
    }

//    //todo rework
//    public Account findAuthorizedUser(String login, String password) {
//        Session session = sessionFactory.openSession();
//        Query query = session.createQuery("from Account account where account.login = ? and account.password = ?");
//        query.setParameter(0, login);
//        query.setParameter(1, password);
//        session.close();
//        Account authorizedUser = query.getNamedParameters(login, password);
//
//        return authorizedUser;
//    }

    //todo rework
    public long numberOfAccountsWithLogin(String login) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count(*) from Account account where account.login = ?");
        query.setParameter(0, login);
        Long count = (Long) query.uniqueResult();
        session.close();
        return count;
    }

}
