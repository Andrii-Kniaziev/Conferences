package controller.commands;

import dao.Constants;
import dao.MyException;
import model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitTopicToSpeakerCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        int topicID = Integer.parseInt(req.getParameter("topicID"));
        int speakerID = Integer.parseInt(req.getParameter("speakerID"));

        TopicService service = new TopicService();

        if(service.offerEmptyTopicToSpeaker(topicID, speakerID)) {
            req.setAttribute("result", "Топик: " + topicID + " был предложин спикеру: " + speakerID);
        } else {
            req.setAttribute("result", "Топик не был отправлен, попробуйте пожалуйста позже");
        }

        if(checkLanguageEN(req)) {
            return Constants.ADMIN_ACCOUNT_EN;
        }

        return Constants.ADMIN_ACCOUNT;
    }
}
