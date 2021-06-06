package controller.commands;

import dao.Constants;
import dao.MyException;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DataForTopicCreationCommandTest {
    private DataForTopicCreationCommand command;

    @Before
    public void setUp() {
        command = new DataForTopicCreationCommand();
    }

    @Test
    public void next_page_test() throws MyException {
        final HttpServletRequest req = mock(HttpServletRequest.class);
        final HttpServletResponse res = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);
        when(req.getSession().getAttribute("language")).thenReturn(Constants.UA);

        assertEquals(command.execute(req, res), Constants.TOPIC_CREATION_JSP);

        when(req.getSession().getAttribute("language")).thenReturn(Constants.EN);
        assertEquals(command.execute(req, res), Constants.TOPIC_CREATION_JSP_EN);
    }
}
