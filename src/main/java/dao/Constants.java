package dao;

public class Constants {
    public static final String REGISTER_RESULT = "/WEB-INF/views/registration-result.jsp";
    public static final String ADMIN_ACCOUNT = "/WEB-INF/views/adminAccount.jsp";
    public static final String SPEAKER_ACCOUNT = "/WEB-INF/views/speakerAccount.jsp";
    public static final String LISTENER_ACCOUNT = "/WEB-INF/views/listenerAccount.jsp";
    public static final String INDEX_JSP = "index.jsp";

    public static final String FIELD_ID = "id";
    public static final String FIELD_FIRST_NAME = "first_name";
    public static final String FIELD_LAST_NAME = "last_name";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_ROLE = "role";

    public static final String LN_SEP = System.lineSeparator();
    public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/mydb?user=root&password=5321068Ask@";
    public static final String INSERT_ACCOUNT = "INSERT INTO account"
            + "(email, password, first_name, last_name, role)"
            + "VALUES (?, ?, ?, ?, ?)";
    public static final String GET_ACCOUNT = "SELECT * FROM account WHERE email = ?";
}
