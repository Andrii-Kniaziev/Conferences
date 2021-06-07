package controller.commands;

import dao.Constants;
import dao.MyException;
import model.entities.Event;
import model.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MarkPresenceInfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        int id = Integer.parseInt((String) req.getSession().getAttribute(Constants.FIELD_ID));
        EventService service = new EventService();

        List<Event> eventsPast = service.getEventsVisited(id);
        req.setAttribute("events", eventsPast);

        if(checkLanguageEN(req)) {
            return Constants.CONFIRMATION_OF_PRESENCE_EN;
        }

        return Constants.CONFIRMATION_OF_PRESENCE;
    }
}
