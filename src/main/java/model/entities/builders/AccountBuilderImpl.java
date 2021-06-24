package model.entities.builders;

import model.entities.Account;
import model.entities.Role;

public class AccountBuilderImpl implements AccountBuilder {
    private Account account = new Account();

    @Override
    public AccountBuilder setId(int id) {
        account.setId(id);
        return this;
    }

    @Override
    public AccountBuilder setEmail(String email) {
        account.setEmail(email);
        return this;
    }

    @Override
    public AccountBuilder setPassword(String password) {
        account.setPassword(password);
        return this;
    }

    @Override
    public AccountBuilder setFirstName(String firstName) {
        account.setFirstName(firstName);
        return this;
    }

    @Override
    public AccountBuilder setLastName(String lastName) {
        account.setLastName(lastName);
        return this;
    }

    @Override
    public AccountBuilder setRole(Role role) {
        account.setRole(role);
        return this;
    }

    @Override
    public Account build() {
        return account;
    }
}
