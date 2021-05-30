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

public class OfferTopicInfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        TopicService topicService = new TopicService();
        AccountService accService = new AccountService();

        List<Topic> topicsWithoutSpeakers = topicService.getTopicsWithoutSpeakers();
        List<Account> speakers = accService.getAccountsByRole(Constants.ROLE_SPEAKER);

        req.setAttribute("availableTopics", topicsWithoutSpeakers);
        req.setAttribute("accounts", speakers);

        return Constants.OFFER_TOPIC_TO_SPEAKER_INFO;
    }
}
