package model.service;

import dao.MyException;
import dao.impl.ConnectionPoolHolder;
import model.entities.Account;
import model.entities.Event;
import model.entities.Role;
import model.entities.Topic;
import org.junit.*;

import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class TopicServiceTest {
    private TopicService service;
    private static int speakerID;
    private static int eventID;

    @BeforeClass
    public static void create_listener_and_event_in_DB_for_test() throws MyException, SQLException {
        Account listener = new Account("test@gmail.com", "Aa1@0000",
                "Test", "Test", Role.ADMIN);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2021, Calendar.DECEMBER, 11);
        Event event = new Event("Test", "Test", calendar, "Test", false);

        AccountService accountService = new AccountService();

        accountService.createNewAccount(listener);
        new EventService().insertEvent(event);

        speakerID = new AccountService().getAccountByEmail("test@gmail.com").getId();

        Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery("SELECT id FROM event WHERE name = 'Test' AND place = 'Test'");

        if(res.next()) {
            eventID = res.getInt(1);
        }

        stmt.close();
        connection.close();
    }

    @Before
    public void setUp() {
        service = new TopicService();
    }

    @Test
    public void create_new_topic_test() {
        Topic topic = new Topic
                (eventID, speakerID, "Test", "Test", true, false);
        assertTrue(service.createNewTopic(topic));
    }

    @Test
    public void get_offered_topics_test() throws MyException {
        create_new_topic_test();
        List<Topic> offeredToSpeaker = service.getOfferedTopics(speakerID);

        assertEquals(1, offeredToSpeaker.size());
        assertEquals(offeredToSpeaker.get(0).getSpeakerId(), speakerID);
    }

    @Test
    public void get_topics_without_speakers_test() throws MyException {
        List<Topic> withoutSpkrs = service.getTopicsWithoutSpeakers();

        for(Topic t : withoutSpkrs) {
            assertFalse(t.isAdminApproved());
            assertFalse(t.isSpeakerApproved());
            assertFalse(t.isDeсisionDone());
        }
    }

    @Test
    public void get_proposed_topics_test() {

    }

    @After
    public void delete_topic() throws SQLException {
        Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
        PreparedStatement stmt = connection.prepareStatement
                ("DELETE FROM topic WHERE name = 'Test' AND description = 'Test'");

        stmt.executeUpdate();
    }

    @AfterClass
    public static void clean_Database() throws SQLException {
        AccountServiceTest.clean_Database();
        EventServiceTest.clean_Database();
    }
}
