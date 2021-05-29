package dao.mapper;

import dao.Constants;
import model.entities.Account;
import model.entities.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper {

    public Account getAccFromRS (ResultSet res) throws SQLException {
        int id = res.getInt(Constants.FIELD_ID);
        String firstName = res.getString(Constants.FIELD_FIRST_NAME);
        String lastName = res.getString(Constants.FIELD_LAST_NAME);
        String emailFromDB = res.getString(Constants.FIELD_EMAIL);
        String password = res.getString(Constants.FIELD_PASSWORD);
        Role role = Role.stringToEnum(res.getString(Constants.FIELD_ROLE));

        return new Account(id, emailFromDB, password, firstName, lastName, role);
    }
}
