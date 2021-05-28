package controller;

import controller.commands.*;
import dao.Constants;
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

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("hello from post");
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("hello from GET");
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String address = "WEB-INF/views/error.jsp";
        String commandName = request.getParameter("command");

        System.out.println("Command is: " + commandName);

        //Command command = CommandContainer.getCommandByName(commandName);
        Command command = commands.get(commandName);

        try {
            address = command.execute(request, response);
        } catch (MyException ex) {
            request.setAttribute("error", ex.getCause());
        }

        request.getRequestDispatcher(address).forward(request, response);
    }
}
