package model.entities.builders;

import model.entities.Account;
import model.entities.Role;

public interface AccountBuilder {

    AccountBuilder setId(int id);
    AccountBuilder setEmail(String email);
    AccountBuilder setPassword(String password);
    AccountBuilder setFirstName(String firstName);
    AccountBuilder setLastName(String lastName);
    AccountBuilder setRole(Role role);

    Account build();
}
