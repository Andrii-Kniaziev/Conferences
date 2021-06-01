package dao;

import model.entities.Topic;

import java.util.List;

public interface TopicDAO extends SuperDAO {

    boolean insertTopic(Topic topic) throws MyException;

    public List<Topic> getOfferedByAdmin (int accountID) throws MyException;

    public boolean desigionForOfferedTopic(int topicID, String query) throws MyException;

    public List<Topic> getTopicsWithoutSpeakers() throws MyException;

    public boolean offerEmptyTopic(int topicID, int speakerID) throws MyException;
}
