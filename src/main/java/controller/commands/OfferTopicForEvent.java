package controller.commands;

import dao.Constants;
import dao.MyException;
import model.entities.builders.TopicBuilderImpl;
import model.service.EventService;
import model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * This command is called in case when speaker wants
 * to offer own new topic for some event
 */

public class OfferTopicForEvent implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        Properties pr = getProperties(req);
        String name = req.getParameter("topicName");
        String description = req.getParameter("topicDescription");
        int eventID = Integer.parseInt(req.getParameter("eventID"));
        int speakerID = Integer.parseInt((String) req.getSession().getAttribute(Constants.FIELD_ID));

        if(name == null || description == null) {
            req.setAttribute("result", pr.getProperty("emptyFields"));
            return choosePage(req);
        }

        if(!new EventService().checkEventIsNotFinished(eventID)) {
            req.setAttribute("result", pr.getProperty("idOfEventThatWasFinishedOrNotExists"));
            return choosePage(req);
        }

        boolean res = new TopicService().createNewTopic(new TopicBuilderImpl()
                .setEventId(eventID)
                .setSpeakerId(speakerID)
                .setName(name)
                .setDescription(description)
                .setAdminApproved(false)
                .setSpeakerApproved(true)
                .setDecisionDone(false)
                .build());

        if(res) {
            req.setAttribute("result", pr.getProperty("youHaveOfferedNewTopic") + " " + name);
        } else {
            req.setAttribute("result", pr.getProperty("tryAgainLater"));
        }

        return choosePage(req);
    }

    private String choosePage(HttpServletRequest req) {
        if (checkLanguageEN(req)) {
            return Constants.SPEAKER_ACCOUNT_EN;
        }

        return Constants.SPEAKER_ACCOUNT;
    }
}
