package controller.commands;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import dao.Constants;
import dao.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        String lang = req.getParameter("language");

        System.out.println(lang);

        if(lang.equals(Constants.UA)){
            req.getSession().setAttribute("language", Constants.UA);
            return Constants.INDEX_JSP;
        }

        req.getSession().setAttribute("language", Constants.EN);
        return Constants.INDEX_EN_JSP;
    }
}
