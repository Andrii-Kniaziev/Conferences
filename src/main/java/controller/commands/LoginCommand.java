package controller.commands;

import dao.impl.JDBCAccountDAO;
import dao.Constants;
import dao.MyException;
import model.entities.Account;
import model.entities.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        String result = "Пользователь с таким аддрессом електронной почты не зарегетрирован";
        JDBCAccountDAO dao = JDBCAccountDAO.getInstance();

        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        Account account = dao.getAccountByEmail(email);

        if(account == null) {
            req.setAttribute("result", result);
            return Constants.REGISTER_RESULT;
        }

        if(!pass.equals(account.getPassword())) {
            result = "Неверный логин или пароль";
            req.setAttribute("result", result);
            return Constants.REGISTER_RESULT;
        }

        return chooseDir(req, account);
    }

    @SuppressWarnings("unchecked")
    public String chooseDir(HttpServletRequest req, Account account) {
        HashSet<String> loggedUsers = (HashSet<String>) req.getSession().getServletContext()
                .getAttribute("loggedUsers");

        req.getSession().setAttribute("name", account.getFirstName() + " " + account.getLastName());
        req.getSession().setAttribute("id", account.getId() + "");
        loggedUsers.add(account.getId() + "");
        req.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);

        if(account.getRole() == Role.ADMIN) {
            req.getSession().setAttribute("role", Role.ADMIN.getValue());
            return Constants.ADMIN_ACCOUNT;
        }

        if(account.getRole() == Role.SPEAKER) {
            req.getSession().setAttribute("role", Role.SPEAKER.getValue());
            return Constants.SPEAKER_ACCOUNT;
        }

        req.getSession().setAttribute("role", Role.LISTENER.getValue());
        return Constants.LISTENER_ACCOUNT;
    }
}
