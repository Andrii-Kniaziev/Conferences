package controller.commands;

import dao.Constants;
import model.entities.Visit;
import model.service.VisitService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * Listener can subscribe for some event
 * from list of not finished events. In case
 * if listener already subscribed for some event
 * he will not be able to subscribe second time.
 */

public class SubscribeForEventCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Properties pr = getProperties(req);

        VisitService service = new VisitService();
        int id = Integer.parseInt((String) req.getSession().getAttribute("id"));
        int eventID = Integer.parseInt(req.getParameter("eventID"));
        Visit visit = new Visit(id, eventID);

        boolean res = service.createNewVisit(visit);

        String success = pr.getProperty("subscriptionForEventSuccess");
        String failure = pr.getProperty("subscriptionForEventFailure");

        if(res) {
            req.setAttribute("result", success + eventID);
        } else {
            req.setAttribute("result", failure);
        }

        if(checkLanguageEN(req)) {
            return Constants.LISTENER_ACCOUNT_EN;
        }

        return Constants.LISTENER_ACCOUNT;
    }
}
