package controller.commands;

import dao.Constants;
import model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

public class SubmitTopicToSpeakerCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Properties pr = getProperties(req);

        int topicID = Integer.parseInt(req.getParameter("topicID"));
        int speakerID = Integer.parseInt(req.getParameter("speakerID"));

        TopicService service = new TopicService();

        String success = pr.getProperty("topicNumber") + " " + topicID + " " +
                pr.getProperty("wasProposed") + " " + speakerID;
        String failure = pr.getProperty("tryAgainLater");

        if(service.offerEmptyTopicToSpeaker(topicID, speakerID)) {
            req.setAttribute("result", success);
        } else {
            req.setAttribute("result", failure);
        }

        if(checkLanguageEN(req)) {
            return Constants.ADMIN_ACCOUNT_EN;
        }

        return Constants.ADMIN_ACCOUNT;
    }
}
