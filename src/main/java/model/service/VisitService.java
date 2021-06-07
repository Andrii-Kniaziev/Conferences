package model.service;

import dao.DaoFactory;
import dao.MyException;
import dao.VisitDAO;
import model.entities.Visit;

import java.util.ArrayList;
import java.util.List;

public class VisitService {
    private DaoFactory daoFactory;

    public VisitService() {
        daoFactory = DaoFactory.getInstance();
    }

    /**
     * Method creates new visit of listener to some event
     * @param visit info of visit
     * @return true if visit was crated or 'false' if visit already exists
     */

    public boolean createNewVisit(Visit visit) {
        try(VisitDAO dao = daoFactory.createVisitDao()) {
            return dao.insertVisit(visit);
        } catch (MyException e) {
            return false;
        }
    }

    /**
     * Method returns list of visits of some user by user`s ID
     * @param listenerID indicator to find visits
     * @return list visit`s IDs
     * @throws MyException in case of problems with connection to DB
     */

    public List<Integer> getEventsIDsByListener(int listenerID) throws MyException {
        List<Visit> visits;
        try(VisitDAO dao = daoFactory.createVisitDao()) {
            visits = dao.selectVisitsByUser(listenerID);
        }

        List<Integer> eventIDs = new ArrayList<>();

        for (Visit v : visits) {
            eventIDs.add(v.getEventID());
        }

        return eventIDs;
    }

    /**
     * Method will delete visit of listener for some
     * event if listener doesn`t want to go anymore
     * @param listenerID indicator of listener
     * @param eventID indicator of event
     * @return 'true' in case of success and 'false' vice versa
     */

    public boolean unsubscribeFromEvent(int listenerID, int eventID) {
        try(VisitDAO dao = daoFactory.createVisitDao()) {
            dao.deleteVisit(listenerID, eventID);
        } catch (MyException e) {
            return false;
        }
        return true;
    }

    /**
     * Method will mark presence or absence of some
     * listener on finished event for which this listener
     * was subscribed
     * @param listenerID indicator of listener
     * @param eventID indicator of event
     * @param presence 'yes' or 'no'
     * @return 'true' in case of success and 'false' vice versa
     */

    public boolean markPresence(int listenerID, int eventID, String presence) {
        try(VisitDAO dao = daoFactory.createVisitDao()) {
            dao.markPresence(listenerID, eventID, presence);
        } catch (MyException e) {
            return false;
        }
        return true;
    }

    public String[] getStatisticsOfEvent(int eventID) {
        try(VisitDAO dao = daoFactory.createVisitDao()) {
            return dao.getStatistics(eventID);
        }
    }

}
