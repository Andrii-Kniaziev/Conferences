package controller.commands;

import dao.Constants;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommandTest {
    private ChangeLanguageCommand command = new ChangeLanguageCommand();

    @Before
    public void setUp() {
        command = new ChangeLanguageCommand();
    }

    @Test
    public void test_change_language_command() {
        final HttpServletRequest req = mock(HttpServletRequest.class);
        final HttpServletResponse res = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);

        when(req.getParameter("language")).thenReturn(Constants.UA);
        when(req.getSession()).thenReturn(session);

        assertEquals(command.execute(req, res), Constants.INDEX_JSP);

        when(req.getParameter("language")).thenReturn(Constants.EN);
        assertEquals(command.execute(req, res), Constants.INDEX_EN_JSP);
    }
}
