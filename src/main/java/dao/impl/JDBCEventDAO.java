package dao.impl;

import dao.Constants;
import dao.EventDAO;
import dao.MyException;
import dao.mapper.EventMapper;
import model.entities.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCEventDAO implements EventDAO {
    private Connection con;

    public JDBCEventDAO(Connection con) {
        this.con = con;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Constants.CONNECTION_URL);
    }

    public boolean insertEvent(Event event) throws MyException {
        try (//Connection con = getConnection();
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

    public List<Event> getAllEvents(boolean isNotFinished) throws MyException {
        List<Event> events = new ArrayList<>();

        String query = isNotFinished ? Constants.GET_NOT_FINISHED_EVENTS : Constants.GET_ALL_EVENTS;

        try(//Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query)) {

            events = new EventMapper().getEventsFromResultSet(res);
        } catch (SQLException e) {
            throw new MyException("Something went wrong with getting events from DB", e);
        }

        return events;
    }

    public List<Event> getEventsFrom(int index) throws MyException {
        List<Event> events = new ArrayList<>();

        //Connection con = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement(Constants.GET_EVENTS_FROM_INDEX);
            stmt.setInt(1, index);
            stmt.setInt(2, 5);
            res = stmt.executeQuery();

            events = new EventMapper().getEventsFromResultSet(res);

        } catch (SQLException e) {
            throw new MyException("Something went wrong with getting events from DB", e);
        } finally {
            close(res);
            close(stmt);
            //close(con);
        }

        return events;
    }

    public int getNotFinishedEventCount() {
        try(Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(Constants.GET_NOT_FINISHED_EVENT_COUNT)) {

            if(res.next()) {
                return res.getInt("count(*)");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return -1;
    }

    @Override
    public void close()  {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
