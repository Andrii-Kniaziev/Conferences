package controller.commands;

import dao.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String lang = req.getParameter("language");

        if(lang.equals(Constants.UA)){
            req.getSession().setAttribute("language", Constants.UA);
            return Constants.INDEX_JSP;
        }

        req.getSession().setAttribute("language", Constants.EN);
        return Constants.INDEX_EN_JSP;
    }
}
