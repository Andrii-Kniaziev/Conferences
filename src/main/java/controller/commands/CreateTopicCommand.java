package controller.commands;

import dao.Constants;
import model.entities.Topic;
import model.entities.builders.TopicBuilderImpl;
import model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * This command is responsible for creation of new topics
 * under corresponding events. In both scenarios: success
 * and failure, message with result appears.
 */

public class CreateTopicCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Properties pr = getProperties(req);

        boolean speakerApproved = Boolean.parseBoolean(req.getParameter("speakerApproved"));
        String topicName = req.getParameter("topicName");
        String description = req.getParameter("topicDescription");
        int eventId;
        int speakerId;

        try {
            eventId = Integer.parseInt(req.getParameter("eventID"));
            speakerId = Integer.parseInt(req.getParameter("speakerID"));
            if(topicName == null || description == null) {
                throw new RuntimeException();
            }
        } catch (RuntimeException ex) {
            req.setAttribute("result", pr.getProperty("emptyFields"));
            return getNextStep(req);
        }

        //Topic topic = new Topic(eventId, speakerId, topicName, description, true, speakerApproved);

        Topic topic = new TopicBuilderImpl()
                .setEventId(eventId)
                .setSpeakerId(speakerId)
                .setName(topicName)
                .setDescription(description)
                .setAdminApproved(true)
                .setSpeakerApproved(speakerApproved)
                .build();

        if(new TopicService().createNewTopic(topic)) {
            req.setAttribute("result", pr.getProperty("newTopic") + topicName + " "
                    + pr.getProperty("created"));
        } else {
            req.setAttribute("result", pr.getProperty("topicNotCreated"));
        }

        return getNextStep(req);
    }

    public String getNextStep(HttpServletRequest req) {
        if(checkLanguageEN(req)) {
            return Constants.ADMIN_ACCOUNT_EN;
        }

        return Constants.ADMIN_ACCOUNT;
    }
}
