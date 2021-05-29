package controller.commands;

import dao.Constants;
import dao.MyException;
import model.entities.Visit;
import model.service.VisitService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscribeForEventCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        VisitService service = new VisitService();
        int id = Integer.parseInt((String) req.getSession().getAttribute("id"));
        int eventID = Integer.parseInt(req.getParameter("eventID"));
        Visit visit = new Visit(id, eventID);

        boolean res = service.createNewVisit(visit);

        if(res) {
            req.setAttribute("result", "Вы подписались на ивент. Его id: " + eventID);
        } else {
            req.setAttribute("result", "Вы уже подписаны на етот ивент");
        }

        return Constants.LISTENER_ACCOUNT;
    }
}
