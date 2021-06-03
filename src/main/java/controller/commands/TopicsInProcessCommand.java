package controller.commands;

import dao.Constants;
import dao.MyException;
import model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TopicsInProcessCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        TopicService topicService = new TopicService();
        int speakerID = Integer.parseInt((String) req.getSession().getAttribute(Constants.FIELD_ID));

        req.setAttribute("topicsInProcess", topicService.getTopicsInProcess(speakerID));

        if(checkLanguageEN(req)) {
            return Constants.TOPICS_IN_PROCESS_EN;
        }

        return Constants.TOPICS_IN_PROCESS;
    }
}
