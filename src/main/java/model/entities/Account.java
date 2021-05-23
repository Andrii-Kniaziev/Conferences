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

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }
}
