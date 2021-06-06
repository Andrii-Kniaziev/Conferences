package model.service;

import dao.MyException;
import dao.impl.ConnectionPoolHolder;
import model.entities.Account;
import model.entities.Role;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class AccountServiceTest {
    private static final String CLEAN_DB =
            "DELETE FROM account" +
            " WHERE (email = 'test@gmail.com' " +
            "OR email = 'test1@gmail.com' " +
            "OR email = 'test2@gmail.com') " +
            "AND role = 'admin';";
    private Account original;
    private Account forEmailTest;
    private Account accountExists;
    private AccountService service;

    @Before
    public void setUp() {
        service = new AccountService();
        original = new Account("test@gmail.com", "Aa1@0000",
                "Test", "Test", Role.ADMIN);
        accountExists = new Account("test2@gmail.com", "Aa1@0000",
                "Test", "Test", Role.ADMIN);
        forEmailTest = new Account("test1@gmail.com", "Aa1@0000",
                "Test", "Test", Role.ADMIN);
    }

    @Test
    public void create_new_Account_test() throws MyException {
        assertTrue(service.createNewAccount(original));
    }

    @Test(expected = MyException.class)
    public void account_already_exists_test() throws MyException {
        assertTrue(service.createNewAccount(accountExists));
        service.createNewAccount(accountExists);
    }


    @Test
    public void get_account_by_email_test() throws MyException {
        service.createNewAccount(forEmailTest);

        Account fromDB = service.getAccountByEmail("test1@gmail.com");
        assertEquals(forEmailTest, fromDB);
    }

    @Test
    public void get_accounts_by_role_test() throws MyException {
        List<Account> speakers = service.getAccountsByRole(Role.SPEAKER.getValue());

        for(Account speaker : speakers) {
            assertEquals(speaker.getRole(), Role.SPEAKER);
        }
    }

    @AfterClass
    public static void clean_Database() throws SQLException {
        Connection connection = ConnectionPoolHolder.getDataSource().getConnection();

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(CLEAN_DB);

        stmt.close();
        connection.close();
    }
}
