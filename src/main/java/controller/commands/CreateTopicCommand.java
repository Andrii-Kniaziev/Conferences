package controller.commands;

import dao.Constants;
import dao.MyException;
import model.entities.Topic;
import model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

public class CreateTopicCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        Properties pr = getProperties(req);

        String topicName = req.getParameter("topicName");
        String description = req.getParameter("topicDescription");
        int eventId = Integer.parseInt(req.getParameter("eventID"));
        int speakerId = Integer.parseInt(req.getParameter("speakerID"));
        boolean speakerApproved = Boolean.parseBoolean(req.getParameter("speakerApproved"));

        Topic topic = new Topic(eventId, speakerId, topicName, description, true, speakerApproved);

        System.out.println(topic.getName());

        TopicService service = new TopicService();
        boolean res = service.createNewTopic(topic);

        if(res) {
            req.setAttribute("result", pr.getProperty("newTopic") + topicName + " "
                    + pr.getProperty("created"));
        } else {
            req.setAttribute("result", pr.getProperty("topicNotCreated"));
        }

        if(checkLanguageEN(req)) {
            return Constants.ADMIN_ACCOUNT_EN;
        }

        return Constants.ADMIN_ACCOUNT;
    }
}
