package controller.commands;

import dao.Constants;
import model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * This command is used when speaker makes decision
 * about offered by admin topics. Admin can offer
 * to spend some topic to speaker and speaker can
 * confirm or decline this offer.
 */

public class OfferedTopicDecisionCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Properties pr = getProperties(req);
        TopicService service = new TopicService();

        String decision = req.getParameter("decision");
        int topicID = Integer.parseInt(req.getParameter("topicID"));

        boolean isExecuted = service.offeredTopicDecision(decision, topicID);
        String result = decision.equals("yes") ?
                pr.getProperty("youAgreedSpendTopic") + topicID :
                pr.getProperty("youDeniedSpendTopic") + topicID;

        if (!isExecuted) {
            result = pr.getProperty("tryAgainLater");
        }

        req.setAttribute("result", result);

        if(checkLanguageEN(req)) {
            return Constants.SPEAKER_ACCOUNT_EN;
        }

        return Constants.SPEAKER_ACCOUNT;
    }
}
