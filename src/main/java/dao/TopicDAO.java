package dao;

import model.entities.Topic;

import java.util.List;

public interface TopicDAO extends SuperDAO {

    boolean insertTopic(Topic topic) throws MyException;

    boolean decisionForOfferedTopic(int topicID, String query) throws MyException;

    List<Topic> getTopicsWithoutSpeakers() throws MyException;

    boolean offerEmptyTopic(int topicID, int speakerID) throws MyException;

    List<Topic> getTopicsByQuery(int accountID, String query) throws MyException;

    List<Topic> getProposedTopics() throws MyException;

    boolean approveOrDenyOfferedTopic(int topicID, boolean isApproved);
}
