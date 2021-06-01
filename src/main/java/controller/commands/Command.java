package controller.commands;

import dao.Constants;
import dao.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;

public interface Command {
    String EN_PROPERTIES = "properties_en.properties";
    String UA_PROPERTIES = "properties_ua.properties";

    String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException;

    default Properties getProperties(HttpServletRequest req) {
        Properties pr = new Properties();
        InputStream is = null;

        try {
            if (req.getSession().getAttribute("language").equals(Constants.UA)) {
                is = getClass().getClassLoader().getResourceAsStream(UA_PROPERTIES);
            } else {
                is = getClass().getClassLoader().getResourceAsStream(EN_PROPERTIES);
            }
            pr.load(is);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return pr;
    }

    default boolean checkLanguageEN(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute("language");
        if (language == null) {
            return false;
        }

        if (language.equals(Constants.UA)) {
            return false;
        }

        return true;
    }

}
