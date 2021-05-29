package dao;

import model.entities.Topic;

public interface TopicDAO extends SuperDAO {

    boolean insertTopic(Topic topic) throws MyException;
}
