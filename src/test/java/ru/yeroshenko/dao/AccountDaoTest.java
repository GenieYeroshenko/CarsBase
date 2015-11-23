package ru.yeroshenko.dao;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ru.yeroshenko.domain.Account;
import ru.yeroshenko.domain.CabDriver;
import ru.yeroshenko.util.HibernateUtil;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Genie Yeroshenko on 10/11/15.
 */
public class AccountDaoTest {

    private AccountDao accountDao;

    @Before
    public void setUp() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        accountDao = new AccountDao(sessionFactory);
    }

    @Test
    public void testFindAllUsersByLogin() {
        CabDriver cabDriver1 = new CabDriver();
        CabDriver cabDriver2 = new CabDriver();
        CabDriver cabDriver3 = new CabDriver();
        cabDriver1.setLogin("login1");
        cabDriver2.setLogin("login1");
        cabDriver3.setLogin("login2");

        accountDao.add(cabDriver1);
        accountDao.add(cabDriver2);
        accountDao.add(cabDriver3);

        List<Account> accounts = accountDao.findAllUsersByLogin("login1");
        accountDao.delete(cabDriver1);
        accountDao.delete(cabDriver2);
        accountDao.delete(cabDriver3);

        assertEquals(accounts.size(), 2);
    }

    @Test
    public void testCountOfAccountsWithLogin() {
        CabDriver cabDriver1 = new CabDriver();
        CabDriver cabDriver2 = new CabDriver();
        CabDriver cabDriver3 = new CabDriver();
        cabDriver1.setLogin("login1");
        cabDriver2.setLogin("login1");
        cabDriver3.setLogin("login2");

        accountDao.add(cabDriver1);
        accountDao.add(cabDriver2);
        accountDao.add(cabDriver3);

        long numberOfAccounts = accountDao.countAccountsWithLogin("login1");
        accountDao.delete(cabDriver1);
        accountDao.delete(cabDriver2);
        accountDao.delete(cabDriver3);

        assertEquals(numberOfAccounts, 2);
    }
}