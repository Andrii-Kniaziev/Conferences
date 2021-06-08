package controller.commands;

import dao.Constants;
import dao.MyException;
import model.entities.Account;
import model.entities.Topic;
import model.service.AccountService;
import model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This command sends data for special form available
 * only for admin. At this form admin can offer to spend
 * some topics without speakers to another speakers.
 * Also at this form admin can confirm or reject offers
 * from speakers to add some topic to event and spend it.
 * On the jsp will be checked if previous speaker rejected
 * admin`s offer to spend topic, admin will not be able to
 * send this topic second time to the same speaker.
 */

public class OfferTopicInfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        TopicService topicService = new TopicService();
        AccountService accService = new AccountService();

        List<Topic> topicsWithoutSpeakers = topicService.getTopicsWithoutSpeakers();
        List<Topic> proposedTopics = topicService.getProposedTopics();
        List<Account> speakers = accService.getAccountsByRole(Constants.ROLE_SPEAKER);

        req.setAttribute("availableTopics", topicsWithoutSpeakers);
        req.setAttribute("proposedTopics", proposedTopics);
        req.setAttribute("accounts", speakers);

        if(checkLanguageEN(req)) {
            return Constants.OFFER_TOPIC_TO_SPEAKER_INFO_EN;
        }

        return Constants.OFFER_TOPIC_TO_SPEAKER_INFO;
    }
}
