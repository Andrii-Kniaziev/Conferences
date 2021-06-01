package dao.impl;

import dao.AccountDAO;
import dao.Constants;
import dao.MyException;
import dao.mapper.AccountMapper;
import model.entities.Account;
import model.entities.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAccountDAO implements AccountDAO {
    private Connection con;

    public JDBCAccountDAO(Connection con) {
        this.con = con;
    }

    public boolean insertAccount(Account account) throws MyException {
        try (PreparedStatement stmt = con.prepareStatement(Constants.INSERT_ACCOUNT)) {

            stmt.setString(1, account.getEmail());
            stmt.setString(2, account.getPassword());
            stmt.setString(3, account.getFirstName());
            stmt.setString(4, account.getLastName());
            stmt.setString(5, account.getRole().getValue());

            stmt.executeUpdate();
        } catch (Exception e) {
            throw new MyException("Something went wrong with insertion of new Account", e);
        }
        return true;
    }

    public Account getAccountByEmail(String email) {
        Account account = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            stmt = con.prepareStatement(Constants.GET_ACCOUNT);
            stmt.setString(1, email);
            res = stmt.executeQuery();

            AccountMapper mapper = new AccountMapper();

            if(res.next()) {
                account = mapper.getAccFromRS(res);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            close(res);
            close(stmt);
        }

        return account;
    }

    public List<Account> getAccounts(String role) throws MyException {
        List<Account> speakers = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            stmt = con.prepareStatement(Constants.GET_ACCOUNT_BY_ROLE);
            stmt.setString(1, role);
            res = stmt.executeQuery();

            AccountMapper mapper = new AccountMapper();

            while(res.next()) {
                speakers.add(mapper.getAccFromRS(res));
            }
        } catch (SQLException e) {
            throw new MyException("Something went wrong with getting events from DB", e);
        } finally {
            close(res);
            close(stmt);
        }

        return speakers;
    }

    @Override
    public void close()  {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



//    public static void main(String[] args) throws MyException {
//        AccountDAO dao = AccountDAO.getInstance();
//        Account acc =
//                new Account("oleg123@gmail.com", "4445AdS3", "Oleg", "Kobalev", Role.LISTENER);
//        dao.insertAccount(acc);
//
//        List<Account> speakers = dao.getAccounts(Constants.ROLE_SPEAKER);
//
//        for (Account a : speakers) {
//            System.out.println(a);
//        }
//
//        System.out.println("**********");
//        System.out.println(dao.getAccountByEmail("penelopa111cr@mail.ru"));
//    }
}
