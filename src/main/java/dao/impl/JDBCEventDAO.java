package dao.impl;

import dao.Constants;
import dao.DaoFactory;
import dao.EventDAO;
import dao.MyException;
import dao.mapper.EventMapper;
import model.entities.Event;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCEventDAO implements EventDAO {
    private static Logger logger = Logger.getLogger(JDBCEventDAO.class);
    private Connection con;

    public JDBCEventDAO(Connection con) {
        this.con = con;
    }

    /**
     * Method creates new event in DB
     * @param event represents event to be created
     * @return 'true' in case of success
     * @throws MyException in case if something is wrong with connection to DB
     */

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
            logger.error(e);
            throw new MyException("Something went wrong with insertion of new Event", e);
        }
        return true;
    }

    /**
     * Method returns list of events that arn`t finished
     * @return list of not finished events
     * @throws MyException in case if something is wrong with connection to DB
     */

    public List<Event> getAllEvents() throws MyException {
        List<Event> events;

        try(Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(Constants.GET_NOT_FINISHED_EVENTS)) {

            events = new EventMapper().getEventsFromResultSet(res);
        } catch (SQLException e) {
            logger.error(e);
            throw new MyException("Something went wrong with getting events from DB", e);
        }

        return events;
    }

    /**
     * Method gets IDs of not finished events
     * @return list of IDs of not finished events
     * @throws MyException in case if something is wrong with connection to DB
     */

    public List<Integer> getNotFinishedEventIDs() throws MyException {
        List<Integer> ids = new ArrayList<>();

        try(Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(Constants.SELECT_NOT_FINISHED_EVENTS)) {

            while (res.next()) {
                ids.add(res.getInt(1));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new MyException("Something went wrong with getting not finished event IDs", e);
        }

        return ids;
    }

    /**
     * Method gets list of 5 Events starting from some index
     * @param index starting index
     * @param query different queries
     * for different kinds of sorting
     * @return list of sorted events, starting from index
     * @throws MyException in case if something is wrong with connection to DB
     */

    public List<Event> getEventsFrom(int index, String query) throws MyException {
        PreparedStatement stmt = null;
        ResultSet res = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, index);
            stmt.setInt(2, 5);
            res = stmt.executeQuery();

            return new EventMapper().getEventsFromResultSet(res);

        } catch (SQLException e) {
            logger.error(e);
            throw new MyException("Something went wrong with getting events from DB", e);
        } finally {
            close(res);
            close(stmt);
        }
    }

    /**
     * Method returns count of not finished or finished events
     * @param isNotFinished indicator
     * @return count
     */

    public int getEventCount(boolean isNotFinished) {
        String query = isNotFinished ? Constants.GET_NOT_FINISHED_EVENT_COUNT : Constants.GET_FINISHED_EVENT_COUNT;

        try(Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(query)) {

            if(res.next()) {
                return res.getInt("count(*)");
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return -1;
    }

    @Override
    public void close()  {
        try {
            con.close();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

}
