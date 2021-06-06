package model.service;

import dao.MyException;
import dao.impl.ConnectionPoolHolder;
import model.entities.Event;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class EventServiceTest {
    private static final String CLEAN_DB = "DELETE FROM event WHERE name = 'Test' AND place = 'Test'";
    private EventService service;
    private Event event;


    @Before
    public void setUp() {
        service = new EventService();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2021, Calendar.DECEMBER, 11);
        event = new Event("Test", "Test", calendar, "Test", false);
    }

    @Test
    public void insert_event_test() throws MyException {
        assertTrue(service.insertEvent(event));
    }

    @Test
    public void get_events_from_index_test() throws MyException {
        List<Event> events = service.getEventsFromIndex(0);

        assertEquals(5, events.size());

        for(Event e : events) {
            assertTrue(System.currentTimeMillis() < e.getCalendar().getTimeInMillis());
        }
    }

    @Test
    public void get_sorted_events_from_index() throws MyException {
        List<Event> events = service.getSortedEventsFromIndex(0, "future", "date");

        for (int i = 0; i < events.size(); i++) {
            if(i == 4) {
                break;
            }
            assertTrue(events.get(i).getCalendar().getTimeInMillis()
                    > events.get(i + 1).getCalendar().getTimeInMillis());
        }
    }

    @Test
    public void check_event_not_finished_test() throws MyException {
        assertFalse(service.checkEventIsNotFinished(1));
    }

    @Test
    public void get_count_of_pages_test() throws MyException {
        List<Event> notFinishedEvents = service.getAllEvents();

        int count = notFinishedEvents.size();

        if(count - (count/5) * 5 != 0) {
            count = count/5 + 1;
        } else {
            count = count/5;
        }

        assertTrue(service.getCountOfPages(true).size() == count);
    }

    @AfterClass
    public static void clean_Database() throws SQLException {
        Connection connection = ConnectionPoolHolder.getDataSource().getConnection();

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(CLEAN_DB);

        stmt.close();
        connection.close();
    }
}
