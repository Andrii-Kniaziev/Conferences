package dao;

import model.entities.Event;

import java.util.List;

public interface EventDAO extends SuperDAO {

    boolean insertEvent(Event event) throws MyException;

    List<Event> getAllEvents(boolean isNotFinished) throws MyException;

    List<Event> getEventsFrom(int index) throws MyException;

    public int getNotFinishedEventCount();
}
