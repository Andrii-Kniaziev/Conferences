package controller.filters;

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
        String command = (String) req.getParameter("command");

        System.out.println("Command name from FILTER: " + command);

        if(role != null && !role.equals(Role.UNKNOWN.getValue())
                && (command.equals("login") || command.equals("register"))) {
            req.setAttribute("result", "Для выполнения таких действий, разлогиньтесь");
            redirectToAccount(req, res, role);
        } else {
            req.getRequestDispatcher("/conferences").forward(request, response);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    public void redirectToAccount(HttpServletRequest req, HttpServletResponse resp, String role)
            throws ServletException, IOException {
        if ("admin".equals(role)) {
            req.getRequestDispatcher(Constants.ADMIN_ACCOUNT).forward(req, resp);
        } else if ("listener".equals(role)) {
            req.getRequestDispatcher(Constants.LISTENER_ACCOUNT).forward(req, resp);
        } else {
            req.getRequestDispatcher(Constants.SPEAKER_ACCOUNT).forward(req, resp);
        }
    }
}
