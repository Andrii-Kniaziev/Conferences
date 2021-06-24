package model.service;

import dao.MyException;
import dao.impl.ConnectionPoolHolder;
import model.entities.Account;
import model.entities.Event;
import model.entities.Role;
import model.entities.Visit;
import model.entities.builders.AccountBuilderImpl;
import org.junit.*;

import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class VisitServiceTest {
    private VisitService service;
    private static int listenerID;
    private static int eventID;

    @BeforeClass
    public static void create_listener_and_event_in_DB_for_test() throws MyException, SQLException {
        Account listener = new AccountBuilderImpl().setEmail("test@gmail.com").setPassword("Aa1@0000")
                .setFirstName("Test").setLastName("Test").setRole(Role.ADMIN).build();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2021, Calendar.DECEMBER, 11);
        Event event = new Event("Test", "Test", calendar, "Test", false);

        AccountService accountService = new AccountService();

        accountService.createNewAccount(listener);
        new EventService().insertEvent(event);

        listenerID = new AccountService().getAccountByEmail("test@gmail.com").getId();

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
        service = new VisitService();
    }

    @Test
    public void create_new_visit_test() {
        assertTrue(service.createNewVisit(new Visit(listenerID, eventID)));
    }

    @Test
    public void get_events_ids_by_listener_id() throws MyException {
        create_new_visit_test();
        List<Integer> eventsIDs = service.getEventsIDsByListener(listenerID);
        int eventIDFromDB = eventsIDs.get(0);

        assertEquals(eventsIDs.size(), 1);
        assertEquals(eventIDFromDB, eventID);
    }

    @Test
    public void unsubscribe_from_visit_test() {
        create_new_visit_test();
        service.unsubscribeFromEvent(listenerID, eventID);
    }

    @After
    public void clean_visit() throws SQLException {
        Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
        PreparedStatement stmt = connection.prepareStatement
                ("DELETE FROM event_visitor WHERE account_id = ? AND event_id = ?");
        stmt.setInt(1, listenerID);
        stmt.setInt(2, eventID);

        stmt.executeUpdate();
    }

    @AfterClass
    public static void clean_Database() throws SQLException {
        AccountServiceTest.clean_Database();
        EventServiceTest.clean_Database();
    }
}
