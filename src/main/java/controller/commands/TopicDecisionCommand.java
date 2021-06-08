package controller.commands;

import dao.Constants;
import model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * This class is related to decision of admin to approve
 * or deny some topics which were offered by speakers for
 * events.
 */

public class TopicDecisionCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Properties pr = getProperties(req);
        int topicID = Integer.parseInt(req.getParameter("topicID"));
        String decision = req.getParameter("decision");

        TopicService topicService = new TopicService();
        boolean res = topicService.approveOrDenyOfferedTopic(topicID, decision);

        if(res && decision.equals("yes")) {
            req.setAttribute("result", pr.getProperty("youHaveApprovedTopicFromSpeaker") + " " + topicID);
        } else if (res && decision.equals("no")) {
            req.setAttribute("result", pr.getProperty("youHaveRejectedTopicFromSpeaker") + " " + topicID);
        } else {
            req.setAttribute("result", pr.getProperty("tryAgainLater"));
        }

        if(checkLanguageEN(req)) {
            return Constants.ADMIN_ACCOUNT_EN;
        }

        return Constants.ADMIN_ACCOUNT;
    }
}
