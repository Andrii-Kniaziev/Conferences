package model.entities;

public class Account {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;

    public Account(String email, String password, String first_name, String last_name, Role role) {
        this.email = email;
        this.password = password;
        this.firstName = first_name;
        this.lastName = last_name;
        this.role = role;
    }

    public Account(int id, String email, String password, String firstName, String lastName, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    @Override
    public String toString() {
        return id + " " + firstName + " " + lastName + " " + email + " " + role;
    }
}
