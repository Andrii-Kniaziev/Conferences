package controller;

import controller.commands.*;
import dao.MyException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends javax.servlet.http.HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig config) throws ServletException {
        config.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("register", new RegisterCommand());
        commands.put("login", new LoginCommand());
        commands.put("logOut", new LogoutCommand());
        commands.put("createEvent", new CreateEventCommand());
        commands.put("topicCreateForm", new DataForTopicCreationCommand());
        commands.put("returnToAcc", new ReturnToMainPageCommand());
        commands.put("registerTopic", new CreateTopicCommand());
        commands.put("eventSubscriptionInfo", new EventSubscriptionInfoCommand());
        commands.put("subscribeForEvent", new SubscribeForEventCommand());
        commands.put("getEvents", new GetEventsCommand());
        commands.put("showOfferedTopics", new ShowOfferedTopicsCommand());
        commands.put("offeredTopicDecision", new OfferedTopicDecisionCommand());
        commands.put("offerTopicToSpeakerInfo", new OfferTopicInfoCommand());
        commands.put("offerTopicToSpeaker", new SubmitTopicToSpeakerCommand());
        commands.put("changeLang", new ChangeLanguageCommand());
        commands.put("unsubscribeFromEvent", new UnsubscribeFromEventCommand());
        commands.put("offerTopicForEvent", new OfferTopicForEvent());
        commands.put("approveOrDenyTopic", new TopicDecisionCommand());
        commands.put("showTopicsInProcess", new TopicsInProcessCommand());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String address = "WEB-INF/views/error.jsp";
        String commandName = request.getParameter("command");

        Command command = commands.get(commandName);

        try {
            address = command.execute(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", ex.getCause());
        }

        request.getRequestDispatcher(address).forward(request, response);
    }
}
