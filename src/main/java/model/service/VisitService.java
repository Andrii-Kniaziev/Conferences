package model.service;

import dao.MyException;
import dao.VisitDAO;
import model.entities.Visit;

public class VisitService {
    private VisitDAO visitDAO = VisitDAO.getInstance();

    public boolean createNewVisit(Visit visit) {
        try {
            visitDAO.insertVisit(visit);
        } catch (MyException e) {
            System.out.println(e.getCause());
            return false;
        }
        return true;
    }

}
