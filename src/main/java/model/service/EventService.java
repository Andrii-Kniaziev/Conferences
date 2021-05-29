package model.service;

import dao.DaoFactory;
import dao.EventDAO;
import dao.MyException;
import model.entities.Event;

import java.util.ArrayList;
import java.util.List;

public class EventService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Event> getEventsFromIndex(int index) throws MyException {
        try (EventDAO dao = daoFactory.createEventDao()) {
            return dao.getEventsFrom(index);
        }
    }

    public List<Integer> getCountOfPages() {
        List<Integer> list = new ArrayList<>();
        try (EventDAO dao = daoFactory.createEventDao()) {
            int count = dao.getNotFinishedEventCount();
            int temp = count / 5;

            if((count - temp * 5) != 0) {
                temp++;
            }

            for (int i = 1; i <= temp; i++) {
                list.add(i);
            }

        }
        return list;
    }

    public List<Event> getAllEvents(boolean isNotFinished) throws MyException {
        try (EventDAO dao = daoFactory.createEventDao()) {
            return dao.getAllEvents(isNotFinished);
        }
    }

    public boolean insertEvent(Event event) throws MyException {
        try (EventDAO dao = daoFactory.createEventDao()) {
            return dao.insertEvent(event);
        }
    }
}
