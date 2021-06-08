package controller.commands;

import dao.Constants;
import dao.MyException;
import model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Method will send topics that specified speaker
 * has offered to spend to admin, but admin hasn`t
 * done decision yet.
 */

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
