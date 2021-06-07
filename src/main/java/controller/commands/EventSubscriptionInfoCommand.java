package controller.commands;

import dao.Constants;
import dao.MyException;
import model.entities.Event;
import model.service.EventService;
import model.service.VisitService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This command is used to send list of events to the listener.
 * Pagination of pages is executed in this command.
 * Count of events on each page is 5.
 * Listener can subscribe or unsubscribe for some event.
 */

public class EventSubscriptionInfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        EventService eventService = new EventService();
        VisitService visitService = new VisitService();

        int id = Integer.parseInt((String) req.getSession().getAttribute("id"));
        int page = Integer.parseInt(req.getParameter("page"));
        page = (page - 1) * 5;

        List<Event> events = eventService.getEventsFromIndex(page);
        List<Integer> pages = eventService.getCountOfPages(true);
        List<Integer> eventIDs = visitService.getEventsIDsByListener(id);

        req.setAttribute("events", events);
        req.setAttribute("pages", pages);
        req.setAttribute("eventIDs", eventIDs);

        if(checkLanguageEN(req)) {
            return Constants.EVENT_SUBSCRIPTION_EN;
        }

        return Constants.EVENT_SUBSCRIPTION;
    }
}
