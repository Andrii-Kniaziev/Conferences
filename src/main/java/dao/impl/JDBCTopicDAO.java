package dao.impl;

import dao.Constants;
import dao.MyException;
import dao.TopicDAO;
import model.entities.Topic;

import java.sql.*;

public class JDBCTopicDAO implements TopicDAO {
    private Connection con;

    public JDBCTopicDAO(Connection con) {
        this.con = con;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Constants.CONNECTION_URL);
    }

    public boolean insertTopic(Topic topic) throws MyException {
        try (//Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(Constants.INSERT_TOPIC)) {

            stmt.setInt(1, topic.getEventId());
            stmt.setInt(2, topic.getSpeakerId());
            stmt.setString(3, topic.getName());
            stmt.setString(4, topic.getDescription());
            stmt.setString(5, topic.isAdminApproved() + "");
            stmt.setString(6, topic.isSpeakerApproved() + "");
            stmt.setString(7, topic.isDeсisionDone() + "");

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new MyException("Something went wrong with insertion of new Topic", e);
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
}
