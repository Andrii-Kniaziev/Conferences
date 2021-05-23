package controller.commands;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

public class CommandUtility {

    @SuppressWarnings("unchecked")
    public static boolean removeUserFromAuthorised(HttpServletRequest request, String id) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");

        if(loggedUsers.contains(id)){
            loggedUsers.remove(id);
            request.getSession().getServletContext()
                    .setAttribute("loggedUsers", loggedUsers);
            return true;
        }

        return false;
    }
}
