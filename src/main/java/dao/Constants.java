package dao;

public class Constants {
    public static final String LN_SEP = System.lineSeparator();
    public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/mydb?user=root&password=5321068Ask@";
    public static final String INSERT_EMPLOYEE = "INSERT INTO account"
            + "(email, password, first_name, last_name, role)"
            + "VALUES (?, ?, ?, ?, ?)";
}
