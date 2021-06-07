package dao;

import model.entities.Event;

import java.util.List;

public interface EventDAO extends SuperDAO {

    boolean insertEvent(Event event) throws MyException;

    List<Event> getAllEvents() throws MyException;

    List<Event> getEventsFrom(int index, String query) throws MyException;

    int getEventCount(boolean isNotFinished);

    List<Integer> getNotFinishedEventIDs() throws MyException;

    List<Event> getEventsVisited(int listenerID) throws MyException;

    List<Integer> getFinishedEventIDs() throws MyException;
}
