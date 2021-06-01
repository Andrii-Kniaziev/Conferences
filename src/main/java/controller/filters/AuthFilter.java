package controller.filters;

import controller.commands.Command;
import dao.Constants;
import model.entities.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final HttpSession session = req.getSession();

        String role = (String) session.getAttribute("role");
        String command = req.getParameter("command");

        if(req.getSession().getAttribute("language") == null) {
            req.getSession().setAttribute("language", "UA");
        }

        String warning = req.getSession().getAttribute("language").equals(Constants.UA) ?
                "Для такої дії будьласка розлогінтеся" :
                "For such an action logout please";

        if(role != null && !role.equals(Role.UNKNOWN.getValue())
                && (command.equals("login")
                || command.equals("register")
                || command.equals("changeLang"))) {
            req.setAttribute("result", warning);
            redirectToAccount(req, res, role);
        } else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }

    public void redirectToAccount(HttpServletRequest req, HttpServletResponse resp, String role)
            throws ServletException, IOException {
        boolean isUA = req.getSession().getAttribute("language").equals(Constants.UA);

        if(isUA && Constants.ROLE_ADMIN.equals(role)) {
            req.getRequestDispatcher(Constants.ADMIN_ACCOUNT).forward(req, resp);
        }

        if(isUA && Constants.ROLE_SPEAKER.equals(role)) {
            req.getRequestDispatcher(Constants.SPEAKER_ACCOUNT).forward(req, resp);
        }

        if(isUA && Constants.ROLE_LISTENER.equals(role)) {
            req.getRequestDispatcher(Constants.LISTENER_ACCOUNT).forward(req, resp);
        }

        if(Constants.ROLE_ADMIN.equals(role)) {
            req.getRequestDispatcher(Constants.ADMIN_ACCOUNT_EN).forward(req, resp);
        }

        if(Constants.ROLE_SPEAKER.equals(role)) {
            req.getRequestDispatcher(Constants.SPEAKER_ACCOUNT_EN).forward(req, resp);
        }

        if(Constants.ROLE_LISTENER.equals(role)) {
            req.getRequestDispatcher(Constants.LISTENER_ACCOUNT_EN).forward(req, resp);
        }

//        if ("admin".equals(role)) {
//            req.getRequestDispatcher(Constants.ADMIN_ACCOUNT).forward(req, resp);
//        } else if ("listener".equals(role)) {
//            req.getRequestDispatcher(Constants.LISTENER_ACCOUNT).forward(req, resp);
//        } else {
//            req.getRequestDispatcher(Constants.SPEAKER_ACCOUNT).forward(req, resp);
//        }
    }
}
