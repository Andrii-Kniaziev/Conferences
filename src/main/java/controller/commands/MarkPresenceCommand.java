package controller.commands;

import dao.Constants;
import model.service.VisitService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

public class MarkPresenceCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Properties pr = getProperties(req);
        String presence = req.getParameter("presence");
        int id = Integer.parseInt((String) req.getSession().getAttribute(Constants.FIELD_ID));
        int eventID = Integer.parseInt(req.getParameter("eventID"));

        String result = presence.equals("yes") ?
                pr.getProperty("wasPresent") + " " + eventID : pr.getProperty("wasNotPresent") + " " + eventID;

        if (new VisitService().markPresence(id, eventID, presence)) {
            req.setAttribute("result", result);
        } else {
            req.setAttribute("result", pr.getProperty("tryAgainLater"));
        }

        if (checkLanguageEN(req)) {
            return Constants.LISTENER_ACCOUNT_EN;
        }

        return Constants.LISTENER_ACCOUNT;
    }
}
