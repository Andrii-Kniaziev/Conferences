package controller.commands;

import dao.AccountDAO;
import dao.Constants;
import dao.EventDAO;
import dao.MyException;
import model.entities.Account;
import model.entities.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DataForTopicCreationCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        EventDAO dao = EventDAO.getInstance();
        AccountDAO dao1 = AccountDAO.getInstance();

        List<Event> events = dao.getAllEvents();
        List<Account> speakers = dao1.getAccounts(Constants.ROLE_SPEAKER);

        req.setAttribute("events", events);
        req.setAttribute("accounts", speakers);

        return Constants.TOPIC_CREATION_JSP;
    }
}
