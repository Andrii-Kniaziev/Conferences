package dao.mapper;

import dao.Constants;
import model.entities.Account;
import model.entities.Role;
import model.entities.builders.AccountBuilderImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper {

    /**
     * Method extracts data from ResultSet and creates
     * object of Account class for further processing
     *
     * @param res ResultSet
     * @return Account object
     * @throws SQLException in case of errors
     */

    public Account getAccFromRS(ResultSet res) throws SQLException {
        int id = res.getInt(Constants.FIELD_ID);
        String firstName = res.getString(Constants.FIELD_FIRST_NAME);
        String lastName = res.getString(Constants.FIELD_LAST_NAME);
        String emailFromDB = res.getString(Constants.FIELD_EMAIL);
        String password = res.getString(Constants.FIELD_PASSWORD);
        Role role = Role.stringToEnum(res.getString(Constants.FIELD_ROLE));

        return new AccountBuilderImpl()
                .setId(id)
                .setEmail(emailFromDB)
                .setPassword(password)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setRole(role)
                .build();
    }
}
