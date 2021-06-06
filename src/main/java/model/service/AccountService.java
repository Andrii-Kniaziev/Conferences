package model.service;

import dao.AccountDAO;
import dao.DaoFactory;
import dao.MyException;
import model.entities.Account;

import java.util.List;

public class AccountService {
    private DaoFactory daoFactory;

    public AccountService() {
        daoFactory = DaoFactory.getInstance();
    }

    /**
     * Method returns list of users by role
     * @param role we will get list of users with this role
     * @return list of users with this role
     * @throws MyException in case of problems connection to DB
     */

    public List<Account> getAccountsByRole(String role) throws MyException {
        try(AccountDAO dao = daoFactory.createAccountDao()) {
            return dao.getAccounts(role);
        }
    }

    /**
     * Method creates new Account by calling
     * {@link dao.impl.JDBCAccountDAO#insertAccount(Account account)}
     * @param account account to be created
     * @return 'true' if account was inserted in DB successfully
     * @throws MyException in case if email already exists in DB
     */

    public boolean createNewAccount(Account account) throws MyException {
        try(AccountDAO dao = daoFactory.createAccountDao()) {
            dao.insertAccount(account);
        }
        return true;
    }

    /**
     * Returns account object by email by calling
     * {@link dao.impl.JDBCAccountDAO#getAccountByEmail(String email)}
     * @param email the indicator to find account
     * @return account or null if email is absent in DB
     */

    public Account getAccountByEmail(String email) {
        try(AccountDAO dao = daoFactory.createAccountDao()) {
            return dao.getAccountByEmail(email);
        }
    }
}
