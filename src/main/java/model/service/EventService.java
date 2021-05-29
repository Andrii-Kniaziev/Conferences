package model.service;

import dao.EventDAO;
import dao.MyException;
import model.entities.Event;

import java.util.ArrayList;
import java.util.List;

public class EventService {

    public List<Event> getEventsFromIndex (int index) throws MyException {
        List<Event> events = new ArrayList<>();

        EventDAO dao = EventDAO.getInstance();

        events = dao.getEventsFrom(index);

        return events;
    }
}
