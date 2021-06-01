package model.service;

import dao.AccountDAO;
import dao.DaoFactory;
import dao.MyException;
import model.entities.Account;

import java.util.List;

public class AccountService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Account> getAccountsByRole(String role) throws MyException {
        try(AccountDAO dao = daoFactory.createAccountDao()) {
            return dao.getAccounts(role);
        }
    }

    public boolean createNewAccount(Account account) throws MyException {
        try(AccountDAO dao = daoFactory.createAccountDao()) {
            dao.insertAccount(account);
        }
        return true;
    }

    public Account getAccountByEmail(String email) {
        Account account = null;
        try(AccountDAO dao = daoFactory.createAccountDao()) {
            account = dao.getAccountByEmail(email);
        }
        return account;
    }
}
