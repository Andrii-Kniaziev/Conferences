package controller.commands;

import dao.Constants;
import dao.EventDAO;
import dao.MyException;
import model.entities.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateEventCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws MyException {
        String result = "Ивент добавлен";
        EventDAO dao = EventDAO.getInstance();

        String name = req.getParameter("eventName");
        String description = req.getParameter("eventDescription");
        String dateTime = req.getParameter("eventDate");
        String place = req.getParameter("eventPlace");

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        GregorianCalendar cal = new GregorianCalendar();

        Date date = null;
        try {
            date = df.parse(dateTime);
        } catch (ParseException e) {
            result = "Неверный формат поля \"Дата и Время\"";
            req.setAttribute("result", result);
            return Constants.ADMIN_ACCOUNT;
        }

        cal.setTime(date);
        boolean isFinished = cal.getTimeInMillis() < new GregorianCalendar().getTimeInMillis();
        Event event = new Event(name, description, cal, place, isFinished);

        System.out.println(date.getTime() - new GregorianCalendar().getTimeInMillis());

        dao.insertEvent(event);
        req.setAttribute("result", result);

        return Constants.ADMIN_ACCOUNT;
    }
}
