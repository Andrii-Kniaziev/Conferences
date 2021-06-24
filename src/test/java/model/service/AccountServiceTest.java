package model.service;

import dao.MyException;
import dao.impl.ConnectionPoolHolder;
import model.entities.Account;
import model.entities.Role;
import model.entities.builders.AccountBuilderImpl;
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
    private Account emailTest;
    private Account accExists;
    private AccountService service;

    @Before
    public void setUp() {
        service = new AccountService();

        original = new AccountBuilderImpl().setEmail("test@gmail.com").setPassword("Aa1@0000").setFirstName("Test")
                .setLastName("Test").setRole(Role.ADMIN).build();
        accExists = new AccountBuilderImpl().setEmail("test2@gmail.com").setPassword("Aa1@0000").setFirstName("Test")
                .setLastName("Test").setRole(Role.ADMIN).build();
        emailTest = new AccountBuilderImpl().setEmail("test1@gmail.com").setPassword("Aa1@0000").setFirstName("Test")
                .setLastName("Test").setRole(Role.ADMIN).build();
    }

    @Test
    public void create_new_Account_test() throws MyException {
        assertTrue(service.createNewAccount(original));
    }

    @Test(expected = MyException.class)
    public void account_already_exists_test() throws MyException {
        assertTrue(service.createNewAccount(accExists));
        service.createNewAccount(accExists);
    }


    @Test
    public void get_account_by_email_test() throws MyException {
        service.createNewAccount(emailTest);

        Account fromDB = service.getAccountByEmail("test1@gmail.com");
        assertEquals(emailTest, fromDB);
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
