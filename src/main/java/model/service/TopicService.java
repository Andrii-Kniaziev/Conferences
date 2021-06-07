package model.service;

import dao.Constants;
import dao.DaoFactory;
import dao.MyException;
import dao.TopicDAO;
import model.entities.Topic;

import java.util.List;

public class TopicService {
    private DaoFactory daoFactory;

    public TopicService() {
        daoFactory = DaoFactory.getInstance();
    }

    /**
     * Method creates new topic.
     * @param topic topic to be added to DB
     * @return true in case of success,
     * or false if something is wrong with connection to DB.
     */

    public boolean createNewTopic(Topic topic) {
        try(TopicDAO dao = daoFactory.createTopicDao()) {
            return dao.insertTopic(topic);
        } catch (MyException e) {
            return false;
        }
    }

    /**
     * Method returns list of Topics which were offered by admin to some speaker.
     * @param speakerId ID of speaker
     * @return list of topics which admin offered to spend to speaker
     * but speaker hasn`t done decision yet.
     * @throws MyException in case of problems with connection to DB.
     */

    public List<Topic> getOfferedTopics(int speakerId) throws MyException {
        try(TopicDAO dao = daoFactory.createTopicDao()) {
            return dao.getTopicsByQuery(speakerId, Constants.CHECK_OFFERED_TOPICS);
        }
    }

    /**
     * Method returns list of Topics which speaker agree to spend.
     * @param speakerID ID of speaker
     * @return list of topics which speaker will spend.
     * @throws MyException in case of problems with connection to DB
     */

    public List<Topic> getAgreedTopics(int speakerID) throws MyException {
        try(TopicDAO dao = daoFactory.createTopicDao()) {
            return dao.getTopicsByQuery(speakerID, Constants.SELECT_AGREED_TOPIC_BY_SPEAKER_ID);
        }
    }

    /**
     * Method gets list of topics by speaker`s ID, that he offered
     * to admin for some Event.
     * @return list of topics which are not yet approved by admin.
     * @throws MyException in case of absence connection to DB
     */

    public List<Topic> getTopicsInProcess(int speakerID) throws MyException {
        try(TopicDAO dao = daoFactory.createTopicDao()) {
            return dao.getTopicsByQuery(speakerID, Constants.SELECT_TOPICS_IN_PROCESS);
        }
    }

    /**
     * Method returns topics which speaker denied to spend, and admin wants
     * to offer to somebody else to spend.
     * @return list of topics which speakers denied to spend
     * @throws MyException in case of absence connection to DB
     */

    public List<Topic> getTopicsWithoutSpeakers() throws MyException {
        try(TopicDAO dao = daoFactory.createTopicDao()) {
            return dao.getTopicsWithoutSpeakers();
        }
    }

    /**
     * This method will get list of topics which were proposed
     * for events from speakers, but not yet approved by admin
     */

    public List<Topic> getProposedTopics() throws MyException {
        try(TopicDAO dao = daoFactory.createTopicDao()) {
            return dao.getProposedTopics();
        }
    }

    /**
     * This method will return true in case when query was successfully executed
     * (proposed from listener topic was approved or denied by admin)
     * In case if something went wrong with connection to the database
     * and query was not executed method returns false.
     */

    public boolean approveOrDenyOfferedTopic(int topicID, String decision) {
        try(TopicDAO dao = daoFactory.createTopicDao()) {
            return dao.approveOrDenyOfferedTopic(topicID, decision.equals("yes"));
        }
    }

    /**
     * In this method speaker can make decision about what topics he wants to spend,
     * from the list {@link #getOfferedTopics(int speakerID)}
     * @param decision 'yes' or 'no'
     * @param topicID ID of topic
     * @return 'true' in case of success or 'false' in case of
     * problems of connection to DB
     */

    public boolean offeredTopicDecision(String decision, int topicID) {
        String query = decision.equals("yes") ?
                Constants.AGREE_FOR_OFFERED_TOPIC : Constants.DISAGREE_FOR_OFFERED_TOPIC;

        try(TopicDAO dao = daoFactory.createTopicDao()) {
            dao.decisionForOfferedTopic(topicID, query);
        } catch (MyException ex) {
            return false;
        }
        return true;
    }

    /**
     * In case when some speaker denied to spend some topic
     * it is possible to offer it to someone else.
     * List of such topics we get from {@link #getTopicsWithoutSpeakers()}
     * @param topicID ID of topic
     * @param speakerID ID of speaker
     * @return 'true' in case of success or 'false' in case of
     * problems of connection to DB
     */

    public boolean offerEmptyTopicToSpeaker(int topicID, int speakerID) {
        try(TopicDAO dao = daoFactory.createTopicDao()) {
            dao.offerEmptyTopic(topicID, speakerID);
        } catch (MyException ex) {
            return false;
        }
        return true;
    }

}
