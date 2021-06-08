package controller.commands;

import dao.Constants;
import dao.MyException;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.BDDMockito.given;
import static org.junit.Assert.*;

public class EventSubscriptionInfoCommandTest {
    EventSubscriptionInfoCommand command;
    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse res;
    @Mock
    HttpSession session;

    public EventSubscriptionInfoCommandTest() {
        MockitoAnnotations.initMocks(this);
        command = new EventSubscriptionInfoCommand();
    }

    @Test
    public void execute_test() throws MyException {
        given(req.getSession()).willReturn(session);
        given(req.getParameter("page")).willReturn("1");
        given(session.getAttribute("id")).willReturn("38");
        given(session.getAttribute("language")).willReturn("UA");

        assertEquals(command.execute(req, res), Constants.EVENT_SUBSCRIPTION);
    }
}
