package dao.impl;

import dao.Constants;
import dao.DaoFactory;
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

    public boolean insertEvent(Event event) throws MyException {
        try (PreparedStatement stmt = con.prepareStatement(Constants.INSERT_EVENT)) {
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
        List<Event> events;

        try(Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(Constants.GET_NOT_FINISHED_EVENTS)) {

            events = new EventMapper().getEventsFromResultSet(res);
        } catch (SQLException e) {
            throw new MyException("Something went wrong with getting events from DB", e);
        }

        return events;
    }

    public List<Integer> getNotFinishedEventIDs() throws MyException {
        List<Integer> ids = new ArrayList<>();

        try(Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(Constants.SELECT_NOT_FINISHED_EVENTS)) {

            while (res.next()) {
                ids.add(res.getInt(1));
            }
        } catch (SQLException e) {
            throw new MyException("Something went wrong with getting not finished event IDs", e);
        }

        return ids;
    }

    public List<Event> getEventsFrom(int index, String query) throws MyException {
        List<Event> events = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet res = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, index);
            stmt.setInt(2, 5);
            res = stmt.executeQuery();

            events = new EventMapper().getEventsFromResultSet(res);

        } catch (SQLException e) {
            throw new MyException("Something went wrong with getting events from DB", e);
        } finally {
            close(res);
            close(stmt);
        }

        return events;
    }

    public int getEventCount(boolean isNotFinished) {
        String query = isNotFinished ? Constants.GET_NOT_FINISHED_EVENT_COUNT : Constants.GET_FINISHED_EVENT_COUNT;

        try(Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(query)) {

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
//        List<Event> evs = null;
//
//        JDBCEventDAO dao = (JDBCEventDAO) DaoFactory.getInstance().createEventDao();
//
//
//        try {
//            evs = dao.getEventsFrom(0, Constants.GET_FINISHED_EVENTS_LIMIT);
//        } catch (MyException e) {
//            e.printStackTrace();
//        }
//
//
//        for (Event e : evs) {
//            System.out.println(e);
//        }
//
//        EventMapper em = new EventMapper();
//
//        System.out.println(em.getQueryForEventSort("future", "date"));
//        System.out.println("**********************");
//        System.out.println(em.getQueryForEventSort("future", "topicNumber"));
//        System.out.println("**********************");
//        System.out.println(em.getQueryForEventSort("future", "listenersNumber"));
//        System.out.println("**********************");
//    }
}
