package dao.impl;

import dao.Constants;
import dao.MyException;
import dao.VisitDAO;
import dao.mapper.VisitMapper;
import model.entities.Visit;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class JDBCVisitDAO implements VisitDAO {
    private static Logger logger = Logger.getLogger(JDBCVisitDAO.class);
    private Connection con;

    public JDBCVisitDAO(Connection con) {
        this.con = con;
    }

    /**
     * Method creates new visit of user
     * for some event
     * @param visit to be created
     * @return true in case of success
     * @throws MyException if something went wrong
     * with connection to DB
     */

    public boolean insertVisit(Visit visit) throws MyException {
        try (PreparedStatement stmt = con.prepareStatement(Constants.INSERT_VISIT)) {

            stmt.setInt(1, visit.getAccountID());
            stmt.setInt(2, visit.getEventID());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e);
            throw new MyException("Something went wrong with insertion of new Visit", e);
        }
    }

    /**
     * Method returns list of visits of listener by listener`s ID
     * @param listenerID indicator of listener
     * @return list of listener`s visits
     * @throws MyException if something went wrong
     * with connection to DB
     */

    public List<Visit> selectVisitsByUser(int listenerID) throws MyException {
        List<Visit> visits;
        ResultSet res = null;

        try(PreparedStatement stmt = con.prepareStatement(Constants.SELECT_VISITS_BY_USER_ID)) {
            stmt.setInt(1, listenerID);
            res = stmt.executeQuery();

            visits = new VisitMapper().getVisitsFromResSet(res);
        } catch (SQLException ex) {
            logger.error(ex);
            throw new MyException("Something went wrong with getting events from DB", ex);
        }

        return visits;
    }

    /**
     * Method deletes visit of listener to the specified event
     * @param listenerID indicator of listener
     * @param eventID indicator of event
     * @return 'true' in case of success
     * @throws MyException if something went wrong
     * with connection to DB
     */

    public boolean deleteVisit(int listenerID, int eventID) throws MyException {
        try(PreparedStatement stmt = con.prepareStatement(Constants.DELETE_VISIT)) {
            stmt.setInt(1, listenerID);
            stmt.setInt(2, eventID);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new MyException("error while deletion of visit", ex);
        }
        return true;
    }

    /**
     * Method marks presence or absence of the listener
     * on the event for which this listener was subscribed
     * @param listenerID indicator of listener
     * @param eventID indicator of event
     * @param presence 'yes' or 'no'
     * @return 'true' in case of success
     * @throws MyException if something went wrong
     * with connection to DB
     */

    public boolean markPresence(int listenerID, int eventID, String presence) throws MyException {
        try(PreparedStatement stmt = con.prepareStatement(Constants.MARK_PRESENCE)) {
            stmt.setString(1, presence);
            stmt.setInt(2, listenerID);
            stmt.setInt(3, eventID);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new MyException("error while mark visit presence", ex);
        }
        return true;
    }

    /**
     * Method gets data from DB that represents count of
     * registered listeners for some event, and count of
     * listeners that actually came
     * @param eventID indicator of event
     * @return String that consists of two parts:
     * first part is total amount of registered listeners
     * second part is amount of listeners that actually came
     * parts are separated by the space between them
     */

    public String[] getStatistics(int eventID) {
        String[] result = new String[2];
        PreparedStatement stmt = null;
        ResultSet res = null;
        Savepoint savepoint = null;
        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(Constants.COUNT_ALL_VISITS_OF_EVENT);
            stmt.setInt(1, eventID);

            savepoint = con.setSavepoint();

            res = stmt.executeQuery();
            if(res.next()) { result[0] = res.getInt(1) + ""; }

            stmt = con.prepareStatement(Constants.COUNT_CONFIRMED_VISITS_OF_EVENT);
            stmt.setInt(1, eventID);
            res = stmt.executeQuery();

            if(res.next()) { result[1] = res.getInt(1) + ""; }

            con.commit();

        } catch (SQLException e) {
            logger.error(e);
            try {
                if (con != null) { con.rollback(savepoint); }
            } catch (SQLException ex) {
                logger.error(ex);
            }
        } finally {
            close(res);
            close(stmt);
        }
        return result;
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
