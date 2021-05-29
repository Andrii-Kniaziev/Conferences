package dao;

import model.entities.Visit;

public interface VisitDAO extends SuperDAO {

    public boolean insertVisit(Visit visit) throws MyException;
}
