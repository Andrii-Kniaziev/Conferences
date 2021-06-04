package dao.impl;

import dao.Constants;
import dao.DaoFactory;
import dao.MyException;
import dao.TopicDAO;
import dao.mapper.TopicMapper;
import model.entities.Topic;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class JDBCTopicDAO implements TopicDAO {
    private static Logger logger = Logger.getLogger(JDBCTopicDAO.class);
    private Connection con;

    public JDBCTopicDAO(Connection con) {
        this.con = con;
    }

    /**
     * Method creates new Topic in DB
     * @param topic representation of topic to be created
     * @return 'true' in case of success
     * @throws MyException if something is wrong with connection to DB
     */

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
            logger.error(e);
            throw new MyException("Something went wrong with insertion of new Topic", e);
        }
        return true;
    }

    /**
     * Returns list of topics which speakers denied to spend
     * @return list of topics which speakers denied to spend
     * @throws MyException if something is wrong with connection to DB
     */

    public List<Topic> getTopicsWithoutSpeakers() throws MyException {
        List<Topic> noSpeakersTopics;

        try (Statement stmt = con.createStatement()) {
            ResultSet res = stmt.executeQuery(Constants.FIND_TOPICS_WITHOUT_SPEAKERS);

            noSpeakersTopics = new TopicMapper().getTopicsFromResSet(res);

        } catch (SQLException e) {
            logger.error(e);
            throw new MyException("Something went wrong with getting of topics without speakers", e);
        }

        return noSpeakersTopics;
    }

    /**
     * Method returns list of topics which are related
     * to some speaker
     * @param accountID indicator of speaker
     * @param query specified selector for topics
     * @return list of topics which were chosen by query
     * and related to speaker
     * @throws MyException if something is wrong with connection to DB
     */

    public List<Topic> getTopicsByQuery(int accountID, String query) throws MyException {
        List<Topic> topics;
        ResultSet res = null;

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, accountID);
            res = stmt.executeQuery();

            topics = new TopicMapper().getTopicsFromResSet(res);

        } catch (SQLException e) {
            logger.error(e);
            throw new MyException("Something went wrong with getting of topics", e);
        } finally {
            close(res);
        }

        return topics;
    }

    /**
     * Method updates 'topic' table in DB. This means admin can offer
     * topic that was rejected by speaker before, to another speaker
     * @param topicID indicator of topic
     * @param speakerID indicator of speaker
     * @return 'true' in case of success
     * @throws MyException if something is wrong with connection to DB
     */

    public boolean offerEmptyTopic(int topicID, int speakerID) throws MyException {
        try (PreparedStatement stmt = con.prepareStatement(Constants.OFFER_EMPTY_TOPIC_TO_SPEAKER)) {
            stmt.setInt(1, speakerID);
            stmt.setInt(2, topicID);

            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            throw new MyException("Something went wrong with updating of topics", e);
        }

        return true;
    }

    /**
     * This method represents approvement or declination of offered by speaker
     * topic for some events. Decision is done by admin
     * @param topicID indicator of topic
     * @param isApproved decision of admin
     * @return 'true' in case of successfully finished operation
     * and false in case if there was a problem with connection to DB
     */

    public boolean approveOrDenyOfferedTopic(int topicID, boolean isApproved) {
        String query = isApproved ? Constants.APPROVE_OFFERED_TOPIC : Constants.DENY_OFFERED_TOPIC;

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, topicID);

            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            return false;
        }

        return true;
    }

    /**
     * Method represents decision of speaker about approvement or
     * rejection of spending some topic in event that was offered
     * by admin
     * @param topicID indicator of topic
     * @param query to be executed, means approvement or rejection
     * of topic by speaker in DB
     * @return 'true' in case if operation was executed successfully
     * @throws MyException if something is wrong with connection to DB
     */

    public boolean decisionForOfferedTopic(int topicID, String query) throws MyException {
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, topicID);

            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            throw new MyException("Something went wrong with updating of topics", e);
        }

        return true;
    }

    /**
     * method returns list of topics that were offered by speakers for
     * events, but not approved yet by admin
     * @return list of topics waiting for decision of admin
     * @throws MyException if something is wrong with connection to DB
     */

    public List<Topic> getProposedTopics() throws MyException {
        try (Statement stmt = con.createStatement()) {
            ResultSet res = stmt.executeQuery(Constants.SELECT_PROPOSED_FOR_ADMIN_DECISION_TOPICS);

            return new TopicMapper().getTopicsFromResSet(res);

        } catch (SQLException e) {
            logger.error(e);
            throw new MyException("Something went wrong with getting of topics without speakers", e);
        }
    }

    @Override
    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

}
