package controller.commands;

import dao.Constants;
import dao.MyException;
import model.entities.Event;
import model.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This command gets the list of sorted events to the user
 * for observation. User can choose parameters of sorting
 * and filters.
 * There are two options by the time of spending:
 * 1. Finished events
 * 2. Not finished events
 * And three options by sorting:
 * 1. By the quantity of topics
 * 2. By the quantity of listeners
 * 3. By the time of spending (latest at first)
 * In general 6 combinations are available
 */

public class GetEventsCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        String sorting = req.getParameter("sortBy");
        String time = req.getParameter("eventTime");

        int page = Integer.parseInt(req.getParameter("page"));
        page = (page - 1) * 5;

        boolean isNotFinished = time.equals("future");
        EventService service = new EventService();

        List<Integer> pages = service.getCountOfPages(isNotFinished);
        List<Event> events = service.getSortedEventsFromIndex(page, time, sorting);

        req.setAttribute("events", events);
        req.setAttribute("pages", pages);
        req.setAttribute("sortBy", sorting);
        req.setAttribute("eventTime", time);

        if(checkLanguageEN(req)) {
            return Constants.SORTED_EVENTS_EN;
        }

        return Constants.SORTED_EVENTS;
    }
}
