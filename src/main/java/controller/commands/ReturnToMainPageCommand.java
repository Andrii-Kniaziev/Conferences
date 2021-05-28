package controller.commands;

import dao.Constants;
import dao.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReturnToMainPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        String role = (String) req.getSession().getAttribute("role");
        if(role.equals(Constants.ROLE_SPEAKER)) {
            return Constants.SPEAKER_ACCOUNT;
        }
        if(role.equals(Constants.ROLE_ADMIN)) {
            return Constants.ADMIN_ACCOUNT;
        }
        return Constants.LISTENER_ACCOUNT;
    }
}
