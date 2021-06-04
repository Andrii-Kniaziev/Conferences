package controller.commands;

import dao.Constants;
import dao.MyException;
import model.entities.Topic;
import model.service.EventService;
import model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This command is called in case when speaker wants
 * to offer own new topic for some event
 */

public class OfferTopicForEvent implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        String name = req.getParameter("topicName");
        String description = req.getParameter("topicDescription");
        int eventID = Integer.parseInt(req.getParameter("eventID"));
        int speakerID = Integer.parseInt((String) req.getSession().getAttribute(Constants.FIELD_ID));

        if(!new EventService().checkEventIsNotFinished(eventID)) {
            req.setAttribute("result", "Вказанний вами ID належить івенту що вже закінчився або не існує");

            return choosePage(req);
        }

        TopicService topicService = new TopicService();
        boolean res = topicService.createNewTopic(new Topic(eventID, speakerID, name, description,
                false, true, false));

        if(res) {
            req.setAttribute("result", "Ви запропонували нову доповідь" + name);
        } else {
            req.setAttribute("result", "Щось пішло не так");
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
