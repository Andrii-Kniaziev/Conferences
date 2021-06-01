package dao;

import model.entities.Visit;

import java.util.List;

public interface VisitDAO extends SuperDAO {

    public boolean insertVisit(Visit visit) throws MyException;

    public List<Visit> selectVisitsByUser(int listenerID) throws MyException;

    public boolean deleteVisit(int listenerID, int eventID) throws MyException;
}
