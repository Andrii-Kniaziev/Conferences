package dao.impl;

import dao.Constants;
import dao.DaoFactory;
import dao.MyException;
import dao.VisitDAO;
import dao.mapper.EventMapper;
import dao.mapper.VisitMapper;
import model.entities.Event;
import model.entities.Visit;

import java.sql.*;
import java.util.List;

public class JDBCVisitDAO implements VisitDAO {
    private Connection con;

    public JDBCVisitDAO(Connection con) {
        this.con = con;
    }

    public boolean insertVisit(Visit visit) throws MyException {
        try (PreparedStatement stmt = con.prepareStatement(Constants.INSERT_VISIT)) {

            stmt.setInt(1, visit.getAccountID());
            stmt.setInt(2, visit.getEventID());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MyException("Something went wrong with insertion of new Visit", e);
        }
    }

    public List<Visit> selectVisitsByUser(int listenerID) throws MyException {
        List<Visit> visits;
        ResultSet res = null;

        try(PreparedStatement stmt = con.prepareStatement(Constants.SELECT_VISITS_BY_USER_ID)) {
            stmt.setInt(1, listenerID);
            res = stmt.executeQuery();

            visits = new VisitMapper().getVisitsFromResSet(res);
        } catch (SQLException e) {
            throw new MyException("Something went wrong with getting events from DB", e);
        }

        return visits;
    }

    public boolean deleteVisit(int listenerID, int eventID) throws MyException {
        try(PreparedStatement stmt = con.prepareStatement(Constants.DELETE_VISIT)) {
            stmt.setInt(1, listenerID);
            stmt.setInt(2, eventID);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new MyException("error while deletion of visit", ex);
        }
        return true;
    }

    @Override
    public void close()  {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) throws MyException {
//        JDBCVisitDAO dao = (JDBCVisitDAO) DaoFactory.getInstance().createVisitDao();
//
//        List<Visit> visits = dao.selectVisitsByUser(42);
//
//        for (Visit v : visits) {
//            System.out.println("AccountID " + v.getAccountID() + "EventID" + v.getEventID());
//        }
//    }
}
