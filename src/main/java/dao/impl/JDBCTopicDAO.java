package dao.impl;

import dao.Constants;
import dao.DaoFactory;
import dao.MyException;
import dao.TopicDAO;
import dao.mapper.TopicMapper;
import model.entities.Topic;

import java.sql.*;
import java.util.List;

public class JDBCTopicDAO implements TopicDAO {
    private Connection con;

    public JDBCTopicDAO(Connection con) {
        this.con = con;
    }

    public boolean insertTopic(Topic topic) throws MyException {
        try (PreparedStatement stmt = con.prepareStatement(Constants.INSERT_TOPIC)) {

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

    public List<Topic> getTopicsWithoutSpeakers() throws MyException {
        List<Topic> noSpeacersTopics;

        try (Statement stmt = con.createStatement()) {
            ResultSet res = stmt.executeQuery(Constants.FIND_TOPICS_WITHOUT_SPEAKERS);

            noSpeacersTopics = new TopicMapper().getTopicsFromResSet(res);

        } catch (SQLException e) {
            throw new MyException("Something went wrong with getting of topics without speakers", e);
        }

        return noSpeacersTopics;
    }

    public List<Topic> getOfferedByAdmin(int accountID) throws MyException {
        List<Topic> offeredTopics;
        ResultSet res = null;

        try (PreparedStatement stmt = con.prepareStatement(Constants.CHECK_OFFERED_TOPICS)) {
            stmt.setInt(1, accountID);
            res = stmt.executeQuery();

            offeredTopics = new TopicMapper().getTopicsFromResSet(res);

        } catch (SQLException e) {
            throw new MyException("Something went wrong with getting of topics", e);
        } finally {
            close(res);
        }

        return offeredTopics;
    }

    public boolean offerEmptyTopic(int topicID, int speakerID) throws MyException {
        try (PreparedStatement stmt = con.prepareStatement(Constants.OFFER_EMPTY_TOPIC_TO_SPEAKER)) {
            stmt.setInt(1, speakerID);
            stmt.setInt(2, topicID);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new MyException("Something went wrong with updating of topics", e);
        }

        return true;
    }

    public boolean desigionForOfferedTopic(int topicID, String query) throws MyException {
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, topicID);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new MyException("Something went wrong with updating of topics", e);
        }

        return true;
    }

    @Override
    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) {
//        List<Topic> tops = null;
//
//        JDBCTopicDAO dao = (JDBCTopicDAO) DaoFactory.getInstance().createTopicDao();
//
//        try {
//            tops = dao.getOfferedByAdmin(18);
//        } catch (MyException e) {
//            e.printStackTrace();
//        }
//
//        for (Topic t : tops) {
//            System.out.println(t);
//        }
//    }
}
