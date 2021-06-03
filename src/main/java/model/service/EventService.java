package model.service;

import dao.Constants;
import dao.DaoFactory;
import dao.EventDAO;
import dao.MyException;
import dao.mapper.EventMapper;
import model.entities.Event;

import java.util.ArrayList;
import java.util.List;

public class EventService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Event> getEventsFromIndex(int index) throws MyException {
        try (EventDAO dao = daoFactory.createEventDao()) {
            return dao.getEventsFrom(index, Constants.GET_EVENTS_FROM_INDEX);
        }
    }

    public List<Event> getSortedEventsFromIndex(int index, String time, String sorting) throws MyException {
        String query = new EventMapper().getQueryForEventSort(time, sorting);
        try (EventDAO dao = daoFactory.createEventDao()) {
            return dao.getEventsFrom(index, query);
        }
    }

    public List<Integer> getCountOfPages(boolean isNotFinished) {
        List<Integer> list = new ArrayList<>();
        try (EventDAO dao = daoFactory.createEventDao()) {
            int count = dao.getEventCount(isNotFinished);
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

    public List<Event> getAllEvents() throws MyException {
        try (EventDAO dao = daoFactory.createEventDao()) {
            return dao.getAllEvents();
        }
    }

    public boolean insertEvent(Event event) throws MyException {
        try (EventDAO dao = daoFactory.createEventDao()) {
            return dao.insertEvent(event);
        }
    }

    public boolean checkEventIsNotFinished(int eventID) throws MyException {
        List<Integer> notFinished = new ArrayList<>();
        try (EventDAO dao = daoFactory.createEventDao()) {
            notFinished = dao.getNotFinishedEventIDs();
        }

        if (notFinished.contains(eventID)) {
            return true;
        }

        return false;
    }
}
