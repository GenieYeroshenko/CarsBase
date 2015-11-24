package ru.yeroshenko.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.yeroshenko.domain.Account;

import java.util.List;


/**
 * Class with basic methods that service DAO Account
 */
public class AccountDao {

    private SessionFactory sessionFactory;

    /**
     * @param sessionFactory - a factory to create new Session instances
     */
    public AccountDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @param login - User login
     * @return list of accounts with the same login
     */
    public List<Account> findAllUsersByLogin(String login) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Account account where account.login = ?");
        query.setParameter(0, login);
        List list = query.list();
        session.close();
        return list;
    }

    /**
     * @param login - User login
     * @return number of accounts with the same login
     */
    public long countAccountsWithLogin(String login) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count(*) from Account account where account.login = ?");
        query.setParameter(0, login);
        Long count = (Long) query.uniqueResult();
        session.close();
        return count;
    }

    /**
     * @param account - User with login and password
     *                Method saves account in one transaction
     */
    public void add(Account account) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(account);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * @param account - User with login and password
     *                Method deletes account in one transaction
     */
    public void delete(Account account) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(account);
            session.flush();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
