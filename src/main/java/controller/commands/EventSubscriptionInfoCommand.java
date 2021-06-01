package controller.commands;

import dao.Constants;
import dao.MyException;
import model.entities.Event;
import model.service.EventService;
import model.service.VisitService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

        String language = (String) req.getSession().getAttribute("language");

        if(checkLanguageEN(req)) {
            return Constants.EVENT_SUBSCRIPTION_EN;
        }

        return Constants.EVENT_SUBSCRIPTION;
    }
}
