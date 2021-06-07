package controller.commands;

import dao.Constants;
import dao.MyException;
import model.service.EventService;
import model.service.VisitService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

public class CheckStatisticsCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        Properties pr = getProperties(req);
        int eventID = Integer.parseInt(req.getParameter("eventID"));

        if(!new EventService().checkEventIsFinished(eventID)) {
            req.setAttribute("result", pr.getProperty("idOfEventThatWasNotFinishedOrNotExists"));

            return nextPage(req);
        }

        String[] stat = new VisitService().getStatisticsOfEvent(eventID);
        req.setAttribute("result", String.format(pr.getProperty("statistics"), stat[0], stat[1]));

        return nextPage(req);
    }

    public String nextPage(HttpServletRequest req) {
        if(checkLanguageEN(req)) {
            return Constants.ADMIN_ACCOUNT_EN;
        }
        return Constants.ADMIN_ACCOUNT;
    }
}
