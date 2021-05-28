package dao;

public class Constants {
    public static final String FORWARD = "forward";

    public static final String REGISTER_RESULT = "/WEB-INF/views/registration-result.jsp";
    public static final String ADMIN_ACCOUNT = "/WEB-INF/views/adminAccount.jsp";
    public static final String SPEAKER_ACCOUNT = "/WEB-INF/views/speakerAccount.jsp";
    public static final String LISTENER_ACCOUNT = "/WEB-INF/views/listenerAccount.jsp";
    public static final String TOPIC_CREATION_JSP = "/WEB-INF/views/topicCreation.jsp";
    public static final String INDEX_JSP = "index.jsp";

    public static final String FIELD_ID = "id";
    public static final String FIELD_FIRST_NAME = "first_name";
    public static final String FIELD_LAST_NAME = "last_name";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_ROLE = "role";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_DATE = "date";
    public static final String FIELD_PLACE = "place";
    public static final String FIELD_IS_FINISHED = "is_finished";
    public static final String ROLE_SPEAKER = "speaker";
    public static final String ROLE_ADMIN = "admin";

    public static final String LN_SEP = System.lineSeparator();
    public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/mydb?user=root&password=5321068Ask@";
    public static final String INSERT_ACCOUNT = "INSERT INTO account"
            + "(email, password, first_name, last_name, role)"
            + "VALUES (?, ?, ?, ?, ?)";
    public static final String GET_ACCOUNT = "SELECT * FROM account WHERE email = ?";

    public static final String INSERT_EVENT = "INSERT INTO event"
            + "(name, description, date, place, is_finished) "
            + "VALUES (?, ?, ?, ?, ?)";
    public static final String INSERT_TOPIC = "INSERT INTO topic"
            + "(event_id, account_id, name, description, admin_approved, speaker_approved, desigion_is_done) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_ALL_EVENTS = "SELECT * FROM event ORDER BY date DESC";
    public static final String GET_ACCOUNT_BY_ROLE = "SELECT * FROM ACCOUNT WHERE role = ?";
}
