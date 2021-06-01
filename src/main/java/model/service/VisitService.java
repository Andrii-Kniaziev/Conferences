package model.service;

import dao.DaoFactory;
import dao.MyException;
import dao.VisitDAO;
import model.entities.Visit;

import java.util.ArrayList;
import java.util.List;

public class VisitService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public boolean createNewVisit(Visit visit) {
        try(VisitDAO dao = daoFactory.createVisitDao()) {
            return dao.insertVisit(visit);
        } catch (MyException e) {
            System.out.println(e.getCause());
            return false;
        }
    }

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

    public boolean unsubscribeFromEvent(int listenerID, int eventID) {
        try(VisitDAO dao = daoFactory.createVisitDao()) {
            dao.deleteVisit(listenerID, eventID);
        } catch (MyException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
