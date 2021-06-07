package controller.commands;

import dao.Constants;
import dao.MyException;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class EventSubscriptionInfoCommandTest {
    EventSubscriptionInfoCommand command;
    Map<String, Object> attributes;
    Integer flag;

    @Before
    public void setUp() {
        command = new EventSubscriptionInfoCommand();
        attributes = new HashMap<>();
    }

    @Test
    public void execute_test() throws MyException {
        HttpServletRequest req = spy(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn("35");
        when(req.getParameter("page")).thenReturn("1");
        when(req.getParameter("language")).thenReturn(Constants.UA);

        assertEquals(command.execute(req, res), Constants.EVENT_SUBSCRIPTION);
    }
}
