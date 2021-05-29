package model.service;

import dao.DaoFactory;
import dao.MyException;
import dao.VisitDAO;
import model.entities.Visit;

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

}
