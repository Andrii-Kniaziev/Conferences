package controller.commands;

import dao.Constants;
import dao.MyException;
import model.entities.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        HttpSession session = req.getSession();
        session.setAttribute("role", Role.UNKNOWN.getValue());

        CommandUtility.removeUserFromAuthorised(req, (String) session.getAttribute(Constants.FIELD_ID));

        if(checkLanguageEN(req)) {
            return Constants.INDEX_EN_JSP;
        }

        return Constants.INDEX_JSP;
    }
}
