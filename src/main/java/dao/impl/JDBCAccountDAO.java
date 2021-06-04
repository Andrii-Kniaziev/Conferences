package dao.impl;

import dao.AccountDAO;
import dao.Constants;
import dao.MyException;
import dao.mapper.AccountMapper;
import model.entities.Account;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAccountDAO implements AccountDAO {
    private static Logger logger = Logger.getLogger(JDBCAccountDAO.class);
    private Connection con;

    public JDBCAccountDAO(Connection con) {
        this.con = con;
    }

    /**
     * Method creates new user in DB.
     * @param account to be created (inserted into DB)
     * @return 'true' in case of success
     * @throws MyException in case if email already exists in DB
     */

    public boolean insertAccount(Account account) throws MyException {
        try (PreparedStatement stmt = con.prepareStatement(Constants.INSERT_ACCOUNT)) {

            stmt.setString(1, account.getEmail());
            stmt.setString(2, account.getPassword());
            stmt.setString(3, account.getFirstName());
            stmt.setString(4, account.getLastName());
            stmt.setString(5, account.getRole().getValue());

            stmt.executeUpdate();
        } catch (SQLException e) {
            if(e instanceof SQLIntegrityConstraintViolationException) {
                throw new MyException("Email already exists", e);
            } else {
                logger.error(e);
            }
        }
        return true;
    }

    /**
     * Method returns account by email, in case if email
     * is absent in DB returns null
     * @param email method finds account by this email
     * @return account that was found by email
     */

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
            logger.error(e);
        } finally {
            close(res);
            close(stmt);
        }

        return account;
    }

    /**
     * Method finds all accounts with some role
     * @param role to be found
     * @return list of accounts by the role
     * @throws MyException in case if something is wrong with connection to DB
     */

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
            logger.error(e);
            throw new MyException("Something went wrong with getting events from DB", e);
        } finally {
            close(res);
            close(stmt);
        }

        return speakers;
    }

    @Override
    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

}
