package controller.commands;

import dao.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReturnToMainPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String role = (String) req.getSession().getAttribute("role");
        String language = (String) req.getSession().getAttribute("language");

        if(role.equals(Constants.ROLE_SPEAKER) && language.equals(Constants.UA)) {
            return Constants.SPEAKER_ACCOUNT;
        }

        if(role.equals(Constants.ROLE_ADMIN) && language.equals(Constants.UA)) {
            return Constants.ADMIN_ACCOUNT;
        }

        if(role.equals(Constants.ROLE_LISTENER) && language.equals(Constants.UA)) {
            return Constants.LISTENER_ACCOUNT;
        }

        if(role.equals(Constants.ROLE_SPEAKER)) {
            return Constants.SPEAKER_ACCOUNT_EN;
        }

        if(role.equals(Constants.ROLE_ADMIN)) {
            return Constants.ADMIN_ACCOUNT_EN;
        }

        return Constants.LISTENER_ACCOUNT_EN;
    }
}
