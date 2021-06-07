package dao;

import model.entities.Visit;
import java.util.List;

public interface VisitDAO extends SuperDAO {

    boolean insertVisit(Visit visit) throws MyException;

    List<Visit> selectVisitsByUser(int listenerID) throws MyException;

    boolean deleteVisit(int listenerID, int eventID) throws MyException;

    boolean markPresence(int listenerID, int eventID, String presence) throws MyException;

    String[] getStatistics(int eventID);
}
