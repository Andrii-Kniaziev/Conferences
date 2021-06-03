package controller.commands;

import dao.Constants;
import dao.MyException;
import model.entities.Topic;
import model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowOfferedTopicsCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        int myID = Integer.parseInt((String) req.getSession().getAttribute(Constants.FIELD_ID));

        TopicService service = new TopicService();
        List<Topic> offeredTopics = service.getOfferedTopics(myID);
        List<Topic> agreedTopics = service.getAgreedTopics(myID);

        req.setAttribute("offeredTopics", offeredTopics);
        req.setAttribute("agreedTopics", agreedTopics);

        if(checkLanguageEN(req)) {
            return Constants.OFFERED_TOPICS_EN;
        }

        return Constants.OFFERED_TOPICS;
    }
}
