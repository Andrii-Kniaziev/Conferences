package dao;

import model.entities.Account;
import model.entities.Event;
import model.entities.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class AccountDAO {
    private static AccountDAO dao;

    private AccountDAO() {
    }

    public static synchronized AccountDAO getInstance() {
        if (dao == null) {
            dao = new AccountDAO();
        }
        return dao;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Constants.CONNECTION_URL);
    }

    public boolean insertAccount(Account account) throws MyException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(Constants.INSERT_ACCOUNT)) {

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

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement(Constants.GET_ACCOUNT);
            stmt.setString(1, email);
            res = stmt.executeQuery();
            if(res.next()) {
                account = getAccFromRS(res);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            close(res);
            close(stmt);
            close(con);
        }

        return account;
    }

    public List<Account> getAccounts(String role) throws MyException {
        List<Account> speakers = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            con = getConnection();
            stmt = con.prepareStatement(Constants.GET_ACCOUNT_BY_ROLE);
            stmt.setString(1, role);
            res = stmt.executeQuery();

            while(res.next()) {
                speakers.add(getAccFromRS(res));
            }
        } catch (SQLException e) {
            throw new MyException("Something went wrong with getting events from DB", e);
        } finally {
            close(res);
            close(stmt);
            close(con);
        }

        return speakers;
    }

    private Account getAccFromRS (ResultSet res) throws SQLException {
        int id = res.getInt(Constants.FIELD_ID);
        String firstName = res.getString(Constants.FIELD_FIRST_NAME);
        String lastName = res.getString(Constants.FIELD_LAST_NAME);
        String emailFromDB = res.getString(Constants.FIELD_EMAIL);
        String password = res.getString(Constants.FIELD_PASSWORD);
        Role role = Role.stringToEnum(res.getString(Constants.FIELD_ROLE));

        return new Account(id, emailFromDB, password, firstName, lastName, role);
    }

    public void close(AutoCloseable ac) {
        if(ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                System.out.println(e);
            }
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
