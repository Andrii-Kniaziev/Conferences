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

    /**
     * Method gets portion of 5 not finished events
     * starting from index without sorting. Method is used
     * for sending events to listener for subscription purposes.
     * @param index starting index
     * @return list of events
     * @throws MyException in case if something is wrong with connection to DB
     */

    public List<Event> getEventsFromIndex(int index) throws MyException {
        try (EventDAO dao = daoFactory.createEventDao()) {
            return dao.getEventsFrom(index, Constants.GET_EVENTS_FROM_INDEX);
        }
    }

    /**
     * Method returns list of 5 sorted events starting
     * from some index, this is needed for pagination.
     * @param index indicator of first event on the page
     * @param time finished events or not finished
     * @param sorting indicator of sorting
     * @return List of 5 sorted events starting from some index
     * @throws MyException in case if something is wrong with connection to DB
     */

    public List<Event> getSortedEventsFromIndex(int index, String time, String sorting) throws MyException {
        String query = new EventMapper().getQueryForEventSort(time, sorting);
        try (EventDAO dao = daoFactory.createEventDao()) {
            return dao.getEventsFrom(index, query);
        }
    }

    /**
     * Method returns quantity of pages for pagination using
     * indicator for finished or not finished events
     * @param isNotFinished indicator (finished events will be
     * counted or not finished)
     * @return list integers which represents count of pages
     */

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

    /**
     * Method returns list of not finished events
     * @return list of not finished events
     * @throws MyException in case if something is wrong with connection to DB
     */

    public List<Event> getAllEvents() throws MyException {
        try (EventDAO dao = daoFactory.createEventDao()) {
            return dao.getAllEvents();
        }
    }

    /**
     * Method creates new event
     * @param event contains information about event
     * that will be created
     * @return 'true' in case of successful creation
     * @throws MyException in case if something is wrong with connection to DB
     */

    public boolean insertEvent(Event event) throws MyException {
        try (EventDAO dao = daoFactory.createEventDao()) {
            return dao.insertEvent(event);
        }
    }

    /**
     * The method checks if event is still not finished
     * @param eventID indicator of event to check
     * @return 'true' if event is not finished and vice versa
     * @throws MyException in case if something is wrong with connection to DB
     */

    public boolean checkEventIsNotFinished(int eventID) throws MyException {
        List<Integer> notFinished;
        try (EventDAO dao = daoFactory.createEventDao()) {
            notFinished = dao.getNotFinishedEventIDs();
        }

        if (notFinished.contains(eventID)) {
            return true;
        }

        return false;
    }
}
