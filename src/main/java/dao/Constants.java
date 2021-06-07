package dao;

public class Constants {
    public static final String UA = "UA";
    public static final String EN = "EN";

    public static final String CONFIRMATION_OF_PRESENCE = "/WEB-INF/views/confirmationOfPresence.jsp";
    public static final String CONFIRMATION_OF_PRESENCE_EN = "/WEB-INF/views_en/confirmationOfPresence.jsp";
    public static final String REGISTER_RESULT = "/WEB-INF/views/registration-result.jsp";
    public static final String REGISTER_RESULT_EN = "/WEB-INF/views_en/registration-result.jsp";
    public static final String ADMIN_ACCOUNT = "/WEB-INF/views/adminAccount.jsp";
    public static final String ADMIN_ACCOUNT_EN = "/WEB-INF/views_en/adminAccount.jsp";
    public static final String SPEAKER_ACCOUNT = "/WEB-INF/views/speakerAccount.jsp";
    public static final String SPEAKER_ACCOUNT_EN = "/WEB-INF/views_en/speakerAccount.jsp";
    public static final String LISTENER_ACCOUNT = "/WEB-INF/views/listenerAccount.jsp";
    public static final String LISTENER_ACCOUNT_EN = "/WEB-INF/views_en/listenerAccount.jsp";
    public static final String TOPIC_CREATION_JSP = "/WEB-INF/views/topicCreation.jsp";
    public static final String TOPIC_CREATION_JSP_EN = "/WEB-INF/views_en/topicCreation.jsp";
    public static final String EVENT_SUBSCRIPTION = "/WEB-INF/views/eventSubscription.jsp";
    public static final String EVENT_SUBSCRIPTION_EN = "/WEB-INF/views_en/eventSubscription.jsp";
    public static final String SORTED_EVENTS = "/WEB-INF/views/sortedEvents.jsp";
    public static final String SORTED_EVENTS_EN = "/WEB-INF/views_en/sortedEvents.jsp";
    public static final String OFFERED_TOPICS = "/WEB-INF/views/offeredTopics.jsp";
    public static final String OFFERED_TOPICS_EN = "/WEB-INF/views_en/offeredTopics.jsp";
    public static final String OFFER_TOPIC_TO_SPEAKER_INFO = "/WEB-INF/views/offerTopicToSpeaker.jsp";
    public static final String OFFER_TOPIC_TO_SPEAKER_INFO_EN = "/WEB-INF/views_en/offerTopicToSpeaker.jsp";
    public static final String TOPICS_IN_PROCESS = "/WEB-INF/views/topicsInProcess.jsp";
    public static final String TOPICS_IN_PROCESS_EN = "/WEB-INF/views_en/topicsInProcess.jsp";
    public static final String INDEX_JSP = "index.jsp";
    public static final String INDEX_EN_JSP = "index_en.jsp";

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
    public static final String FIELD_EVENT_ID = "event_id";
    public static final String FIELD_ACCOUNT_ID = "account_id";
    public static final String ADMIN_APPROVED = "admin_approved";
    public static final String SPEAKER_APPROVED = "speaker_approved";
    public static final String DESIGION_IS_DONE = "desigion_is_done";
    public static final String ROLE_SPEAKER = "speaker";
    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_LISTENER = "listener";

    public static final String LN_SEP = System.lineSeparator();

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

    public static final String INSERT_VISIT = "INSERT INTO event_visitor(account_id, event_id) " +
            "VALUES (?, ?)";

    public static final String GET_EVENTS_FROM_INDEX =
            "SELECT * FROM event WHERE date > now() limit ?, ?";

    public static final String GET_NOT_FINISHED_EVENT_COUNT = "SELECT count(*) FROM event WHERE date > now()";
    public static final String GET_FINISHED_EVENT_COUNT = "SELECT count(*) FROM event WHERE date < now()";
    public static final String GET_NOT_FINISHED_EVENTS = "SELECT * FROM event WHERE date > now() ORDER BY date DESC";
    public static final String GET_FINISHED_EVENTS_LIMIT =
            "SELECT * FROM event WHERE date < now() ORDER BY date DESC limit ?, ?";
    public static final String GET_NOT_FINISHED_EVENTS_LIMIT =
            "SELECT * FROM event WHERE date > now() ORDER BY date DESC limit ?, ?";

    public static final String GET_ACCOUNT_BY_ROLE = "SELECT * FROM ACCOUNT WHERE role = ?";

    public static final String GET_NOT_FINISHED_EVENTS_GROUP_BY_VISITORS =
            "SELECT event.*, COUNT(account_id) AS account_count " +
                    "FROM event LEFT JOIN event_visitor " +
                    "ON event.id = event_visitor.event_id " +
                    "WHERE date > now() " +
                    "GROUP BY event.id " +
                    "ORDER BY account_count DESC limit ?, ?";

    public static final String GET_FINISHED_EVENTS_GROUP_BY_VISITORS =
            "SELECT event.*, COUNT(account_id) AS account_count " +
                    "FROM event LEFT JOIN event_visitor " +
                    "ON event.id = event_visitor.event_id " +
                    "WHERE date < now() " +
                    "GROUP BY event.id " +
                    "ORDER BY account_count DESC limit ?, ?";

    public static final String GET_NOT_FINISHED_EVENTS_GROUP_BY_TOPICS =
            "SELECT event.*, COUNT(topic.id) AS topic_count " +
                    "FROM event LEFT JOIN topic " +
                    "ON event.id = topic.event_id " +
                    "WHERE date > now() " +
                    "GROUP BY event.id " +
                    "ORDER BY topic_count DESC limit ?, ?";

    public static final String GET_FINISHED_EVENTS_GROUP_BY_TOPICS =
            "SELECT event.*, COUNT(topic.id) AS topic_count " +
                    "FROM event LEFT JOIN topic " +
                    "ON event.id = topic.event_id " +
                    "WHERE date < now() " +
                    "GROUP BY event.id " +
                    "ORDER BY topic_count DESC limit ?, ?";

    public static final String CHECK_OFFERED_TOPICS =
            "select * from topic " +
                    "Where (account_id = ? " +
                    "AND admin_approved = 'true' " +
                    "AND speaker_approved = 'false' " +
                    "AND desigion_is_done = 'false' " +
                    "AND event_id IN (SELECT id FROM event WHERE date > now()))";

    public static final String AGREE_FOR_OFFERED_TOPIC =
            "UPDATE topic " +
                    "SET speaker_approved = 'true', " +
                    "desigion_is_done = 'true' " +
                    "WHERE id = ?";

    public static final String DISAGREE_FOR_OFFERED_TOPIC =
            "UPDATE topic " +
                    "SET speaker_approved = 'false', " +
                    "desigion_is_done = 'false', " +
                    "admin_approved = 'false' " +
                    "WHERE id = ?";

    public static final String APPROVE_OFFERED_TOPIC =
            "UPDATE topic " +
                    "SET admin_approved = 'true', " +
                    "desigion_is_done = 'true' " +
                    "WHERE id = ?";

    public static final String DENY_OFFERED_TOPIC =
            "UPDATE topic " +
                    "SET admin_approved = 'false', " +
                    "speaker_approved = 'false', " +
                    "desigion_is_done = 'true' " +
                    "WHERE id = ?";

    public static final String SELECT_NOT_FINISHED_EVENTS =
            "SELECT id FROM event WHERE date > now()";

    public static final String SELECT_FINISHED_EVENTS =
            "SELECT id FROM event WHERE date < now()";

    public static final String FIND_TOPICS_WITHOUT_SPEAKERS =
            "SELECT * FROM topic " +
                    "WHERE (account_id is null OR " +
                    "(speaker_approved = 'false' " +
                    "AND desigion_is_done = 'false' " +
                    "AND admin_approved = 'false')) " +
                    "AND event_id IN (SELECT id FROM event WHERE date > now())";

    public static final String OFFER_EMPTY_TOPIC_TO_SPEAKER =
            "UPDATE topic " +
                    "SET account_id = ?, " +
                    "    speaker_approved = 'false', " +
                    "    desigion_is_done = 'false', " +
                    "    admin_approved = 'true' " +
                    "WHERE id = ?";

    public static final String SELECT_VISITS_BY_USER_ID =
            "SELECT * FROM event_visitor " +
            "WHERE (account_id = ? AND event_id IN (SELECT id FROM event WHERE date > now()))";

    public static final String SELECT_NOT_MARKED_VISITS_FOR_LISTENER =
            "select event_visitor.event_id, event.* \n" +
                    "from event_visitor join event\n" +
                    "on event_visitor.event_id = event.id \n" +
                    "where account_id = ? \n" +
                    "AND event_visitor.was_present = 'false' \n" +
                    "AND event_visitor.event_id IN (Select id from event where date < now());";

    public static final String COUNT_ALL_VISITS_OF_EVENT =
            "SELECT count(*) FROM event_visitor WHERE event_id = ?;";

    public static final String COUNT_CONFIRMED_VISITS_OF_EVENT =
            "SELECT count(*) FROM event_visitor WHERE event_id = ? AND was_present = 'yes';";

    public static final String MARK_PRESENCE =
            "UPDATE event_visitor SET was_present = ? WHERE account_id = ? AND event_id = ?;";

    public static final String DELETE_VISIT =
            "DELETE FROM event_visitor WHERE account_id = ? AND event_id = ?";

    public static final String SELECT_AGREED_TOPIC_BY_SPEAKER_ID =
            "SELECT * FROM topic WHERE account_id = ? " +
                    "AND admin_approved = 'true' " +
                    "AND speaker_approved = 'true' " +
                    "AND desigion_is_done='true' " +
                    "AND event_id IN (SELECT id FROM event WHERE date > now())";

    public static final String SELECT_TOPICS_IN_PROCESS =
            "SELECT * FROM topic WHERE account_id = ? " +
                    "AND admin_approved = 'false' " +
                    "AND speaker_approved = 'true' " +
                    "AND desigion_is_done='false' " +
                    "AND event_id IN (SELECT id FROM event WHERE date > now())";

    public static final String SELECT_PROPOSED_FOR_ADMIN_DECISION_TOPICS =
            "SELECT * FROM topic WHERE (admin_approved = 'false'" +
                    " AND speaker_approved = 'true'" +
                    " AND desigion_is_done = 'false')";




}
