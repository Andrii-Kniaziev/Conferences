package controller.commands;

import dao.Constants;
import model.service.VisitService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

public class UnsubscribeFromEventCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Properties pr = getProperties(req);
        VisitService visitService = new VisitService();

        int eventID = Integer.parseInt(req.getParameter("eventID"));
        int listenerID = Integer.parseInt((String) req.getSession().getAttribute("id"));

        String success = pr.getProperty("unsubscribedFromEvent") + " " + eventID;
        String failure = pr.getProperty("tryAgainLater");

        if(visitService.unsubscribeFromEvent(listenerID, eventID)) {
            req.setAttribute("result", success);
        } else {
            req.setAttribute("result", failure);
        }

        if(checkLanguageEN(req)) {
            return Constants.LISTENER_ACCOUNT_EN;
        }

        return Constants.LISTENER_ACCOUNT;
    }
}
