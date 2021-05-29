package dao;

import model.entities.Account;
import java.util.List;

public interface AccountDAO extends SuperDAO {

    boolean insertAccount(Account account) throws MyException;

    Account getAccountByEmail(String email);

    List<Account> getAccounts(String role) throws MyException;


}
