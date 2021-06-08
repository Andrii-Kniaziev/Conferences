package controller.commands;

import dao.Constants;
import dao.MyException;
import model.entities.Event;
import model.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

/**
 * This command is responsible for creation of new events.
 * Only admin can create new event. In case if wrong input date
 * is entered, corresponding warning message appears.
 * In case of successful creation of new event, confirmation
 * message appears.
 */

public class CreateEventCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        Properties pr = getProperties(req);

        String result = pr.getProperty("eventCreated");
        EventService service = new EventService();

        String name = req.getParameter("eventName");
        String description = req.getParameter("eventDescription");
        String dateTime = req.getParameter("eventDate") + " " + req.getParameter("eventTime");
        String place = req.getParameter("eventPlace");

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        GregorianCalendar cal = new GregorianCalendar();

        Date date = null;
        try {
            date = df.parse(dateTime);
        } catch (ParseException e) {
            result = pr.getProperty("wrongDateTime");
            req.setAttribute("result", result);
            return nextPage(req);
        }

        cal.setTime(date);
        boolean isFinished = cal.getTimeInMillis() < new GregorianCalendar().getTimeInMillis();

        service.insertEvent(new Event(name, description, cal, place, isFinished));
        req.setAttribute("result", result);

        return nextPage(req);
    }

    public String nextPage(HttpServletRequest req) {
        if(checkLanguageEN(req)) {
            return Constants.ADMIN_ACCOUNT_EN;
        }
        return Constants.ADMIN_ACCOUNT;
    }
}
