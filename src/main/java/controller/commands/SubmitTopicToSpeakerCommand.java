package controller.commands;

import dao.Constants;
import dao.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitTopicToSpeakerCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        int topicID = Integer.parseInt((String) req.getParameter("topicID"));
        int speakerID = Integer.parseInt((String) req.getParameter("speakerID"));

        System.out.println("SubmitTopicToSpeakerCommand ===> " + topicID + " " + speakerID);
        //ToDo : finish command

        return Constants.ADMIN_ACCOUNT;
    }
}
