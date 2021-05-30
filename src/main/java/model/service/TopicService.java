package model.service;

import dao.Constants;
import dao.DaoFactory;
import dao.MyException;
import dao.TopicDAO;
import model.entities.Topic;

import java.util.List;

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

    public List<Topic> getOfferedTopics(int speakerId) throws MyException {
        try(TopicDAO dao = daoFactory.createTopicDao()) {
            return dao.getOfferedByAdmin(speakerId);
        }
    }

    public boolean offeredTopicDecision(String decision, int topicID) {
        String query = decision.equals("yes") ?
                Constants.AGREE_FOR_OFFERED_TOPIC : Constants.DISAGREE_FOR_OFFERED_TOPIC;

        try(TopicDAO dao = daoFactory.createTopicDao()) {
            dao.desigionForOfferedTopic(topicID, query);
        } catch (MyException ex) {
            return false;
        }
        return true;
    }
}
