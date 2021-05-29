package model.service;

import dao.DaoFactory;
import dao.MyException;
import dao.TopicDAO;
import model.entities.Topic;

public class TopicService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public boolean createNewTopic(Topic topic) {
        try(TopicDAO dao = daoFactory.createTopicDao()) {
            return dao.insertTopic(topic);
        } catch (MyException e) {
            System.out.println(e.getCause());
            return false;
        }
    }
}
