package controller;

import controller.commands.Command;
import controller.commands.CommandContainer;
import dao.MyException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

public class Servlet extends javax.servlet.http.HttpServlet {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }
    }

    public void init(ServletConfig config) throws ServletException {
        config.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
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

        System.out.println("Command is: " + commandName);

        Command command = CommandContainer.getCommandByName(commandName);

        try {
            address = command.execute(request, response);
        } catch (MyException ex) {
            request.setAttribute("error", ex.getMessage());
        }

        request.getRequestDispatcher(address).forward(request, response);
    }
}
