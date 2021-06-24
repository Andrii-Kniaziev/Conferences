package model.entities;

public class Account {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;

    public int getId() { return id; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public void setId(int id) { this.id = id; }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    @Override
    public String toString() {
        return id + " " + firstName + " " + lastName + " " + email + " " + role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (!email.equals(account.email)) return false;
        if (!password.equals(account.password)) return false;
        if (!firstName.equals(account.firstName)) return false;
        if (!lastName.equals(account.lastName)) return false;
        return role == account.role;
    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
