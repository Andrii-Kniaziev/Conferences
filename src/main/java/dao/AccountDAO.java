package dao;

import model.entities.Account;
import model.entities.Role;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
             PreparedStatement stmt = con.prepareStatement(Constants.INSERT_EMPLOYEE)) {

            stmt.setString(1, account.getEmail());
            stmt.setString(2, account.getPassword());
            stmt.setString(3, account.getFirstName());
            stmt.setString(4, account.getLastName());
            stmt.setString(5, account.getRole().getValue());

            stmt.executeUpdate();
        } catch (Exception e) {
            throw new MyException("New user was not added", e);
        }
        return true;
    }

//    public static void main(String[] args) throws MyException {
//        AccountDAO dao = AccountDAO.getInstance();
//        Account acc =
//                new Account("oleg123@gmail.com", "4445AdS3", "Oleg", "Kobalev", Role.LISTENER);
//        dao.insertAccount(acc);
//    }
}
