package dao;

import model.entities.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class EventDAO {
    private static EventDAO dao;

    private EventDAO() {
    }

    public static synchronized EventDAO getInstance() {
        if (dao == null) {
            dao = new EventDAO();
        }
        return dao;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Constants.CONNECTION_URL);
    }

    public boolean insertEvent(Event event) throws MyException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(Constants.INSERT_EVENT)) {
            Timestamp ts = new Timestamp(event.getCalendar().getTimeInMillis());

            stmt.setString(1, event.getName());
            stmt.setString(2, event.getDescription());
            stmt.setTimestamp(3, ts);
            stmt.setString(4, event.getPlace());
            stmt.setString(5, event.isFinished() + "");

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new MyException("Something went wrong with insertion of new Event", e);
        }
        return true;
    }

    public List<Event> getAllEvents() throws MyException {
        List<Event> events = new ArrayList<>();

        try(Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(Constants.GET_ALL_EVENTS)) {

            while(res.next()) {
                int id = res.getInt(Constants.FIELD_ID);
                String name = res.getString(Constants.FIELD_NAME);
                String description = res.getString(Constants.FIELD_DESCRIPTION);
                Date date = new Date(res.getTimestamp(Constants.FIELD_DATE).getTime());

                String place = res.getString(Constants.FIELD_PLACE);
                boolean isFinished = Boolean.parseBoolean(res.getString(Constants.FIELD_IS_FINISHED));

                GregorianCalendar c = new GregorianCalendar();
                c.setTimeInMillis(date.getTime());

                events.add(new Event(id, name, description, c, place, isFinished ));
            }
        } catch (SQLException e) {
            throw new MyException("Something went wrong with getting events from DB", e);
        }

        return events;
    }

//    public static void main(String[] args) {
//        List<Event> events = null;
//
//        EventDAO dao = EventDAO.getInstance();
//
//        try {
//            events = dao.getAllEvents();
//        } catch (MyException e) {
//            System.out.println(e.getCause());
//        }
//
//        for (Event e : events) {
//            System.out.println(e);
//        }
//    }
}
