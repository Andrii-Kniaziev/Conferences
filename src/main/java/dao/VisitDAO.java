package dao;

import model.entities.Visit;

import java.sql.*;

public class VisitDAO {
    private static VisitDAO dao;

    private VisitDAO() {
    }

    public static synchronized VisitDAO getInstance() {
        if (dao == null) {
            dao = new VisitDAO();
        }
        return dao;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Constants.CONNECTION_URL);
    }

    public boolean insertVisit(Visit visit) throws MyException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(Constants.INSERT_VISIT)) {

            stmt.setInt(1, visit.getAccountID());
            stmt.setInt(2, visit.getEventID());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MyException("Something went wrong with insertion of new Visit", e);
        }
    }


}
