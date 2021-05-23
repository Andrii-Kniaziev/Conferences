package controller;

import controller.commands.Command;
import controller.commands.CommandContainer;
import dao.MyException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends javax.servlet.http.HttpServlet {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "WEB-INF/views/error.jsp";
        String commandName = request.getParameter("command");

        Command command = CommandContainer.getCommandByName(commandName);

        try {
            address = command.execute(request, response);
        } catch (MyException ex) {
            request.setAttribute("error", ex.getMessage());
        }

        request.getRequestDispatcher(address).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
