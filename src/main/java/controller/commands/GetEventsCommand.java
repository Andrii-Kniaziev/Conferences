package controller.commands;

import dao.Constants;
import dao.MyException;
import model.entities.Event;
import model.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
