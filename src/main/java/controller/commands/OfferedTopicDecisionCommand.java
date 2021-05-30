package controller.commands;

import dao.Constants;
import dao.MyException;
import model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OfferedTopicDecisionCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        String decision = req.getParameter("decision");
        int topicID = Integer.parseInt(req.getParameter("topicID"));

        System.out.println("Info ===> " + decision + " " + topicID);

        TopicService service = new TopicService();

        boolean isExecuted = service.offeredTopicDecision(decision, topicID);
        String result = decision.equals("yes") ?
                "Вы соглаились вести топик №" + topicID : "Вы отказались вести топик №" + topicID;

        if (!isExecuted) {
            result = "Извините, что то пошло нетак, попробуйте снова позже";
        }

        req.setAttribute("result", result);
        return Constants.SPEAKER_ACCOUNT;
    }
}
