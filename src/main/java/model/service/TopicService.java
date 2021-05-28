package model.service;

import dao.MyException;
import dao.TopicDAO;
import model.entities.Topic;

public class TopicService {
    private TopicDAO topicDAO = TopicDAO.getInstance();

    public boolean createNewTopic(Topic topic) {
        try {
            topicDAO.insertTopic(topic);
        } catch (MyException e) {
            System.out.println(e.getCause());
            return false;
        }
        return true;
    }
}
