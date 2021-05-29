package dao.impl;

import dao.Constants;
import dao.MyException;
import dao.VisitDAO;
import model.entities.Visit;

import java.sql.*;

public class JDBCVisitDAO implements VisitDAO {
    private Connection con;

    public JDBCVisitDAO(Connection con) {
        this.con = con;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Constants.CONNECTION_URL);
    }

    public boolean insertVisit(Visit visit) throws MyException {
        try (//Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(Constants.INSERT_VISIT)) {

            stmt.setInt(1, visit.getAccountID());
            stmt.setInt(2, visit.getEventID());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MyException("Something went wrong with insertion of new Visit", e);
        }
    }

    @Override
    public void close()  {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
