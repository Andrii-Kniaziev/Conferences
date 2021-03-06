package controller.commands;

import dao.Constants;
import dao.MyException;
import model.entities.Account;
import model.entities.Event;
import model.service.AccountService;
import model.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This command returns information to the form
 * where new topics will be created. Data for
 * topic creation is: available speakers and
 * not finished events at the moment of creation new
 * topic.
 */

public class DataForTopicCreationCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        EventService eventService = new EventService();
        AccountService accountService = new AccountService();

        List<Event> events = eventService.getAllEvents();
        List<Account> speakers = accountService.getAccountsByRole(Constants.ROLE_SPEAKER);

        req.setAttribute("events", events);
        req.setAttribute("accounts", speakers);

        if(checkLanguageEN(req)) {
            return Constants.TOPIC_CREATION_JSP_EN;
        }

        return Constants.TOPIC_CREATION_JSP;
    }
}
