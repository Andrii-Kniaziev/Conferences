package controller.commands;

import dao.Constants;
import dao.MyException;
import model.entities.Account;
import model.entities.Role;
import model.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Properties;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Properties pr = getProperties(req);

        String result = pr.getProperty("emailNotRegistered");
        AccountService service = new AccountService();

        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        Account account = service.getAccountByEmail(email);

        if(account == null) {
            req.setAttribute("result", result);
            return checkLanguageEN(req) ? Constants.REGISTER_RESULT_EN : Constants.REGISTER_RESULT;
        }

        if(!pass.equals(account.getPassword())) {
            result = pr.getProperty("wrongLoginOrPassword");
            req.setAttribute("result", result);
            return checkLanguageEN(req) ? Constants.REGISTER_RESULT_EN : Constants.REGISTER_RESULT;
        }

        return chooseDir(req, account);
    }

    /**
     * Method chooses further direction i.e. next page by the
     * information about user which is represented by Account account
     * object
     * @param req to get information about used language and user
     * @param account representation of account
     * @return further direction
     */

    @SuppressWarnings("unchecked")
    public String chooseDir(HttpServletRequest req, Account account) {
        String language = (String) req.getSession().getAttribute("language");

        HashSet<String> loggedUsers = (HashSet<String>) req.getSession().getServletContext()
                .getAttribute("loggedUsers");

        req.getSession().setAttribute("name", account.getFirstName() + " " + account.getLastName());
        req.getSession().setAttribute("id", account.getId() + "");
        loggedUsers.add(account.getId() + "");
        req.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);

        if(account.getRole() == Role.ADMIN && language.equals(Constants.UA)) {
            req.getSession().setAttribute("role", Role.ADMIN.getValue());
            return Constants.ADMIN_ACCOUNT;
        }

        if(account.getRole() == Role.SPEAKER && language.equals(Constants.UA)) {
            req.getSession().setAttribute("role", Role.SPEAKER.getValue());
            return Constants.SPEAKER_ACCOUNT;
        }

        if(account.getRole() == Role.LISTENER && language.equals(Constants.UA)) {
            req.getSession().setAttribute("role", Role.LISTENER.getValue());
            return Constants.LISTENER_ACCOUNT;
        }

        if(account.getRole() == Role.ADMIN && language.equals(Constants.EN)) {
            req.getSession().setAttribute("role", Role.ADMIN.getValue());
            return Constants.ADMIN_ACCOUNT_EN;
        }

        if(account.getRole() == Role.SPEAKER && language.equals(Constants.EN)) {
            req.getSession().setAttribute("role", Role.SPEAKER.getValue());
            return Constants.SPEAKER_ACCOUNT_EN;
        }

        req.getSession().setAttribute("role", Role.LISTENER.getValue());
        return Constants.LISTENER_ACCOUNT_EN;
    }
}
