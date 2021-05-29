package controller.commands;

import dao.Constants;
import dao.MyException;
import model.entities.Event;
import model.service.EventService;
import model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EventSubscriptionInfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        EventService service = new EventService();
        int page = Integer.parseInt(req.getParameter("page"));
        page = (page - 1) * 5;

        List<Event> events = service.getEventsFromIndex(page);
        List<Integer> pages = service.getCountOfPages();

        System.out.println(page);

        req.setAttribute("events", events);
        req.setAttribute("pages", pages);

        return Constants.EVENT_SUBSCRIPTION;
    }
}
